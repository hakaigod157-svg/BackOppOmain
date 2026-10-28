package com.opham.prepa.service;

import com.opham.prepa.Utils.Convert;
import com.opham.prepa.Utils.UserSessionManager;
import com.opham.prepa.mapper.Amm.*;
import com.opham.prepa.mapper.genererBP.ArticleCmdMapper;
import com.opham.prepa.mapper.genererBP.DepotCmdMapper;
import com.opham.prepa.mapper.genererBP.ListeCmdMapper;
import com.opham.prepa.mapper.genererBP.RayonCmdMapper;
import com.opham.prepa.model.Amm.*;
import com.opham.prepa.repository.Amm.AmmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.util.*;

@Repository
    public class AmmService implements AmmRepository {
        private UserSessionManager userSessionManager;
        @Autowired
        public void AmmService(UserSessionManager userSessionManager) {
            this.userSessionManager = userSessionManager;
        }

        @Override
        public List<fabricant> findFabricant(String sessionId, String libelle) {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
            return jdbcTemplate.query("exec AMM_OPHAM..x_Liste_Fabricant ?",
                    new FabricantMapper(),libelle);
        }

        @Override
        public int updateLibelleFabricant(String sessionId, String libelle_update ,String code_update) {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
            String sql = "update AMM_OPHAM..AMM_FABRICANT set FAB_LIBELLE= ? where FAB_CODE = ?";
            return jdbcTemplate.update(sql,libelle_update,code_update);
        }

        @Override
        public int insertFabricant(String sessionId, String codeFab, String libelleFab) {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
            String sql = "INSERT INTO AMM_OPHAM..AMM_FABRICANT (FAB_CODE, FAB_LIBELLE) VALUES (?,?)";
            return jdbcTemplate.update(sql, codeFab,libelleFab);
        }

       /* @Override
        public List<dossier> findDossier(String sessionId,String labo) {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
            return jdbcTemplate.query("exec AMM_OPHAM..x_Liste_dossier ?",
                    new DossierMapper(),labo);
        } */

    @Override
    public List<dossier> findDossier(String sessionId, String labo) {

        JdbcTemplate jdbcTemplate =
                userSessionManager.getUserJdbcTemplate(sessionId);

        long start = System.currentTimeMillis();

        System.out.println(">>> Début x_Liste_dossier");

        List<dossier> result = jdbcTemplate.query(
                "exec AMM_OPHAM..x_Liste_dossier ?",
                new DossierMapper(),
                labo
        );

        long end = System.currentTimeMillis();

        System.out.println(">>> Fin x_Liste_dossier");
        System.out.println(">>> Temps SQL + mapping : " + (end - start) + " ms");
        System.out.println(">>> Nombre de dossiers : " + result.size());

        return result;
    }




  /**Si double résultat*/
 /*   @Override
    public List<List<Object>> findDossier(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("AMM_OPHAM..x_Liste_dossier")
                .returningResultSet("result1", new DossierMapper())
                .returningResultSet("result2", new DossierMapper());

        Map<String, Object> results = jdbcCall.execute();

        // On récupère directement la liste mappée
        List<Object> result1 = (List<Object>) results.get("result1");
        List<Object> result2 = (List<Object>) results.get("result2");

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add(result1);
        resultList.add(result2);
        return resultList;
    } */


        @Override
        public int insertLigneDossier(String sessionId, String codeAmm, String codeArticle, String libelle, String focode ,String fab_code) {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
            String sql = """
            INSERT INTO AMM_OPHAM..AMM_LIGNE_ARTICLE(LIGNE_AMM_DS_CODE, LIGNE_CODE_ARTICLE, LIGNE_LIBELLE_ARTICLE,LIGNE_TITULAIRE,LIGNE_FABRICANT)
            VALUES (?, ?, ?, ? ,?)
        """;
            return jdbcTemplate.update(sql, codeAmm, codeArticle, libelle ,focode,fab_code);
        }

      /*  @Override
        public int insertAmmDossier(String sessionId, String codeAmm, String responsable, String type, Integer status) {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
            String sql = "INSERT INTO AMM_OPHAM..AMM_DOSSIER (AMM_DS_CODE, AMM_RESPO ,AMM_TYPE, AMM_DECISION) VALUES (?,?,?,?)";
            return jdbcTemplate.update(sql, codeAmm,responsable,type ,status);
        } */

            @Override
            public int insertAmmDossier(String sessionId, String codeAmm, String responsable, String type, String classification,String ref_variation) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                String sql = "INSERT INTO AMM_OPHAM..AMM_DOSSIER (AMM_DS_CODE, AMM_RESPO ,AMM_TYPE,AMM_CLASSIFICATION,AMM_REF_VARIATION) VALUES (?,?,?,?,?)";
                return jdbcTemplate.update(sql, codeAmm,responsable,type,classification,ref_variation);
            }

     /*       @Override
            public List<List<Object>> findnumdossier(String sessionId ,String prefixe, String nom_table) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

                SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                        .withProcedureName("AMM_OPHAM..x_nouveauCode")
                        .declareParameters(new SqlParameter("prefixe", Types.VARCHAR))
                        .declareParameters(new SqlParameter("nom_table", Types.VARCHAR))
                        .returningResultSet("result1", new ListnumdossierMapper());


                Map<String, Object> results = jdbcCall.execute();

                // On récupère directement la liste mappée
                List<Object> result1 = (List<Object>) results.get("result1");

                List<List<Object>> resultList = new ArrayList<>();
                resultList.add(result1);
                return resultList;
            } */

    @Override
    public List<List<Object>> findnumdossier(String sessionId, String prefixe, String nom_table) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("AMM_OPHAM..x_nouveauCode")
                // 🔹 Empêche Spring d'essayer de lire les métadonnées (car Sybase ne les expose pas toujours)
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("@prefixe", Types.VARCHAR),
                        new SqlParameter("@nom_table", Types.VARCHAR)
                )
                .returningResultSet("result1", new ListnumdossierMapper());

        // 🔹 On prépare les paramètres d'entrée
        Map<String, Object> inParams = new HashMap<>();
        inParams.put("@prefixe", prefixe);
        inParams.put("@nom_table", nom_table);

        // 🔹 Exécution avec les paramètres
        Map<String, Object> results = jdbcCall.execute(inParams);

        // 🔹 Récupération du ResultSet
        List<Object> result1 = (List<Object>) results.get("result1");

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add(result1);
        return resultList;
    }



    @Override
            public List<ListTitulaire> findTitulaire(String sessionId) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                return jdbcTemplate.query("select FOCODE,FONOM from Equagestion..FFO where FOMARQUE=1 and FOCODE<>'---'",
                        new ListTitulaireMapper());
            }

            @Override
            public int updateDossier(String sessionId, String dossierCode ,String type_update ,String respo_update, String class_update) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                String sql = "update AMM_OPHAM..AMM_DOSSIER set AMM_TYPE= ?, AMM_RESPO = ? ,AMM_CLASSIFICATION= ? where AMM_DS_CODE= ?";
                return jdbcTemplate.update(sql,type_update,respo_update,class_update,dossierCode);
            }


            @Override
            public List<List<Object>> findArticleDetail(String sessionId, String dossier) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

                jdbcTemplate.execute("USE AMM_OPHAM");

                SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                        .withSchemaName("dbo")
                        .withProcedureName("x_DetailDossier")
                        .declareParameters(new SqlParameter("dossier", Types.VARCHAR))
                        .returningResultSet("result1", new articleDetailMapper())
                        .returningResultSet("result2", new etapeDetailMapper())
                        .returningResultSet("result3", new dossierAfournirMapper())
                        .returningResultSet("result4", new histoDetailMapper())
                        .returningResultSet("result5", new reclamationDetailMapper())
                        .returningResultSet("result6", new variationDetailMapper());

                Map<String, Object> inParams = new HashMap<>();
                inParams.put("dossier", dossier);

                Map<String, Object> results = jdbcCall.execute(inParams);

                List<List<Object>> resultList = new ArrayList<>();
                resultList.add((List<Object>) results.get("result1"));
                resultList.add((List<Object>) results.get("result2"));
                resultList.add((List<Object>) results.get("result3"));
                resultList.add((List<Object>) results.get("result4"));
                resultList.add((List<Object>) results.get("result5"));
                resultList.add((List<Object>) results.get("result6"));

                return resultList;
            }

            @Override
            public List<etapeList> findEtape(String sessionId , String ammCode) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                return jdbcTemplate.query("select ETAPE_DOSSIER FROM AMM_OPHAM..AMM_ETAPE where ETAPE_DOSSIER NOT IN (SELECT distinct ETAPE FROM AMM_OPHAM..AMM_DOSSIER_ETAPE WHERE DOSSIER = ?)",
                        new etapeListMapper(),ammCode);
            }

            @Override
            public int insertAmmEtape(String sessionId, String codeAmm, String Etape, int Etat , Date dateNew) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                String sql = "INSERT INTO AMM_OPHAM..AMM_DOSSIER_ETAPE (DOSSIER,ETAPE,ETAT,DATE_ETAPE) values (?,?,?,?)";
                return jdbcTemplate.update(sql, codeAmm,Etape,Etat,dateNew);
            }

            @Override
            public List<fournirList> findFournir(String sessionId , String ammCode) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                return jdbcTemplate.query("SELECT DOSSIER_CODE,DOSSIER_LIB FROM AMM_OPHAM..AMM_DOSSIER_FOURNIR WHERE DOSSIER_CODE NOT IN (SELECT distinct LIGNE_DOSSIER_CODE FROM AMM_OPHAM..AMM_LIGNE_DOSSIER where LIGNE_AMM_CODE = ?)",
                        new fournirListMapper(),ammCode);
            }

            @Override
            public int insertFournir(String sessionId, String codeAmm, String codeDossier, Date date_fournir) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                String sql = "INSERT INTO AMM_OPHAM..AMM_LIGNE_DOSSIER (LIGNE_AMM_CODE,LIGNE_DOSSIER_CODE,LIGNE_DATE) values (?,?,?)";
                return jdbcTemplate.update(sql, codeAmm,codeDossier,date_fournir);
            }

            @Override
            public int updateEtape(String sessionId, Date dateUpdate,String ds_code,String etape) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                String sql = "update AMM_OPHAM..AMM_DOSSIER_ETAPE set DATE_ETAPE=? where DOSSIER = ? and ETAPE = ?";
                return jdbcTemplate.update(sql,dateUpdate,ds_code,etape);
            }

            @Override
            public int insertHisto(String sessionId, String codeAmm, Date date, String commentaire) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                String sql = """
                INSERT INTO AMM_OPHAM..AMM_HISTO (AMM_HISTO_DOSSIER,AMM_HISTO_DATE,AMM_HISTO_COMMENT) values(?,?,?)
                """;
                return jdbcTemplate.update(sql, codeAmm, date, commentaire);
            }

            @Override
            public int updateFournir(String sessionId, Date dateUpdate,String ds_code,String dossierLcode) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                String sql = "update AMM_OPHAM..AMM_LIGNE_DOSSIER set LIGNE_DATE=? where LIGNE_AMM_CODE=? and LIGNE_DOSSIER_CODE = ?";
                return jdbcTemplate.update(sql,dateUpdate,ds_code,dossierLcode);
            }

            @Override
            public List<AllDossierList> findalllistdossier(String sessionId ) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                return jdbcTemplate.query("SELECT DOSSIER_CODE,DOSSIER_LIB FROM AMM_OPHAM..AMM_DOSSIER_FOURNIR",
                        new AllDossierListMapper());
            }

            @Override
            public int insertdocfournir(String sessionId, String dossier_code, String dossier_lib) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                String sql = "INSERT INTO AMM_OPHAM..AMM_DOSSIER_FOURNIR (DOSSIER_CODE,DOSSIER_LIB) VALUES(?,?)";
                return jdbcTemplate.update(sql, dossier_code,dossier_lib);
            }

            @Override
            public int updateAmmDossier(String sessionId,String ds_code,int decision,Date date,String text) {

                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                String sql = "UPDATE AMM_OPHAM..AMM_DOSSIER set AMM_DECISION = ?,AMM_DATE_DECISION= ?,AMM_MOTIF_DECISION= ? where AMM_DS_CODE = ?";
                return jdbcTemplate.update(sql,decision,date,text,ds_code);
            }

            @Override
            public int insertDossierDefaut(String sessionId, String type_dossier, String code_dossier, String classification) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                String sql = "INSERT INTO AMM_OPHAM..AMM_DOSSIER_DEFAUT (TYPE_DOSSIER,CODE_DOSSIER,CLASSIFICATION) VALUES (?,?,?)";
                return jdbcTemplate.update(sql, type_dossier, code_dossier,classification);
            }

            @Override
            public List<allListGen> findalllistgen(String sessionId ) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                //return jdbcTemplate.query("SELECT TYPE_DOSSIER,CODE_DOSSIER,CLASSIFICATION FROM AMM_OPHAM..AMM_DOSSIER_DEFAUT where CLASSIFICATION = 'GENERIQUE'",
                return jdbcTemplate.query("SELECT d.TYPE_DOSSIER,d.CODE_DOSSIER,f.DOSSIER_LIB,d.CLASSIFICATION FROM AMM_OPHAM..AMM_DOSSIER_DEFAUT d JOIN AMM_OPHAM..AMM_DOSSIER_FOURNIR f ON d.CODE_DOSSIER = f.DOSSIER_CODE WHERE d.CLASSIFICATION ='GENERIQUE'",
                        new allListGenMapper());
            }

            @Override
            public List<allListSpe> findalllistspe(String sessionId ) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                //return jdbcTemplate.query("SELECT TYPE_DOSSIER,CODE_DOSSIER,CLASSIFICATION FROM AMM_OPHAM..AMM_DOSSIER_DEFAUT where CLASSIFICATION = 'SPECIALITE'",
                return jdbcTemplate.query("SELECT d.TYPE_DOSSIER,d.CODE_DOSSIER,f.DOSSIER_LIB,d.CLASSIFICATION FROM AMM_OPHAM..AMM_DOSSIER_DEFAUT d JOIN AMM_OPHAM..AMM_DOSSIER_FOURNIR f ON d.CODE_DOSSIER = f.DOSSIER_CODE WHERE d.CLASSIFICATION ='SPECIALITE'",
                new allListSpeMapper());
            }

            @Override
            public void deleteFournir(String sessionId,String dossier_code) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                String sql="delete from AMM_OPHAM..AMM_DOSSIER_FOURNIR where DOSSIER_CODE = ?";
                jdbcTemplate.update(sql,dossier_code);
            }

            @Override
            public void deleteDossierDefaut(String sessionId,String classification,String ds_code ,String type_dossier) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                String sql="delete from AMM_OPHAM..AMM_DOSSIER_DEFAUT where CODE_DOSSIER = ? and CLASSIFICATION = ? and TYPE_DOSSIER = ?";
                jdbcTemplate.update(sql,ds_code,classification,type_dossier);
            }

            @Override
            public List<motifDetail>findMotif(String sessionId , String ammCode) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                //return jdbcTemplate.query("SELECT AMM_DS_CODE,AMM_RESPO,AMM_TYPE,AMM_DECISION,AMM_CLASSIFICATION,AMM_DATE_DECISION,AMM_MOTIF_DECISION FROM AMM_OPHAM..AMM_DOSSIER WHERE AMM_DS_CODE = ? and AMM_DECISION in (2,3)",
                return jdbcTemplate.query("SELECT AMM_DS_CODE,AMM_RESPO,AMM_TYPE,AMM_DECISION,AMM_CLASSIFICATION,AMM_DATE_DECISION,AMM_MOTIF_DECISION FROM AMM_OPHAM..AMM_DOSSIER WHERE AMM_DS_CODE = ?",
                new motifDetailMapper(),ammCode);
            }


            @Override
            public List<listnumDS> findnumDS(String sessionId ,String dossier, int etat) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                return jdbcTemplate.query("exec AMM_OPHAM..x_CreateCodeTable ?,?",
                        new listnumDSMapper(),dossier,etat);
            }

            @Override
            public List<listnumFB> findnumFB(String sessionId ,String dossier, int etat) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                return jdbcTemplate.query("exec AMM_OPHAM..x_CreateCodeTable ?,?",
                        new listnumFBMapper(),dossier,etat);
            }

            @Override
            public List<typeReclam> findTypeReclam(String sessionId) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                return jdbcTemplate.query("SELECT * FROM AMM_OPHAM..AMM_TYPE_RECLAMATION",
                        new typeReclamMapper());
            }

            @Override
            public int insertReclam(String sessionId, String code_ds, String reclamation_code, Date date_reclam ,String nbre_reclam) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                String sql = "INSERT INTO AMM_OPHAM..AMM_RECLAMATION (AMM_RECL_DOSSIER,AMM_RECL_RECL,AMM_DATE_NBRE_RECLAM,AMM_RECL_NOMBRE) values (?,?,?,?)";
                return jdbcTemplate.update(sql, code_ds, reclamation_code,date_reclam,nbre_reclam);
            }

            @Override
            public int updateammreclam(String sessionId, String ds_code ,Date date_reclam) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                String sql = "UPDATE AMM_OPHAM..AMM_DOSSIER set AMM_RECLAM = 1 , AMM_DATERECLAM = ? where  AMM_DS_CODE = ?";
                return jdbcTemplate.update(sql,date_reclam,ds_code);
            }

            @Override
            public List<reclamationDetail> findDetailReclam(String sessionId , String ammCode) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                return jdbcTemplate.query("SELECT r.AMM_RECL_DOSSIER,r.AMM_RECL_RECL,r.AMM_RECL_DATE,t.LIB_RECL FROM AMM_OPHAM..AMM_RECLAMATION r LEFT JOIN AMM_OPHAM..AMM_TYPE_RECLAMATION t ON r.AMM_RECL_RECL = t.TYPE_RECL_CODE WHERE r.AMM_RECL_DOSSIER =?",
                        new reclamationDetailMapper(),ammCode);
            }


            @Override
            public List<list_amm_dossier> findlistAmmDossier(String sessionId , String ammCode) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                return jdbcTemplate.query("SELECT AMM_DS_CODE,AMM_RECLAM,AMM_TYPE,AMM_REF_VARIATION FROM AMM_OPHAM..AMM_DOSSIER WHERE AMM_DS_CODE = ?",
                        new list_amm_dossierMapper(),ammCode);
            }


     /*       public int updateReclam(String sessionId, Date dateUpdate,String ds_code ,String code_lib, int annule_reclam) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                String sql = "update AMM_OPHAM..AMM_RECLAMATION set AMM_RECL_DATE = ? ,AMM_RECL_ANNUL = ? from AMM_OPHAM..AMM_RECLAMATION r join AMM_OPHAM..AMM_TYPE_RECLAMATION t on t.TYPE_RECL_CODE = r.AMM_RECL_RECL where r.AMM_RECL_DOSSIER = ? and t.LIB_RECL = ?";

                return jdbcTemplate.update(sql,dateUpdate,annule_reclam,ds_code,code_lib);
            } */
            @Override
            public int updateReclam(String sessionId, Date dateUpdate, String ds_code, String code_lib, Integer annule_reclam) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                if (annule_reclam == null) {
                    // Mise à jour uniquement de la date
                    String sql = "UPDATE AMM_OPHAM..AMM_RECLAMATION " +
                            "SET AMM_RECL_DATE = ? " +
                            "FROM AMM_OPHAM..AMM_RECLAMATION r " +
                            "JOIN AMM_OPHAM..AMM_TYPE_RECLAMATION t ON t.TYPE_RECL_CODE = r.AMM_RECL_RECL " +
                            "WHERE r.AMM_RECL_DOSSIER = ? AND t.LIB_RECL = ?";
                    return jdbcTemplate.update(sql, dateUpdate, ds_code, code_lib);
                } else {
                    // Mise à jour date + annule
                    String sql = "UPDATE AMM_OPHAM..AMM_RECLAMATION " +
                            "SET AMM_RECL_DATE = ?, AMM_RECL_ANNUL = ? " +
                            "FROM AMM_OPHAM..AMM_RECLAMATION r " +
                            "JOIN AMM_OPHAM..AMM_TYPE_RECLAMATION t ON t.TYPE_RECL_CODE = r.AMM_RECL_RECL " +
                            "WHERE r.AMM_RECL_DOSSIER = ? AND t.LIB_RECL = ?";
                    return jdbcTemplate.update(sql, dateUpdate, annule_reclam, ds_code, code_lib);
                }
            }


            @Override
            public List<ListRespo> findListRespo(String sessionId) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                return jdbcTemplate.query("SELECT * FROM AMM_OPHAM..AMM_RESPO WHERE isnull(ACTIF,0)=0",
                        new ListRespoMapper());
            }

            /*@Override
            public int updateAmmLigneArticle(String sessionId,String ds_code,String num_Amm,Date date_Amm,int seq) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                String sql = "UPDATE AMM_OPHAM..AMM_LIGNE_ARTICLE set LIGNE_NUM_AMM = ?, LIGNE_DATE_AMM=? where SEQ = ?  and LIGNE_AMM_DS_CODE=?";
                return jdbcTemplate.update(sql,num_Amm,date_Amm,seq,ds_code);
            } */

    @Override
    public int updateAmmLigneArticle(String sessionId, String ds_code, String num_Amm, Date date_Amm, int seq) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = "UPDATE AMM_OPHAM..AMM_LIGNE_ARTICLE " +
                "SET LIGNE_NUM_AMM = ?, LIGNE_DATE_AMM = ? " +
                "WHERE SEQ = ? AND LIGNE_AMM_DS_CODE = ?";

        return jdbcTemplate.update(sql, ps -> {
            // num_Amm : si null, setNull
            if (num_Amm != null) {
                ps.setString(1, num_Amm);
            } else {
                ps.setNull(1, java.sql.Types.VARCHAR);
            }
            // date_Amm : si null, setNull
            if (date_Amm != null) {
                ps.setDate(2, new java.sql.Date(date_Amm.getTime()));
            } else {
                ps.setNull(2, java.sql.Types.DATE);
            }

            // seq et ds_code
            ps.setInt(3, seq);
            ps.setString(4, ds_code);
        });
    }

           @Override
            public List<ListArticleLier> findListArticleLier(String sessionId ,String labo , int type) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                return jdbcTemplate.query("exec AMM_OPHAM..x_ListeArticleAlier ?,?",
                        new ListArticleLierMapper(),labo ,type);
            }


        /*    @Override
            public List<List<Object>> findListArticleLier(String sessionId, String labo, int type) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

                jdbcTemplate.execute("USE AMM_OPHAM");

                SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                        .withSchemaName("dbo")
                        .withProcedureName("x_ListeArticleAlier")
                        .declareParameters(
                                new SqlParameter("labo", Types.VARCHAR),
                                new SqlParameter("type", Types.INTEGER)
                        )
                        .returningResultSet("result1", new ListArticleLierMapper())
                        .returningResultSet("result2", new ListArticleLierMapper());

                Map<String, Object> inParams = new HashMap<>();
                inParams.put("labo", labo);
                inParams.put("type", type);

                Map<String, Object> results = jdbcCall.execute(inParams);

                List<List<Object>> resultList = new ArrayList<>();
                resultList.add((List<Object>) results.get("result1"));
                resultList.add((List<Object>) results.get("result2"));


                return resultList;
            } */


            @Override
            public int updateCodeArticle(String sessionId,String ds_code,String codeArticle,int seq) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                String sql = "UPDATE AMM_OPHAM..AMM_LIGNE_ARTICLE set LIGNE_CODE_ARTICLE = ? where SEQ = ? and LIGNE_AMM_DS_CODE = ?";
                return jdbcTemplate.update(sql,codeArticle,seq,ds_code);
            }


            @Override
            public int insertLigneVariation(String sessionId, String codeAmm,String libelle_variation) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                String sql = "INSERT INTO AMM_OPHAM..AMM_LIGNE_VARIATION (VAR_DOSSIER, VAR_LIBELLE) VALUES (?,?)";
                return jdbcTemplate.update(sql, codeAmm,libelle_variation);
            }

            @Override
            public int insertVariation(String sessionId, String codeAmm, String libelle_variation) {
                JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
                String sql = """
                        INSERT INTO AMM_OPHAM..AMM_LIGNE_VARIATION (VAR_DOSSIER,VAR_LIBELLE) values (?,?)
                        """;
                return jdbcTemplate.update(sql,codeAmm,libelle_variation);
            }

    @Override
    public boolean importMoisExist(String sessionId,int date,int mois) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "select count(*) from AMM_OPHAM..AMM_LISTE_MIDAC where LM_ANNEE=? and LM_MOIS=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, date,mois);
        return count != null && count > 0;
    }

    @Override
    public int import_midac(String sessionId, Midac t) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        System.out.println(t);
        String sql = """
                    INSERT INTO AMM_OPHAM..AMM_LISTE_MIDAC (
                        LM_NOM_COMMERCIAL,
                        LM_CODE_PROD,
                        LM_CODE_CIP,
                        LM_DCI,
                        LM_MARQUE,
                        LM_PRESENTATION,
                        LM_FABRIQUANT,
                        LM_TITULAIRE_AMM,
                        LM_CODE_HS,
                        LM_TYPE_COLIS,
                        LM_DATE_AMM,
                        LM_NUM_AMM,
                        LM_STATUS,
                        LM_MOIS,
                        LM_ANNEE
                    )
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        return jdbcTemplate.update(sql,
                Convert.toIso1(t.getNom_com()),      // LM_NOM_COMMERCIAL
                Convert.toIso1(t.getCode_prod()),    // LM_CODE_PROD
                Convert.toIso1(t.getCode_cip()),     // LM_CODE_CIP
                Convert.toIso1(t.getDci()),          // LM_DCI
                Convert.toIso1(t.getMarque()),       // LM_MARQUE
                Convert.toIso1(t.getPresentation()), // LM_PRESENTATION
                Convert.toIso1(t.getFabriquant()),   // LM_FABRIQUANT
                Convert.toIso1(t.getTitulaire()),    // LM_TITULAIRE_AMM
                Convert.toIso1(t.getCode_hs()),      // LM_CODE_HS
                Convert.toIso1(t.getType_colis()),   // LM_TYPE_COLIS
                t.getDate_amm(),     // LM_DATE_AMM (type Date)
                Convert.toIso1(t.getNum_amm()),      // LM_NUM_AMM
                Convert.toIso1(t.getStatus()),       // LM_STATUS
                t.getVmois(),        // LM_MOIS
                t.getVdate()         // LM_ANNEE
        );
    }

      /*  @Override
        public List<Midac> findlistMidac(String sessionId) {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
            return jdbcTemplate.query("exec AMM_OPHAM..x_ListeMIDAC",
                    new MidacMapper());
        } */

    @Override
    public List<List<Object>> findlistMidac(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                //.withProcedureName("AMM_OPHAM..x_ListeMIDAC")
                .withProcedureName("AMM_OPHAM..x_ListeMIDAC_2")
                // 🔹 Empêche Spring d'essayer de lire les métadonnées (car Sybase ne les expose pas toujours)
                .withoutProcedureColumnMetaDataAccess()
                .returningResultSet("result1", new MidacMapper())
                .returningResultSet("result2", new MidacLastImportMapper());

        Map<String, Object> results = jdbcCall.execute();

        // 🔹 Récupération du ResultSet
        List<Object> result1 = (List<Object>) results.get("result1");
        List<Object> result2 = (List<Object>) results.get("result2");

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add(result1);
        resultList.add(result2);
        return resultList;
    }


    //Service Update FAR
        @Override
        public int updateFarMidac(String sessionId, String code_pro, String codeArticle) {
            JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
            String sql = "exec AMM_OPHAM..x_Lier_Article_MIDAC ?, ?";
            return jdbcTemplate.update(sql, code_pro, codeArticle);
        }

    @Override
    public int updateImage(String sessionId, int seq, byte[] file) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "update AMM_OPHAM..AMM_LIGNE_ARTICLE set LIGNE_IMAGE = ? where SEQ = ?";
        return jdbcTemplate.update(sql, file, seq);
    }

    @Override
    public List<list_titulaire_amm> findListTitulaire(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("exec AMM_OPHAM..x_listeTitulaire",
                new list_titulaire_ammMapper());
    }

    @Override
    public int insertAmmReclam(String sessionId, String ds_code, String code_reclam, Date date_reclam, String nb_reclam) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        /*String sql = "INSERT INTO AMM_OPHAM..AMM_RECLAMATION(AMM_RECL_DOSSIER,AMM_RECL_RECL,AMM_RECL_DATE,AMM_RECL_NOMBRE) VALUES (?,?,?,?)"; */
        String sql = "INSERT INTO AMM_OPHAM..AMM_RECLAMATION(AMM_RECL_DOSSIER,AMM_RECL_RECL,AMM_DATE_NBRE_RECLAM,AMM_RECL_NOMBRE) VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql, ds_code, code_reclam,date_reclam,nb_reclam);
    }


   /* @Override
    public int updateFarMidac(String sessionId, String code_pro, String codeArticle) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("x_Lier_Article_MIDAC") // nom de la procédure
                .withSchemaName("AMM_OPHAM"); // schéma

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("code_pro", code_pro);
        inParams.put("codeArticle", codeArticle);

        // Exécute la procédure et récupère le résultat
        Map<String, Object> out = jdbcCall.execute(inParams);

        // Si ta procédure retourne le nombre de lignes affectées, tu peux le récupérer ici
        // sinon, retourne simplement 1 si exécution réussie
        return 1;
    } */


    @Override
    public List<amm_renouveler> findListArenouveler(String sessionId,int type) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("exec AMM_OPHAM..x_AMM_a_renouveler ?",
                new amm_renouvelerMapper(),type);
    }

    @Override
    public String findFicheArtWork(String sessionId, String amm_ds_code, String ligne_code_article, String ligne_libelle_article) {

        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        return jdbcTemplate.queryForObject("exec AMM_OPHAM..x_LoadImage ?, ?, ?",
                String.class, amm_ds_code, ligne_code_article, ligne_libelle_article);
    }

    public List<Abrogation> findAbrogation(String sessionId) {

        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("exec AMM_OPHAM..x_AMM_ABROGE ",
                new AbrogationMapper());
    }

   public int updateDateAbrogation(String sessionId, LocalDate dateValidation, String code ) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "update Equagestion..xFARTYPE set xAMM_STAT=5  , xAMM_DATEABROGE=? where  xARTP_ARCODE= ? ";
       Timestamp timestamp = Timestamp.valueOf(dateValidation.atStartOfDay());
        return jdbcTemplate.update(sql,timestamp,code );
    }


}

