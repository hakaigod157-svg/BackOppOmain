package com.opham.prepa.service;

import com.opham.prepa.Utils.Convert;
import com.opham.prepa.Utils.UserSessionManager;
import com.opham.prepa.mapper.Amm.*;
import com.opham.prepa.mapper.EnPreparation.EnCoursPrepBPMapper;
import com.opham.prepa.mapper.Expedition.*;
import com.opham.prepa.mapper.Transfert.RotationMapper;
import com.opham.prepa.mapper.Transfert.TransfertMapper;
import com.opham.prepa.mapper.Utlis.DroitMapper;
import com.opham.prepa.mapper.Utlis.LoginPlusMapper;
import com.opham.prepa.mapper.ValidationCommande.CommandeAlivrerMapper;
import com.opham.prepa.model.EnPreparation.EnCoursPrepBP;
import com.opham.prepa.model.Expedition.*;
import com.opham.prepa.model.Utils.Droit;
import com.opham.prepa.model.Utils.LoginPlus;
import com.opham.prepa.model.ValidationCommande.CommandeAlivrer;
import com.opham.prepa.repository.Expedition.ExpeditionRepository;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.*;

@Repository
public class ExpeditionService implements ExpeditionRepository {
    private final UserSessionManager userSessionManager;

    public ExpeditionService(UserSessionManager userSessionManager) {
        this.userSessionManager = userSessionManager;
    }

    @Override
    public int insertTransFacture(String sessionId, Trans_facture T) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "INSERT INTO x_FA_TRANSP(FA_TRANS_CODE,FA_TRANS_NUM_FACT,FA_TRANS_DATE_FACT, FA_TRANS_MONTANT,FA_TRANS_TRANSPORT)\n" +
                "    VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(sql, T.getCode(), T.getFacture(), T.getDate(), T.getMontant(), T.getTransport());

    }

    @Override
    public int updateTransFacture(String sessionId, Trans_facture T) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "update x_FA_TRANSP set FA_TRANS_NUM_FACT=?,FA_TRANS_DATE_FACT=?,FA_TRANS_MONTANT=?,FA_TRANS_TRANSPORT=? where FA_TRANS_CODE=? ";
        return jdbcTemplate.update(sql, T.getFacture(), T.getDate(), T.getMontant(), T.getTransport(), T.getCode());
    }

    @Override
    public List<Trans_Attribution_Lv> findAttribLv(String sessionId, String transporteur, Date datedeb, Date datefin) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("exec x_ATTRIB_LV ?,?,?",
                new Trans_Attribution_LvMapper(), transporteur, datedeb, datefin);
    }

    @Override
    public int insertTransFacture_L(String sessionId, Trans_facture_L L) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "insert into x_FA_TRANS_L(TRANS_L_CODE,TRANS_L_LIV) values (?,?)";
        return jdbcTemplate.update(sql, L.getCode(), L.getCode_liv());
    }

    @Override
    public List<Trans_facture_L> listTrans_Fact_L(String sessionId, String code) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "select TRANS_L_LIV from x_FA_TRANS_L where TRANS_L_CODE=? order by TRANS_L_LIV";
        return jdbcTemplate.query(sql, new Trans_Facture_L_Mapper(), code);
    }

    @Override
    public void deleteTrans_Fact(String sessionId, String liv, String code) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "delete from x_FA_TRANS_L where TRANS_L_LIV=? and TRANS_L_CODE=?";
        jdbcTemplate.update(sql, liv, code);
    }

    @Override
    public int test_Attrib_Liv(String sessionId, String liv, String transport) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("v_bp_test_attrib_liv")
                .declareParameters(
                        new SqlParameter("liv", Types.VARCHAR),
                        new SqlParameter("transport", Types.VARCHAR),
                        new SqlOutParameter("retour", Types.INTEGER)
                );

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("liv", liv);
        inParams.put("transport", transport);

        Map<String, Object> outParams = jdbcCall.execute(inParams);

        return (int) outParams.get("retour");
    }


    @Override
    public int updateTransValide(String sessionId, String trans_code) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "update x_FA_TRANSP set FA_TRANS_VALIDE = 1 where FA_TRANS_CODE = ? ";
        return jdbcTemplate.update(sql, trans_code);
    }


    @Override
    public List<Trans_Detail_Lv> listDetailLv(String sessionId, String num_code, int type) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "x_DETAIL_LIV ?,?";
        return jdbcTemplate.query(sql, new Trans_Detail_LvMapper(), num_code, type);
    }

    @Override
    public List<Trans_Detail_Nb_cmd> listDetailNbCmd(String sessionId, String code_ft, int type) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "x_DETAIL_LIV ?,?";
        return jdbcTemplate.query(sql, new Trans_Detail_Nb_cmdMapper(), code_ft, type);
    }

    @Override
    public List<Trans_Detail_Lv> ajoutLv(String sessionId, String lv) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "x_AJOUT_LIV ?";
        return jdbcTemplate.query(sql, new Trans_Detail_LvMapper(), lv);
    }

  /*  @Override
    public List<List<Object>> listLivraison(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("x_PiloteLivraison")
                .declareParameters(
                        new SqlParameter("depot", Types.VARCHAR),
                        new SqlParameter("alle", Types.VARCHAR)

                )
                .returningResultSet("result1", new livraisonMapper())
                .returningResultSet("result2", new livraisonMapper())
                .returningResultSet("result3", new livraisonMapper())
                .returningResultSet("result4", new livraisonMapper())
                .returningResultSet("result5", new livraisonMapper())
                .returningResultSet("result6", new detail_livraisonMapper());

        Map<String, Object> results = jdbcCall.execute();

        Map<String, List<Object>> mappedResults = new HashMap<>();

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add((List<Object>) results.get("result1"));
        resultList.add((List<Object>) results.get("result2"));
        resultList.add((List<Object>) results.get("result3"));
        resultList.add((List<Object>) results.get("result4"));
        resultList.add((List<Object>) results.get("result5"));
        resultList.add((List<Object>) results.get("result6"));
        return resultList;
    } */



/*    @Override
    public List<List<Object>> listLivraison(String sessionId, String modeLiv) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        jdbcTemplate.execute("USE Equagestion");
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("dbo")
                .withProcedureName("x_PiloteLivraison_ERPO")
                .declareParameters(
                        new SqlParameter("modeLiv", Types.VARCHAR)
                )
                .returningResultSet("result1", new livraison_ophamMapper())
                .returningResultSet("result2", new detail_livraisonMapper())
                .returningResultSet("result3", new livraison_ophamDemainMapper())
                .returningResultSet("result4", new detail_livraisonDemainMapper());

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("modeLiv", modeLiv);

        Map<String, Object> results = jdbcCall.execute(inParams);

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add((List<Object>) results.get("result1"));
        resultList.add((List<Object>) results.get("result2"));
        resultList.add((List<Object>) results.get("result3"));
        resultList.add((List<Object>) results.get("result4"));
        return resultList;
    } */

    @SuppressWarnings("unchecked")
    private List<Object> getSafeResult(Map<String, Object> results, String key) {
        Object value = results.get(key);
        return value != null ? (List<Object>) value : new ArrayList<>();
    }

    @Override
    public List<List<Object>> listLivraison(String sessionId, String modeLiv) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        jdbcTemplate.execute("USE Equagestion");

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("dbo")
                .withProcedureName("x_PiloteLivraison_ERPO_Tout")
                .declareParameters(
                        new SqlParameter("modeLiv", Types.VARCHAR)
                )
                .returningResultSet("result1", new livraison_ophamMapper())
                .returningResultSet("result2", new detail_livraisonMapper())
                .returningResultSet("result3", new livraison_ophamDateMapper())
                .returningResultSet("result4", new livraison_ophamDemainMapper())
                .returningResultSet("result5", new detail_livraisonDemainMapper())
                .returningResultSet("result6", new livraison_ophamDemainDateMapper());


        Map<String, Object> inParams = new HashMap<>();
        inParams.put("modeLiv", modeLiv);

        Map<String, Object> results = jdbcCall.execute(inParams);

        List<List<Object>> resultList = new ArrayList<>();

        resultList.add(getSafeResult(results, "result1"));
        resultList.add(getSafeResult(results, "result2"));
        resultList.add(getSafeResult(results, "result3"));
        resultList.add(getSafeResult(results, "result4"));
        resultList.add(getSafeResult(results, "result5"));
        resultList.add(getSafeResult(results, "result6"));

        return resultList;
    }


    @Override
    public List<List<Object>> listIndLivraison(String sessionId, Date datedebut,Date dateFin,String modeLiv) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

                jdbcTemplate.execute("USE Equagestion");
                SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("dbo")
                .withProcedureName("x_PilotageLivraisonInd_ERPO")
                .declareParameters(
                        new SqlParameter("datedebut", Types.VARCHAR),
                        new SqlParameter("dateFin", Types.VARCHAR),
                        new SqlParameter("modeLiv", Types.VARCHAR)
                )
                .returningResultSet("result1", new indicateur_livraisonMapper())
                .returningResultSet("result2", new indicateur_liv_detailMapper())
                .returningResultSet("result3", new indicateur_liv_tauxMapper());


        Map<String, Object> inParams = new HashMap<>();
        // ✅ Gestion des nulls
        String dateDebStr = (datedebut != null) ? new java.sql.Date(datedebut.getTime()).toString() : null;
        String dateFinStr = (dateFin != null) ? new java.sql.Date(dateFin.getTime()).toString() : null;

        inParams.put("datedebut", dateDebStr);
        inParams.put("dateFin", dateFinStr);
        inParams.put("modeLiv", modeLiv);
        Map<String, Object> results = jdbcCall.execute(inParams);

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add((List<Object>) results.get("result1"));
        resultList.add((List<Object>) results.get("result2"));
        resultList.add((List<Object>) results.get("result3"));

        return resultList;
    }


    @Override
    public List<List<Object>> listIndChauffeur(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

            jdbcTemplate.execute("USE Equagestion");

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withSchemaName("dbo")
                    .withProcedureName("x_PiloteLivraison_Chauf_ERPO")
             /*   .declareParameters(
                        new SqlParameter("modeLiv", Types.VARCHAR)
                ) */
                .returningResultSet("result1", new indicateur_chauffeurMapper());

        Map<String, Object> inParams = new HashMap<>();
      /*  inParams.put("modeLiv", modeLiv); */

        Map<String, Object> results = jdbcCall.execute(inParams);

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add((List<Object>) results.get("result1"));

        return resultList;
    }



    @Override
    public List<edition_lv> listEditionLv(String sessionId, String livraison, String commande, Date date, String chauf, String voit, Integer saisie) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "bp_afficheLivraison ?,?,?,?,?,?";
        return jdbcTemplate.query(sql, new edition_lvMapper(), livraison, commande, date, chauf, voit, saisie);
    }

    @Override
    public byte[] genererLV(String sessionId, String code) {
        try {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
            String hexes = Convert.decimalToHexadecimal(Integer.parseInt(code.substring(2, 10) + "6"));
            InputStream jrxmlInputStream = getClass().getResourceAsStream("/report/LVA4.jrxml");
            if (jrxmlInputStream == null) {
                throw new FileNotFoundException("Le fichier JRXML n'a pas été trouvé.");
            }
            JasperReport jasperReport = net.sf.jasperreports.engine.JasperCompileManager.compileReport(jrxmlInputStream);

            // Paramètres du rapport
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("code", code);
            parameters.put("hexa", hexes);

            // Générer le rapport
            byte[] reportBytes = JasperRunManager.runReportToPdf(jasperReport, parameters, jdbcTemplate.getDataSource().getConnection());

            return reportBytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<params_liv> listparamsLv(String sessionId ,String code_client) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        /*String sql="select * from x_ParamLivraisonCLient";*/
        /*String sql="exec x_afficheParamLivraison ?";*/
        String sql = "exec x_afficheParamLivraison '" + code_client + "'";
        return jdbcTemplate.query(sql,new params_livMapper());
    }

    @Override
    public List<params_liv_adr> listparamsLv_adr(String sessionId, int seq) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "exec x_afficheParamLivraison_ADR " + seq;
        return jdbcTemplate.query(sql, new params_liv_adrMapper());
    }

    @Override
    public byte[] genererCourse(String sessionId, String code) {
        try {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

            String hexes = String.format("%08X", Integer.parseInt(code.substring(2, 10).concat("6")));
            InputStream jrxmlInputStream = getClass().getResourceAsStream("/report/LV_COURSE.jrxml");
            if (jrxmlInputStream == null) {
                throw new FileNotFoundException("Le fichier JRXML n'a pas été trouvé.");
            }
            JasperReport jasperReport = net.sf.jasperreports.engine.JasperCompileManager.compileReport(jrxmlInputStream);

            // Paramètres du rapport
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("code", code);
            parameters.put("hexa", hexes);

            // Générer le rapport
            byte[] reportBytes = JasperRunManager.runReportToPdf(jasperReport, parameters, jdbcTemplate.getDataSource().getConnection());

            return reportBytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

        @Override
        public void deleteParams(String sessionId, String code_client, int jour,int heure) {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
            String sql = "delete from x_ParamLivraisonCLient where LIV_CLT=? and LIV_JOUR =? and LIV_HEURE =? ";
            jdbcTemplate.update(sql, code_client, jour,heure);
        }

        @Override
        public void deleteParamsAdr(String sessionId,int seq, String code_client, int jour,int heure) {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
            String sql = "delete from x_ParamLivraisonCLient_ADR where LIV_SEQ_ADR=? and LIV_CLT=? and LIV_JOUR =? and LIV_HEURE =? ";
            jdbcTemplate.update(sql, seq,code_client, jour,heure);
        }

        @Override
        public int insert_params(String sessionId, String code_client, int jour,int heure) {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
            String sql = "INSERT INTO x_ParamLivraisonCLient(LIV_CLT,LIV_JOUR,LIV_HEURE) VALUES (?,?,?)";
            return jdbcTemplate.update(sql, code_client, jour,heure);
        }

        @Override
        public int insert_paramsAdr(String sessionId,int seq, String code_client, int jour,int heure) {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
            String sql = "INSERT INTO x_ParamLivraisonCLient_ADR(LIV_SEQ_ADR,LIV_CLT,LIV_JOUR,LIV_HEURE) VALUES (?,?,?,?)";
            return jdbcTemplate.update(sql, seq,code_client, jour,heure);
        }

        @Override
        public List<DureeLV> getDureeLv(String sessionId, String code) {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
            String sql = "exec x_EditionLivraison ?";
            return jdbcTemplate.query(sql, new DureeLVMapper(), code);
        }

        @Override
        public int onInsertDuree(String sessionId, String code, String code_client, Timestamp h_arrive,Timestamp h_depart) {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
            String sql = "update FCC2 set CC2ARRIVECLIENT=?,CC2DEPARTCLIENT=? from FCC2 inner join FCC on CCCODE=CC2CODE inner join xCH_LIVRAISON on xLIV_SEQ2=CC2LIVRAISON and CC2_CODE_LIVRAISON=xLIV_CODE where xLIV_CODE=? and CCCLIENT=?" ;
            return jdbcTemplate.update(sql,h_arrive,h_depart, code,code_client);
        }

        @Override
        public int onInfoSaisiLV(String sessionId, String code, int remplissage, int cpt_arrive,int cpt_depart,int saisie) {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
            String sql = "update xCH_LIVRAISON set xLIVREMPLI=?,xLIVCPTARRIVE=?,xLIVCPTDEPART=?,xLIVSAISIE=?,xLIVDATESAISIE=getdate()  where xLIV_CODE=?" ;
            return jdbcTemplate.update(sql,remplissage,cpt_arrive,cpt_depart,saisie, code);
        }

    @Override
    public int onSupprLV(String sessionId, String code,String liv,String raison,String user_annul) {
        try {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
            String sql = "exec v_bp_supprCmdLV_web ?,?,?,?";
            return jdbcTemplate.update(sql, code,liv,raison,user_annul);
        } catch (Exception e) {
            e.printStackTrace(); // 🔥 important pour debug
            throw e;
        }
    }


  /*      @Override
        public List<attrib_axe> listattribAxe(String sessionId ,String code_client) {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
            String sql="exec x_listattribAxe ?";
            return jdbcTemplate.query(sql,new attrib_axeMapper(),code_client);
        } */


        @Override
        public List<List<Object>> listattribAxe(String sessionId, String code_client) {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

            jdbcTemplate.execute("USE Equagestion");

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withSchemaName("dbo")
                    .withProcedureName("x_listattribAxe")
                    .declareParameters(new SqlParameter("code_client", Types.VARCHAR))
                    .returningResultSet("result1", new attrib_axeMapper())
                    .returningResultSet("result2", new mode_livMapper())
                    .returningResultSet("result3", new axe_ListMapper())
                    .returningResultSet("result4", new classification_listMapper());

            Map<String, Object> inParams = new HashMap<>();
            inParams.put("code_client", code_client);

            Map<String, Object> results = jdbcCall.execute(inParams);

            List<List<Object>> resultList = new ArrayList<>();
            resultList.add((List<Object>) results.get("result1"));
            resultList.add((List<Object>) results.get("result2"));
            resultList.add((List<Object>) results.get("result3"));
            resultList.add((List<Object>) results.get("result4"));
            return resultList;
        }


        @Override
        public int onUpdateAxe(String sessionId, int seq ,String axe) {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
            String sql = "UPDATE FADR SET ADRAXE = ? WHERE ADRSEQ = ? " ;
            return jdbcTemplate.update(sql,axe,seq);
        }

        @Override
        public int onUpdateModeLiv(String sessionId, int seq ,String mode_livraison) {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
            String sql = "UPDATE FADR SET ADRMODELIVR = ? WHERE ADRSEQ = ? " ;
            return jdbcTemplate.update(sql,mode_livraison,seq);
        }

        @Override
        public TestAvAddCmdLiv ontestAvAddCmdLiv(String sessionId, String cmd, int isCc) {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

            String sql = "exec v_bp_test_AvAddCmdLiv ?, ?";

            return jdbcTemplate.queryForObject(
                    sql,
                    new TestAvAddCmdLivMapper(),
                    cmd,
                    isCc
            );
        }

        @Override
        public int onAddCmdLiv(String sessionId, String cmd ,String liv,String raison) {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
            String sql = "exec v_bp_updateLivCmd ?,?,?" ;
            return jdbcTemplate.update(sql,cmd,liv,raison);
        }

    @Override
    public List<Situation_axe> situationparAxe(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "exec v_bp_situation_liv";
        return jdbcTemplate.query(sql, new Situation_axeMapper());
    }

    @Override
    public List<Chauf> onListChauf(String sessionId, String  type) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String baseSql = "exec Equagestion..x_Voit_Dispo_Livraison ?";

            return jdbcTemplate.query(baseSql, new ChaufMapper(), type);
        }


    @Override
    public List<ListAtteLiv> onListAtteLiv(String sessionId, String mode) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("exec x_Commande_a_Livrer ?",
                new ListAtteLivMapper(), mode);
    }

    @Override
    public boolean isLiv(String sessionId, String CC) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "SELECT COUNT(*) FROM FCC2 WHERE CC2CODE = ? AND ISNULL(CC2LIV_SEQ,0) = 0";

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, CC);

        // Si count > 0 → il y a des lignes où CC2LIV_SEQ = 0 → non livré
        return count != null && count > 0;
    }

    @Override
    public boolean isAtteLiv(String sessionId, String CC) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "select count(*) from VIEW_COMMANDE_ATTE_LIV where CODE=?";

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, CC);

        // Si count > 0 → il y a des lignes où CC2LIV_SEQ = 0 → non livré
        return count != null && count > 0;
    }

    @Override
    public createlv onCreateLv(String sessionId, String chauf, String voit,int etat,String course,String livreur,String axe) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = "exec v_bp_createLv ?, ?,?,?,?,?";

        return jdbcTemplate.queryForObject(
                sql,
                new CreatelvMapper(),
                chauf,voit,etat,course,livreur,axe

        );
    }

    @Override
    public int updateLivCmd(String sessionId,
                            String cmd,
                            String codeLiv) {

        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = "EXEC v_bp_updateLivCmd ?, ?";

        return jdbcTemplate.update(sql, cmd, codeLiv);
    }


    @Override
    public Map<String, Object> onIndicatChauf(String sessionId) {

        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String storedProcedureCall = "exec x_PiloteLivraison_Chauf_ERPO";

        Map<String, Object> result = new HashMap<>();

        List<String> columnNames = new ArrayList<>();
        List<Map<String, Object>> dataResult = new ArrayList<>();

        try {

            dataResult = jdbcTemplate.query(storedProcedureCall, (ResultSet rs) -> {

                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                // 🔹 colonnes (une seule fois)
                for (int i = 1; i <= columnCount; i++) {
                    columnNames.add(metaData.getColumnName(i));
                }

                List<Map<String, Object>> rows = new ArrayList<>();

                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();

                    for (int i = 1; i <= columnCount; i++) {
                        Object value = rs.getObject(i);
                        row.put(metaData.getColumnName(i), value != null ? value : 0);
                    }

                    rows.add(row);
                }

                return rows;

            });

            // 🔹 résultat final propre
            result.put("columnNames", columnNames);
            result.put("dataResult", dataResult);

        } catch (Exception e) {
            e.printStackTrace();
            result.put("error", e.getMessage());
        }

        return result;
    }

    @Override
    public List<ListCmdLivAsuppr> onSupprLVCmd(String sessionId, String liv,String client) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("select FACODE,FACC from FFA inner join FCC2 on CC2CODE=FACC where CC2_CODE_LIVRAISON=? and FACL=?",
                new ListCmdLivAsupprMapper(), liv,client);
    }

    @Override
    public List<LoginPlus> onLivreurOccupe(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "select xLIV_LIVREUR from xCH_LIVRAISON where xLIV_DATE>=current_date() and xLIV_TYPE=1 and xLIV_RET is null and isnull(xLIV_LIVREUR,'')<>''";

        return jdbcTemplate.query(sql, new LivreurOccupeMapper());
    }

    @Override
    public List<proximite> onlistProximite(String sessionId,String type) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "select PRCODE,PRCOM,PR_MOD_LIV,PR_DUREE,PR_TYPE from FPR where PR_TYPE=? and PRDESAC=0 order by PRCODE";

        return jdbcTemplate.query(sql, new ProximiteMapper(),type);
    }

    @Override
    public List<detail_editionLv> detailEditionLv(String sessionId, String mode, String sit_com, String axe) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "exec x_detailCommandeAxe ?, ?, ?";
        return jdbcTemplate.query(sql, new Object[]{mode, sit_com, axe}, new detail_editionLvMapper());
    }

    @Override
    public List<VoitureCh> onVoitureCh(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("select rtrim(xNUM_VOIT),rtrim(xCHAUFF),xDISPO,case when xDISPO=0 then 'Disponible' else 'Non Disponible' end,xCode,xType_livraison,xType_Service,xVOIT_CH.xSeq from xVOIT_CH left join xVOITURE on xNUM=xNUM_VOIT\n" +
                        "where xEtat=1 order by xNUM_VOIT,xCHAUFF",
                new VoitureChMapper());
    }

    @Override
    public int onInsertVoitCh(String sessionId, String voit, String chauf) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "insert into xVOIT_CH(xNUM_VOIT, xCHAUFF,  xDISPO) values (?,?,0)";
        return jdbcTemplate.update(sql, voit,chauf);
    }

    @Override
    public int onDelVoitCh(String sessionId,String seq) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String sql="delete from xVOIT_CH where xSeq=? and xDISPO=0";
        return jdbcTemplate.update(sql,new BigDecimal(seq));
    }

    @Override
    public boolean onTestDispo(String sessionId, String voit,String chauf) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "SELECT COUNT(*) FROM xVOIT_CH WHERE xDISPO=1 and (rtrim(xNUM_VOIT)=? or rtrim(xCHAUFF)=?) ";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, voit,chauf);
        return count != null && count > 0;
    }
}
