#![allow(unused)]

use std::{path::{Path, PathBuf}, sync::{Arc, Mutex}, collections::HashMap, time::{SystemTime, Duration}, fs::File, convert::Infallible};
use axum::{
    routing::{get, post},
    Router,
    extract::{self, State, Json, FromRequestParts},
    response::{Result, IntoResponse, Response},
    http::{
        Request,
        StatusCode,
        request::Parts,
    },
};
use serde::{Serialize,Deserialize};
use anyhow::anyhow;
use r2d2_freetds::freetds::Statement;
use log::{info, error};
use hyper::Body;

pub type Manager = r2d2_freetds::FreetdsConnectionManager;
pub type Pool = r2d2::Pool<Manager>;
pub type Connection = r2d2::PooledConnection<Manager>;

const DEFAULT_SESSION_EXPIRE_MINUTES: u64 = 60 * 24;

struct ExtractSessionID(String);

#[axum::async_trait]
impl<S> FromRequestParts<S> for ExtractSessionID
where
    S: Send + Sync
{
    type Rejection = (StatusCode, &'static str);

    async fn from_request_parts(parts: &mut Parts, state: &S) -> Result<Self, Self::Rejection> {
        if let Some(session_id) = parts.headers.get("X-Session-Id") {
            if let Ok(session_id) = session_id.to_str() {
                return Ok(Self(session_id.to_string()));
            }
        }
        Err((StatusCode::BAD_REQUEST, "Bad Request - No Session Id"))
    }
}

struct AppState {
    syb_server: String,
    syb_port: u16,
    syb_database: String,
    session_dir: PathBuf,
    session_expire_minutes: u64,
    sessions: Mutex<HashMap<String,Session>>,
}

impl AppState {
    fn new(syb_server: &str, syb_port: u16, syb_database: &str, session_dir: &Path, session_expire_minutes: u64) -> AppState {
        let sessions = match Self::load_sessions(session_dir) {
            Err(e) => {
                error!("Failed to load sessions from {:?}", session_dir.display());
                HashMap::new()
            },
            Ok(sessions) => {
                info!("Loaded {} session(s) from {:?}", sessions.len(), session_dir.display());
                sessions
            },
        };
        Self {
            syb_server: syb_server.to_string(),
            syb_port,
            syb_database: syb_database.to_string(),
            session_expire_minutes,
            session_dir: session_dir.to_path_buf(),
            sessions: Mutex::new(sessions),
        }
    }

    fn load_sessions(session_dir: &Path) -> anyhow::Result<HashMap<String,Session>> {
        let mut result = HashMap::new();
        for entry in std::fs::read_dir(session_dir)? {
            let entry = entry?;
            let path = entry.path();
            if let Ok(mut f) = File::open(&path) {
                if let Ok(session) = serde_json::from_reader::<_,Session>(&mut f) {
                    if let Some(filename) = path.file_stem() {
                        let session_id = filename.to_string_lossy().to_string();
                        result.insert(session_id, session);
                    }
                }
            }
        }
        Ok(result)
    }

    fn cleanup_sessions(&self) {
        let mut sessions = self.sessions.lock().unwrap();
        let now = SystemTime::now();
        let expired: Vec<String> = sessions.iter()
            .filter(|(_,session)| session.expire < now)
            .map(|(id,_)| id.to_string())
            .collect();
        for session_id in &expired {
            info!("Removing expired session {session_id}");
            let path = self.session_dir.join(&format!("{session_id}.json"));
            if let Err(e) = std::fs::remove_file(&path) {
                error!("Failed to remove {path:?}: {}", e.to_string());
            }
            sessions.remove(session_id);
        }
    }
}

fn create_pool(syb_server: &str, syb_port: u16, syb_database: &str, login: &str, password: &str) -> anyhow::Result<Manager> {
    let conn_builder = r2d2_freetds::Connection::builder()
        .host(syb_server)
        .port(syb_port)
        .client_charset("UTF-8")
        .username(login)
        .password(password)
        .database(syb_database)
        .tds_version(r2d2_freetds::freetds::TdsVersion::Tds50)
        .login_timeout(2);
    Ok(Manager::new(conn_builder))
}

pub fn load_rights(conn: &mut r2d2_freetds::Connection, user: &str) -> anyhow::Result<HashMap<String,HashMap<String,String>>> {
    let text = "
        select
            APP,
            DROIT,
            rtrim(case when R>0 then 'r' else '' end +
            case when W>0 then 'w' else '' end) as ACCESS
        from ERPO..VIEW_DROIT
        where USR=:user
    ";

    let mut st = Statement::new(text);
    st.set_param("user", user);

    let mut results: HashMap<String,HashMap<String,String>> = HashMap::new();
    let mut rs = conn.execute_statement(&st)?;
    while rs.next() {
        let app = rs.get_string("APP")?.ok_or(anyhow!("Unexpected NULL (ERPO..VIEW_DROIT.APP)"))?;
        let droit = rs.get_string("DROIT")?.ok_or(anyhow!("Unexpected NULL (ERPO..VIEW_DROIT.DROIT)"))?;
        if let Some(access) = rs.get_string("ACCESS")? {
            if let Some(map) = results.get_mut(&app) {
                map.insert(droit, access);
            } else {
                let mut new_map = HashMap::new();
                new_map.insert(droit, access);
                results.insert(app, new_map);
            }
        }
    }
    Ok(results)
}

pub fn is_admin(conn: &mut r2d2_freetds::Connection) -> anyhow::Result<bool> {
    let text = "select case when user_name() = 'dbo' then 1 else 0 end";
    let mut rs = conn.execute(text, &[])?;
    rs.next();

    let admin = rs.get_i32(0)?.unwrap_or(0) != 0;
    Ok(admin)
}

fn generate_session_id() -> String {
    use std::fmt::Write;
    use rand::Rng;

    let mut rng = rand::thread_rng();
    let mut result = String::new();
    for _ in 0..16 {
        let ch = rng.gen_range(0..62);
        if ch < 10 { /* number */
            write!(&mut result, "{}", char::from_u32((ch + b'0').into()).unwrap());
        } else if ch < 10 + 26 { /* lowercase */
            write!(&mut result, "{}", char::from_u32((ch - 10 + b'a').into()).unwrap());
        } else { /* uppercase */
            write!(&mut result, "{}", char::from_u32((ch - 36 + b'A').into()).unwrap());
        }
    }
    result
}

fn serialize_session(session_dir: &Path, session_id: &str, session: &Session) -> anyhow::Result<()> {
    let filename = session_dir.join(&format!("{session_id}.json"));
    let mut f = File::create(filename)?;
    serde_json::to_writer(&mut f, session)?;
    Ok(())
}

#[derive(Deserialize)]
struct LoginParams {
    login: String,
    password: String,
}

#[derive(Serialize)]
struct LoginResult {
    session_id: String,
}

#[derive(Serialize, Deserialize)]
struct Session {
    login: String,
    password: String,
    expire: SystemTime,
    rights: HashMap<String,HashMap<String,String>>,
}

async fn post_login(State(state): State<Arc<AppState>>, Json(payload): Json<LoginParams>) -> Result<Json<LoginResult>> {
    let conn_builder = r2d2_freetds::Connection::builder()
        .host(&state.syb_server)
        .port(state.syb_port)
        .client_charset("UTF-8")
        .username(&payload.login)
        .password(&payload.password)
        .database(&state.syb_database)
        .tds_version(r2d2_freetds::freetds::TdsVersion::Tds50)
        .login_timeout(2);

    /* Connect using this username/password combination */
    let mut conn = match conn_builder.connect() {
        Err(e) => return Err((StatusCode::UNAUTHORIZED, "Unauthorized").into()),
        Ok(conn) => conn,
    };

    /* Read rights */
    let rights = match load_rights(&mut conn, &payload.login) {
        Err(e) => {
            error!("Failed to load rights for {}: {}", payload.login, e.to_string());
            return Err((StatusCode::INTERNAL_SERVER_ERROR, "Internal Server Error").into())
        },
        Ok(rights) => rights,
    };
    drop(conn);

    /* Create session file (we don't store sessions in database to minimize db server load) */
    let session = Session {
        login: payload.login.to_string(),
        password: payload.password.to_string(),
        expire: SystemTime::now() + Duration::from_secs(state.session_expire_minutes * 60),
        rights
    };

    let session_id = generate_session_id();
    if let Err(e) = serialize_session(&state.session_dir, &session_id, &session) {
        error!("Failed to serialize session: {}", e.to_string());
    }

    state.sessions.lock().unwrap().insert(session_id.clone(), session);
    Ok(Json(LoginResult { session_id }))
}

async fn post_logout(State(state): State<Arc<AppState>>, ExtractSessionID(session_id): ExtractSessionID) -> Response {
    let mut sessions = state.sessions.lock().unwrap();
    sessions.remove(&session_id);
    drop(sessions);

    let session_file = state.session_dir.join(&format!("{session_id}.json"));
    if let Err(e) = std::fs::remove_file(&session_file) {
        error!("Failed to delete file {}: {}", session_file.display(), e.to_string());
    }
    (StatusCode::OK, "OK").into_response()
}

async fn get_login() -> impl IntoResponse {
r#"
 _________________
< 400 Bad Request >
 -----------------
        \   ^__^
         \  (xx)\_______
            (__)\       )\/\
             U  ||----w |
                ||     ||
"#.into_response()
}

#[derive(Serialize)]
struct GetAccessResult {
    login: String,
    access: HashMap<String,String>,
}

async fn get_access(
    State(state): State<Arc<AppState>>,
    ExtractSessionID(session_id): ExtractSessionID,
    extract::Path(app): extract::Path<String>
) -> Response {
    state.cleanup_sessions();

    let (login,rights) = {
        let mut sessions = state.sessions.lock().unwrap();
        match sessions.get_mut(&session_id) {
            None => {
                return ((StatusCode::UNAUTHORIZED, "Bad Session Id").into_response());
            },
            Some(mut session) => {
                session.expire = SystemTime::now() + Duration::from_secs(state.session_expire_minutes * 60);
                (session.login.clone(), session.rights.clone())
            }
        }
    };

    if let Some(access) = rights.get(&app) {
        Json(GetAccessResult {
            login,
            access: access.clone(),
        }).into_response()
    } else {
        Json(GetAccessResult {
            login,
            access: HashMap::new()
        }).into_response()
    }
}

/*
 * Queries:
 *  - POST /api/v1/login
 *      JSON { "login": "", "password": "" }
 *      200 OK { "session_id": "" }
 *
 *      Logs in and creates session
 *
 *  - POST /api/v1/logout
 *      HEADERS X-Session-Id
 *
 *  - GET /api/v1/access/$app
 *      HEADERS X-Session-Id
 *      JSON { "droit1": "r", "droit2": "rw", "droit3", "w" }
 */
#[tokio::main]
async fn main() {
    use clap::{arg, command, value_parser, ArgAction, Command, Arg, builder::PossibleValuesParser};
    let _logger = sexy::Logger::builder()
        .show_source(false)
        .build();

    let command = command!()
        .arg(arg!(-S --"syb-server" <ADDRESS> "Sybase host")
            .required(true))
        .arg(arg!(-P --"syb-port" <PORT> "Sybase port")
            .default_value("2025"))
        .arg(arg!(-s --"sessions-dir" <DIRECTORY> "Session storage directory")
            .default_value("./sessions"))
        .arg(arg!(-p --port <PORT> "Listen port")
            .default_value("3000"))
        .arg(arg!(-e --"session-expire" <SECONDS> "Session expire (minutes)")
            .default_value(&format!("{DEFAULT_SESSION_EXPIRE_MINUTES}")));
    let matches = command.get_matches();
    let syb_server = matches.get_one::<String>("syb-server").expect("Unexpected None (syb_server)");
    let syb_port = u16::from_str_radix(matches.get_one::<String>("syb-port").expect("Unexpected None (syb_port)"), 10).expect("Invalid value for syb-port");
    let sessions_dir = PathBuf::from(matches.get_one::<String>("sessions-dir").expect("Unexpected None (sessions_dir)"));
    let port = u16::from_str_radix(matches.get_one::<String>("port").expect("Unexpected None (port)"), 10).expect("Invalid value for port");
    let session_expire_minutes = u64::from_str_radix(matches.get_one::<String>("session-expire").expect("Unexpected None (session_expire_minutes)"), 10).expect("Invalid value for session-expire");

    if !sessions_dir.exists() {
        std::fs::create_dir_all(&sessions_dir).expect("Failed to create {session_dir}");
    }
    let state = Arc::new(AppState::new(
            syb_server, syb_port, "master", 
            &sessions_dir,
            session_expire_minutes));
    state.cleanup_sessions();

    // build our application with a single route
    use tower_http::trace::TraceLayer;
    //use tracing::Level;
    //use tracing_subscriber::filter;
    //use tracing_subscriber::{layer::SubscriberExt, util::SubscriberInitExt};

    //let filter = filter::Targets::new()
    //    .with_target("tower_http::trace::on_response", Level::TRACE)
    //    .with_target("tower_http::trace::on_request", Level::TRACE)
    //    .with_default(Level::INFO);
    //let tracing_layer = tracing_subscriber::fmt::layer();
    //tracing_subscriber::registry()
    //    .with(tracing_layer)
    //    .with(filter)
    //    .init();

    use tower_http::cors::{Any, CorsLayer};
    use http::Method;
    let cors = CorsLayer::new()
        .allow_methods(Any)
        .allow_headers(Any)
        .allow_origin(Any);

    let app = Router::new()
        .route("/v1/login", get(get_login))
        .route("/v1/login/", get(get_login))
        .route("/v1/login", post(post_login))
        .route("/v1/login/", post(post_login))
        .route("/v1/logout", post(post_logout))
        .route("/v1/logout/", post(post_logout))
        .route("/v1/access/:app", get(get_access))
        .route("/v1/access/:app/", get(get_access))
        .layer(TraceLayer::new_for_http())
        .layer(cors)
        .with_state(state);

    // run it with hyper on localhost:3000
    info!("Started on port {port}");
    axum::Server::bind(&format!("0.0.0.0:{port}").parse().unwrap())
        .serve(app.into_make_service())
        .await
        .unwrap();
}

