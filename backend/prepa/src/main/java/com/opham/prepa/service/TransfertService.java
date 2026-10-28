package com.opham.prepa.service;

import com.opham.prepa.Utils.Convert;
import com.opham.prepa.Utils.UserSessionManager;
import com.opham.prepa.mapper.Transfert.*;
import com.opham.prepa.model.Transfert.*;
import com.opham.prepa.repository.Transfert.TransfertRepository;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;


import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Types;
import java.util.*;


@Repository
public class TransfertService implements TransfertRepository {


    private final UserSessionManager userSessionManager;

    @Autowired
    public TransfertService(UserSessionManager userSessionManager) {

        this.userSessionManager = userSessionManager;
    }

  /*  @Autowired
    public JdbcTransfertRepository(JdbcTemplate jdbcTemplate, DataSourceConfig dataSourceConfig) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSourceConfig = dataSourceConfig;
    }*/


    @Override
    public List<List<Object>> listRotation(String sessionId, String depot, String alle) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("bp_afficheRotation2")
                .declareParameters(
                        new SqlParameter("depot", Types.VARCHAR),
                        new SqlParameter("alle", Types.VARCHAR)

                )
                .returningResultSet("result1", new RotationMapper())
                .returningResultSet("result2", new TransfertMapper());

        Map<String, Object> results = jdbcCall.execute(depot, alle);

        Map<String, List<Object>> mappedResults = new HashMap<>();

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add((List<Object>) results.get("result1"));
        resultList.add((List<Object>) results.get("result2"));
        return resultList;
    }

    @Override
    public List<List<Object>> listColisIncomplet(String sessionId, String depotOrg, String depotDest, String alle) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("bp_colisIncomplet1")
                .declareParameters(
                        new SqlParameter("depotOrg", Types.VARCHAR),
                        new SqlParameter("depotDest", Types.VARCHAR),
                        new SqlParameter("alle", Types.VARCHAR)

                )
                .returningResultSet("result1", new ColisIncompletMapper())
                .returningResultSet("result2", new TransfertMapper());

        Map<String, Object> results = jdbcCall.execute(depotOrg, depotDest, alle);

        Map<String, List<Object>> mappedResults = new HashMap<>();

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add((List<Object>) results.get("result1"));
        resultList.add((List<Object>) results.get("result2"));
        return resultList;
    }

    @Override
    public List<List<Object>> listPetitColis(String sessionId, String depotOrg, String depotDest, String alle) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("bp_petitColis")
                .declareParameters(
                        new SqlParameter("depotOrg", Types.VARCHAR),
                        new SqlParameter("depotDest", Types.VARCHAR),
                        new SqlParameter("alle", Types.VARCHAR)

                )
                .returningResultSet("result1", new PetitColisMapper())
                .returningResultSet("result2", new TransfertMapper());

        Map<String, Object> results = jdbcCall.execute(depotOrg, depotDest, alle);

        Map<String, List<Object>> mappedResults = new HashMap<>();

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add((List<Object>) results.get("result1"));
        resultList.add((List<Object>) results.get("result2"));
        return resultList;
    }

    @Override
    public List<List<Object>> listRangement(String sessionId, String depot) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("x_RangementAffiche")
                .declareParameters(
                        new SqlParameter("depot", Types.VARCHAR)

                )
                .returningResultSet("result1", new RangementMapper())
                .returningResultSet("result2", new TransfertMapper());

        Map<String, Object> results = jdbcCall.execute(depot);

        Map<String, List<Object>> mappedResults = new HashMap<>();

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add((List<Object>) results.get("result1"));
        resultList.add((List<Object>) results.get("result2"));
        return resultList;
    }

    @Override
    public int insertL6_Transfert(String sessionId, Transfert t) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "\n" +
                "INSERT INTO DBSUIVI..L6_TRANSFERT\n" +
                "\t(Article, libelle, labo, depotOrg, emplOrg, depotDest, emplDest, lot, dateper, lettre, qte, numarm1, numarm2, devise, padev, paht, frais, ug, qtenonEtq, qteEncours, qteEtiq, id,ip_web)\n" +
                "VALUES \n" +
                "\t(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,? ,? ,? ,? ,? ,? ,? , ?,?)";

        return jdbcTemplate.update(sql, t.getArticle(), t.getLibelle(), t.getLabo(), t.getDepot_org(), t.getEmpl_org(), t.getDepot_dest(), t.getEmpl_dest(), t.getLot(), t.getDatePer(), t.getLettre(),
                t.getQte(), t.getNumarm1(), t.getNumarm1(), t.getDevise(), t.getPadev(), t.getPaht(), t.getFrais(), t.getUg(), t.getQteNonEt(), t.getQteEnCoursEt(), t.getQteEt(), t.getMyId(), t.getIp_web());
    }

    @Override
    public int insertL6_TransfertConseil(String sessionId, TransfertConseilReappro t) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "\n" +
                "INSERT INTO DBSUIVI..L6_TRANSFERT\n" +
                "\t(Article, libelle, labo, depotOrg, emplOrg, depotDest, emplDest, lot, dateper, lettre, qte, numarm1, numarm2, devise, padev, paht, frais, ug, qtenonEtq, qteEncours, qteEtiq, id,commentaire, urgent,ip_web)\n" +
                "VALUES \n" +
                "\t(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,? ,? ,? ,? ,? ,? ,? , ?,?,?,?)";

        return jdbcTemplate.update(sql, t.getArticle(), t.getLibelle(), t.getLabo(), t.getDepot_org(), t.getEmpl_org(), t.getDepot_dest(), t.getEmpl_dest(), t.getLot(), t.getDatePer(), t.getLettre(),
                t.getQte(), t.getNumarm1(), t.getNumarm1(), t.getDevise(), t.getPadev(), t.getPaht(), t.getFrais(), t.getUg(), t.getQteNonEt(), t.getQteEnCoursEt(), t.getQteEt(), t.getMyId(), t.getCommentaire(), t.getUrg(), t.getIp_web());
    }

    @Override
    public String insert_FSIL(String sessionId, String ids, String commentaire, String depOrg, String depDest,String user_web) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "exec v_bp_transfert ?, ?,?,?,?";
        String result = jdbcTemplate.queryForObject(sql, new Object[]{ids, commentaire, depOrg, depDest,user_web}, String.class);
        return result;
    }

    @Override
    public ProblemeStock testStock(String sessionId, String article, int qte, String depot, String lettre, String empl) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "exec v_bp_testStockConseilAppro ?,?,?,?,?";
        return jdbcTemplate.queryForObject(sql, new ProblemeStockMapper(), article, qte, depot, lettre, empl);
    }

    @Override
    public int stockPasVide(String sessionId, String depot_Dest, String empl_Dest) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.queryForObject("select count(*) from VIEW_STOCK_LOT_EMPL_FRBP where  STEMPDEPOT=? and STEMPEMP=? and isnull(QTE_DISPO,0)>0",
                Integer.class, depot_Dest, empl_Dest);
    }

    @Override
    public byte[] generateReportTransfert(String sessionId, String td, String org, String dest, Integer isDouble, String users) {
        try {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

            String hexes = "";

            if (td.startsWith("TD")) {
                hexes = String.format("%08X",
                        Integer.parseInt(td.substring(2, 10) + "1")
                );
            } else if (td.startsWith("TS")) {
                hexes = String.format("%08X",
                        Integer.parseInt(td.substring(2, 10) + "8")
                );
            } else if (td.startsWith("TE")) {
                hexes = String.format("%08X",
                        Integer.parseInt(td.substring(2, 10) + "9")
                );
            } else {
                // Cas inattendu
                hexes = "00000000";
            }

            InputStream jrxmlInputStream = getClass().getResourceAsStream("/report/transfertA5.jrxml");
            if (jrxmlInputStream == null) {
                throw new FileNotFoundException("Le fichier JRXML n'a pas été trouvé.");
            }
            JasperReport jasperReport = net.sf.jasperreports.engine.JasperCompileManager.compileReport(jrxmlInputStream);

            // Paramètres du rapport
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("code_TD", td);
            parameters.put("isDouble", isDouble);
            parameters.put("hexa", hexes);
            parameters.put("depot_org", org);
            parameters.put("depot_dest", dest);
            parameters.put("users", users);

            // Générer le rapport
            byte[] reportBytes = JasperRunManager.runReportToPdf(jasperReport, parameters, jdbcTemplate.getDataSource().getConnection());

            return reportBytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<List<Object>> conseilReappro(String sessionId, String article, String depotOrg, String depotDest) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("x_ListeReappro3")
                .declareParameters(
                        new SqlParameter("article", Types.VARCHAR),
                        new SqlParameter("depotOrg", Types.VARCHAR),
                        new SqlParameter("depotDest", Types.VARCHAR)

                )
                .returningResultSet("result1", new ConseilReapproMapper())
                .returningResultSet("result2", new TransfertConseilReapproMapper());

        Map<String, Object> results = jdbcCall.execute(null, null, article, depotOrg, depotDest);

        Map<String, List<Object>> mappedResults = new HashMap<>();

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add((List<Object>) results.get("result1"));
        resultList.add((List<Object>) results.get("result2"));
        return resultList;
    }

    @Override
    public List<SuiviTDDetail> listSuiviTDDetail(String sessionId, String code, String depOrg, String depDest, Date datedeb, Date datefin, String article, int detail, int etat) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "exec v_bp_list_transfert ?,?,?,?,?,?,?,?";
        return jdbcTemplate.query(sql, new SuiviTDDetailMapper(), code, depOrg, depDest, datedeb, datefin, article,detail,etat);
    }

    @Override
    public List<SuiviTD> listSuiviTD(String sessionId, String code, String depOrg, String depDest, Date datedeb, Date datefin, String article, int detail, int etat) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "exec v_bp_list_transfert ?,?,?,?,?,?,?,?";
        return jdbcTemplate.query(sql, new SuiviTDMapper(), code, depOrg, depDest, datedeb, datefin, article,detail,etat);
    }


}
