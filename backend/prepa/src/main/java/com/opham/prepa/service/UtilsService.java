package com.opham.prepa.service;

import com.opham.prepa.Utils.Convert;
import com.opham.prepa.Utils.UserSessionManager;
import com.opham.prepa.mapper.Utlis.*;
import com.opham.prepa.model.Utils.*;
import com.opham.prepa.repository.Utils.UtilsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.*;

@Repository
public class UtilsService implements UtilsRepository {

    private final UserSessionManager userSessionManager;

    @Autowired
    public UtilsService(UserSessionManager userSessionManager) {

        this.userSessionManager = userSessionManager;
    }


    @Override
    public List<String> findAllAxe(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("select rtrim(PRCODE) as CLPR from FPR where PRDESAC=0 order by PRCODE", new AxeMapper());
    }

    @Override
    public List<String> findAllTarif(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("select distinct ARTTARIF  from FART", new TarifMapper());
    }

    @Override
    public List<Alle> listAlle(String sessionId, String depot, String critere) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "select xDEPOT,xALLE,isnull(xSTATUT,0) as xSTATUT from xEMP_DIGUE where rtrim(xDEPOT)=rtrim(?)" +
                (critere != null && !critere.isEmpty() ? "AND " + critere : "") + "order by xALLE";

        return jdbcTemplate.query(sql, new AlleMapper(), depot);
    }


    @Override
    public List<String> findParCode(String sessionId, String code) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("select rtrim(KELIBELLE) as KELIBELLE from KEnumerations where rtrim(KECODE)=rtrim(?) order by KEORDRE",
                new EnumerationMapper(), code);
    }


    @Override
    public Alerte getAlerte(String sessionId, String code) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.queryForObject("select rtrim(code) as code,val,Alerte from xTiming where rtrim(code) =rtrim(?)",
                new Object[]{code}, new AlerteMapper());
    }

    @Override
    public Etape getEtape(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.queryForObject(" exec v_bp_etape_badge",
                new Object[]{}, new EtapeMapper());
    }

    @Override
    public Lock getLock(String sessionId, String xTable, String xCode, int isClose) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.queryForObject("exec v_bp_lock ?,?,?"
                , new LockMapper(), xTable, xCode, isClose);
    }

    @Override
    public Droit getDoit(String sessionId, String xUser, String access) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.queryForObject("exec v_bp_getDroit ?,?",
                new DroitMapper(), xUser, access);
    }

    @Override
    public List<Droit> listDroit(String sessionId, String xUser) {
        JdbcTemplate jdbcTemplates = userSessionManager.getUserJdbcTemplate(sessionId);

        if (jdbcTemplates == null) {
            throw new RuntimeException("JdbcTemplate is null for user: " + xUser);
        }

        try {
            return jdbcTemplates.query("exec v_bp_getlistDroit ?", new DroitMapper(), xUser);
        } catch (DataAccessException e) {
            // Log l'erreur d'accès aux données
            System.err.println("DataAccessException while executing listDoit for user: " + xUser);
            e.printStackTrace(); // Affiche la pile d'appels pour l'erreur
            throw new RuntimeException("Error executing listDoit for user: " + xUser, e);
        }
    }


    @Override
    public List<Depot> listDepot(String sessionId, String critere) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "SELECT distinct DPCODE, DPNOM, isnull(DPCENTRAL,0) as DPCENTRAL, isnull(DPCOLIS,0) as DPCOLIS, isnull(xDPVTE,0) as xDPVTE,isnull(DP_TRANSFERT,0) as DP_TRANSFERT, isnull(DP_PREPA,0) as  DP_PREPA FROM FDP left join FAD on ADDEP=DPCODE WHERE ISNULL(DP_DESACTIVE, 0) = 0 " +
                (critere != null && !critere.isEmpty() ? "AND " + critere : "") + "order by DPCODE ";

        return jdbcTemplate.query(sql, new DepotMapper());
    }

    @Override
    public List<Reglement> listReglement(String sessionId, String critere) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "select TRCODE,TRNOM,TRDEC,TRTYPEECH,TRJOURECH,TRMODE from FTR " +
                (critere != null && !critere.isEmpty() ? "AND " + critere : "") + "order by TRNOM ";

        return jdbcTemplate.query(sql, new ReglementMapper());
    }

    @Override
    public List<AccordADV> listAccordADV(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "select ACC_CODE,ACC_DESIGNATION from xACCORD";
        return jdbcTemplate.query(sql, new AccordADVMapper());
    }


    @Override
    public String getId(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "select newID()";
        String result = jdbcTemplate.queryForObject(sql, new Object[]{}, String.class);
        return result;
    }


    @Override
    public List<Article> listArticle(String sessionId, String critere) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "\t\tselect ARCODE,ARLIB,ARFO,L.FONOM as NON_LABO,ARDEPART,F.DTLIB as FAMILLE,ARFAM,C.FPLIB as CATEGORIE,xARTP_GAM,G.FONOM as GAMME,xARTP_DISTR,D.FONOM as DISTRIBUTEUR,xARTP_FRNS,P.FONOM as FOURNIS_PRINC\n" +
                "\t\t,xARTP_FAB,B.FONOM as FABRICANT,xARTP_PLATFORME,PL.FONOM as PLATFORME,ARCHEFP,SC.FPLIB as SOUS_CAT,xARTP_DCI,ARLIBXENO,ARFRANCOFO,\n" +
                "\t\tcase when isnull(ARGENERIQUE,0)=0 then 'NORMAL' when isnull(ARGENERIQUE,0)=1 then 'GENERIQUE' when isnull(ARGENERIQUE,0)=2 then 'SPECIALITE' when isnull(ARGENERIQUE,0)=3 then 'GENERIQUE DE MARQUE' end as TYPE,\n" +
                "\t\tcase when isnull(xARTP_FROID,0)=0 then 'NORMAL' when isnull(xARTP_FROID,0)=1 then 'FROID' when isnull(xARTP_FROID,0)=2 then 'FRAIS' end as conversation,\n" +
                "\t\tcase when isnull(xARTP_STATSPEC,0)=0 then 'NORMAL' when isnull(xARTP_STATSPEC,0)=1 then 'STUPEFIANT' when isnull(xARTP_STATSPEC,0)=2 then 'PSYCHOTROPE' when isnull(xARTP_STATSPEC,0)=3 then 'HOSPITALIER' end as STATUT \n" +
                "\t\tfrom Equagestion..VIEW_FAR left join Equagestion..FFO L on L.FOCODE=ARFO left join Equagestion..FDT F on F.DTCODE=ARDEPART \n" +
                "\t\tleft join Equagestion..FFP C on C.FPCODE=ARFAM and isnull(C.FTYPE,0)=1 left join Equagestion..FFO G on G.FOCODE=xARTP_GAM and isnull(G.FOANCIEN,0)=0 and isnull(G.FOGAMME,0)=1\n" +
                "\t\tleft join Equagestion..FFO D on D.FOCODE=xARTP_DISTR and isnull(D.FOANCIEN,0)=0 and isnull(D.FODISTR,0)=1\n" +
                "\t\tleft join Equagestion..FFO P on P.FOCODE=xARTP_FRNS and isnull(P.FOANCIEN,0)=0 and isnull(P.FOFOURNIS,0)=1\n" +
                "\t\tleft join Equagestion..FFO B on B.FOCODE=xARTP_FAB and isnull(B.FOANCIEN,0)=0 and isnull(B.FOFABRIC,0)=1\n" +
                "\t\tleft join Equagestion..FFO PL on PL.FOCODE=xARTP_PLATFORME and isnull(PL.FOANCIEN,0)=0 and isnull(PL.FOPLATEFORME,0)=1\n" +
                "\t\tleft join Equagestion..FFP SC on SC.FPCODE=ARCHEFP and isnull(SC.FTYPE,0)=1\n" +
                "\t\twhere isnull(AROLD,0)=0 " +
                (critere != null && !critere.isEmpty() ? "AND " + critere : "") + "order by ARCODE,ARLIB";

        return jdbcTemplate.query(sql, new ArticleMapper());
    }

    @Override
    public List<Labo> listLabo(String sessionId, String critere) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "select rtrim(FOCODE),rtrim(FONOM),rtrim(FONOM1) from Equagestion..FFO where isnull(FOANCIEN,0)=0 " +
                (critere != null && !critere.isEmpty() ? "AND " + critere : "") + "order by FOCODE,FONOM ";
        return jdbcTemplate.query(sql, new LaboMapper());
    }

    @Override
    public List<Famille> listFamille(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "select rtrim(DTCODE),rtrim(DTLIB) from FDT order by DTCODE,DTLIB";
        return jdbcTemplate.query(sql, new FamilleMapper());
    }

    @Override
    public List<Client> listClient(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "select CLCODE,CLSEQ,CLNOM1,CLADR1,CLADR2,CLSTATUS,CLSA,CLVALEUR,CLCAT from Equagestion..FCL where CLSTATUS=0 ";
        return jdbcTemplate.query(sql, new ClientMapper());
    }

    @Override
    public int InsertAnnuaire(String sessionId, String nom, String code, String type, String numero, String user_cree) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "insert into DBSUIVI..AnnuaireClient(name,code,type,number) values (?,?,?,?)";
        return jdbcTemplate.update(sql, nom, code, type, numero);
    }

    @Override
    public int updateName(String sessionId, String numero) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "UPDATE DBSUIVI..AnnuaireClient SET code = '' WHERE seq IN (SELECT VAC.seq FROM DBSUIVI..VIEW_ANNUAIRE_CLIENT VAC WHERE VAC.numero_normal = ?)";
        return jdbcTemplate.update(sql, numero);
    }

    // debut de code de capa ----------------*-*-*
    @Override
    public List<CauseRacine> findcauseraccode(String sessionId,String code_causerac) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        StringBuilder sql = new StringBuilder("SELECT CAUSE_RAC_SEQ,CAUSE_RAC_NCPSCODE, CAUSE_RAC_DATE, CAUSE_RAC_DESCRIPTION, NBACTION, CAUSE_RAC_PROC_IMPUT, ETAT, CAUSE_RAC_VALID FROM AMC..VIEW_CAUSE_RACINE WHERE 1=1");
        List<Object> params = new ArrayList<>();

        sql.append(" AND CAUSE_RAC_NCPSCODE = ?");
        params.add(code_causerac);

        System.out.println("Requête SQL : " + sql.toString());
        System.out.println("Paramètres : " + params);

        return jdbcTemplate.query(sql.toString(), params.toArray(), new CauseRacineMapper());
    }

    @Override
    public void insertFNCPS_CAUSE(String sessionId,String causeRacCode, String causeRacDescription , Date causeRacDate, int causeRacValid, String causeRacProcImput) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        StringBuilder sql = new StringBuilder("INSERT INTO AMC..FCAUSE_RAC (CAUSE_RAC_NCPSCODE, CAUSE_RAC_DESCRIPTION, CAUSE_RAC_DATE, CAUSE_RAC_VALID, CAUSE_RAC_PROC_IMPUT) VALUES (?, ?, ?, ?, ?)");
        List<Object> params = new ArrayList<>();

        params.add(causeRacCode);
        params.add(causeRacDescription);
        params.add(causeRacDate);
        params.add(causeRacValid);
        params.add(causeRacProcImput);

        jdbcTemplate.update(sql.toString(), params.toArray());
    }

    @Override
    public void updateFCAUSE_RAC(String sessionId,int causeRacSeq, String causeRacCode, Date causeRacDate, String causeRacDescription, String causeRacProcImput, int causeRacValide) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        StringBuilder sql = new StringBuilder("UPDATE AMC..FCAUSE_RAC SET ");
        List<Object> params = new ArrayList<>();

        if (causeRacProcImput != null && !causeRacProcImput.isEmpty()) {
            sql.append("CAUSE_RAC_VALID = ?");
            params.add(causeRacValide);
        }

        if (causeRacDate != null) {
            sql.append(", CAUSE_RAC_DATE = ?");
            params.add(causeRacDate);
        }

        sql.append(", CAUSE_RAC_DESCRIPTION = ?");
        params.add(causeRacDescription);

        if (causeRacProcImput != null && !causeRacProcImput.isEmpty()) {
            sql.append(", CAUSE_RAC_PROC_IMPUT = ?");
            params.add(causeRacProcImput);
        }

        sql.append(" WHERE CAUSE_RAC_SEQ = ?");
        params.add(causeRacSeq);

        System.out.println("SQL Query: " + sql.toString());
        System.out.println("Parameters: " + params);

        jdbcTemplate.update(sql.toString(), params.toArray());
    }


    public void deleteFCAUSE_RAC(String sessionId,int causeracseq) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        StringBuilder sql = new StringBuilder("DELETE FROM AMC..FCAUSE_RAC WHERE CAUSE_RAC_SEQ = ?");
        List<Object> params = new ArrayList<>();

        params.add(causeracseq);
        jdbcTemplate.update(sql.toString(), params.toArray());
    }

    @Override
    public void insertFNCPS_USER(String sessionId,String ncpsuserusercode,String ncpsuserncpscode) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        StringBuilder sql = new StringBuilder("INSERT INTO AMC..FNCPS_USER (NCPS_USER_USERCODE, NCPS_USER_NCPSCODE) VALUES (?, ?)");
        List<Object> params = new ArrayList<>();

        params.add(ncpsuserusercode);
        params.add(ncpsuserncpscode);

        jdbcTemplate.update(sql.toString(), params.toArray());
    }

    @Override
    public List<Userinsert> listUserinsert(String sessionId,String ncpsuserncpscode) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "SELECT * FROM AMC..FNCPS_USER WHERE NCPS_USER_NCPSCODE = ?";
        return jdbcTemplate.query(sql, new Object[]{ncpsuserncpscode}, new UserinsertMapper());
    }

    @Override
    public List<Proc> listProc(String sessionId,String enumType) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "SELECT ENUM_CODE,ENUM_LIBELLE,ENUM_TYPE FROM AMC..FENUMERATION WHERE ENUM_TYPE = ?";
        return jdbcTemplate.query(sql, new Object[]{enumType}, new ProcMapper());
    }

    @Override
    public List<String> findallactiontype(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("SELECT DISTINCT LIBELLE_TYPE FROM AMC..VIEW_ACTION", new ActionTypeMapper());
    }

    @Override
    public void insertAction(String sessionId,String actionNcpscode, int actionValide, int actionMiseappli, String actionDescription,int actionCausesseq, int actionType) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        StringBuilder sql = new StringBuilder("INSERT INTO AMC..FACTION (ACTION_NCPSCODE, ACTION_VALIDE, ACTION_MISEAPPLI, ACTION_DESCRIPTION,ACTION_CAUSESEQ, ACTION_TYPE) VALUES (?,?,?,?,?,?)");
        List<Object> params = new ArrayList<>();

        params.add(actionNcpscode);
        params.add(actionValide);
        params.add(actionMiseappli);
        params.add(actionDescription);
        params.add(actionCausesseq);
        params.add(actionType);

        jdbcTemplate.update(sql.toString(), params.toArray());
    }


    @Override
    public List<String> findallemetteur(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("SELECT DISTINCT NCPS_EMETTEUR FROM AMC..VIEW_SUIVI_AMELIORATION", new EmetteurMapper());
    }

    @Override
    public List<Proc> listEnum(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "SELECT ENUM_CODE,ENUM_LIBELLE,ENUM_TYPE FROM AMC..FENUMERATION";
        return jdbcTemplate.query(sql, new ProcMapper());
    }
    @Override
    public List<String> findalldpt(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("SELECT DISTINCT LIBELLE_DEP FROM AMC..VIEW_SUIVI_AMELIORATION", new DptMapper());
    }
    @Override
    public List<String> findalldpt2(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("SELECT DISTINCT NCPS_DEPARTEMENT FROM AMC..VIEW_SUIVI_AMELIORATION", new Dpt2Mapper());
    }
    @Override
    public List<String> findalluser(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("SELECT USER_NOM FROM AMC..FUSER", new UserMapper());
    }


    @Override
    public List<Transporteur> listTransporteur(String sessionId, String critere) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "select rtrim(xNUM),xType,xCode,xCategorie,xType_Service,rtrim(xNUM)+' ('+rtrim(xType)+')' from xVOITURE where isnull(xEtat,0)=1" +
                (critere != null && !critere.isEmpty() ? "AND " + critere : "") + "order by xNUM";

        return jdbcTemplate.query(sql, new TransporteurMapper());
    }


    @Override
    public String getCode(String sessionId, String table, int cpt) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("bp_getCode_proc")
                .declareParameters(
                        new SqlParameter("table", Types.VARCHAR),
                        new SqlParameter("cpt", Types.INTEGER),
                        new SqlOutParameter("m_code", Types.VARCHAR) // Paramètre de sortie
                );

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("table", table);
        inParams.put("cpt", cpt);


        Map<String, Object> outParams = jdbcCall.execute(inParams);

        return (String) outParams.get("m_code"); // Récupérer la valeur de retour
    }

    @Override
    public List<LoginPlus> onLoginPlus(String sessionId, String critere) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "select xSeq,xUser,xPasswd,xType,xAvion,xExtra,xEtat from xLOGIN_PLUS where isnull(xEtat,0)=1" +
                (critere != null && !critere.isEmpty() ? "AND " + critere : "") + "order by xUser";

        return jdbcTemplate.query(sql, new LoginPlusMapper());
    }

    @Override
    public String getCCDepBP(String sessionId, String BP) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "SELECT BPCC FROM FBP WHERE BPCODE = ?";

        String result = jdbcTemplate.queryForObject(sql, String.class, BP);
        return result;
    }

    @Override
    public String hextoCode( String hex ,String x) {

        return Convert.hexToCode(hex,x);
    }

}