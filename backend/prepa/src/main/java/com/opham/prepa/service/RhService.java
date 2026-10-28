package com.opham.prepa.service;

import com.opham.prepa.Utils.UserSessionManager;
import com.opham.prepa.mapper.Rh.*;
import com.opham.prepa.model.Rh.*;

import com.opham.prepa.repository.Rh.RhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.sql.Types;

@Repository
public class RhService implements RhRepository {
    UserSessionManager userSessionManager;

    @Autowired
    public void RhService(UserSessionManager userSessionManager) {
        this.userSessionManager = userSessionManager;
    }

    @Override
    public int updateCvSelection(String sessionId, String recrut, String cv) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "update DB_RH..RH_LIGNE_CV set CV_LIGNE_SELECTIONNE = 1 where CV_LIGNE_CODE_RECR = ? AND CV_LIGNE_CODE = ?";
        return jdbcTemplate.update(sql,recrut, cv);
    }
    @Override
    public int updateCvEntretien(String sessionId, String recrut, String cv) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "update DB_RH..RH_LIGNE_CV set CV_LIGNE_TEST_ENTRETIEN = 1 where CV_LIGNE_CODE_RECR = ? AND CV_LIGNE_CODE = ?";
        return jdbcTemplate.update(sql,recrut, cv);
    }

    @Override
    public int updateCvDateProposition(String sessionId, String recrut, String cv) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "update DB_RH..RH_LIGNE_CV set CV_LIGNE_SELECTIONNE = 3 ,CV_LIGNE_DATE_PROPOSITION = getDate() where CV_LIGNE_CODE_RECR = ? AND CV_LIGNE_CODE = ?";
        return jdbcTemplate.update(sql,recrut, cv);
    }

    public int onUpdatevalidation(String sessionId, String recrut, String cv, Date dateValidation, String categorie) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
       /* String sql = "update DB_RH..RH_LIGNE_CV set CV_LIGNE_VALIDATION = 1 where CV_LIGNE_CODE_RECR = ? AND CV_LIGNE_CODE = ?"; */
        String sql = "update DB_RH..RH_LIGNE_CV set CV_LIGNE_SELECTIONNE = 4 , CV_LIGNE_VALIDATION_DATE = getDate() ,CV_LIGNE_PRISE_FONCT =? ,CV_LIGNE_CATEGORIE =? where CV_LIGNE_CODE_RECR = ? AND CV_LIGNE_CODE = ?";
        return jdbcTemplate.update(sql,dateValidation,categorie,recrut, cv);
    }

    @Override
    public int updateDateCvEntretien(String sessionId, java.sql.Timestamp date, String recrut, String cv) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "UPDATE DB_RH..RH_LIGNE_CV SET CV_LIGNE_DATE_TEST = ? " +
                "WHERE CV_LIGNE_CODE_RECR = ? AND CV_LIGNE_CODE = ?";

        try {
            // Exécution de la mise à jour
            int rowsUpdated = jdbcTemplate.update(sql, date, recrut, cv);
            /*System.out.println("Nombre de lignes mises à jour : " + rowsUpdated);*/
            return rowsUpdated;
        } catch (DataAccessException e) {
            System.err.println("Erreur lors de la mise à jour de la date d'entretien : " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int onUpdatedateValidation(String sessionId, java.sql.Timestamp date, String recrut, String cv) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "UPDATE DB_RH..RH_LIGNE_CV SET CV_LIGNE_VALIDATION_DATE = ? " +
                "WHERE CV_LIGNE_CODE_RECR = ? AND CV_LIGNE_CODE = ?";

        try {
            // Exécution de la mise à jour
            int rowsUpdated = jdbcTemplate.update(sql, date, recrut, cv);
          /*  System.out.println("Nombre de lignes mises à jour : " + rowsUpdated); */
            return rowsUpdated;
        } catch (DataAccessException e) {
            System.err.println("Erreur lors de la mise à jour de la date d'entretien : " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int onUpdatedateProposition(String sessionId, java.sql.Timestamp date, String recrut, String cv) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "UPDATE DB_RH..RH_LIGNE_CV SET CV_LIGNE_DATE_PROPOSITION = ? " +
                "WHERE CV_LIGNE_CODE_RECR = ? AND CV_LIGNE_CODE = ?";

        try {
            // Exécution de la mise à jour
            int rowsUpdated = jdbcTemplate.update(sql, date, recrut, cv);
            /*  System.out.println("Nombre de lignes mises à jour : " + rowsUpdated); */
            return rowsUpdated;
        } catch (DataAccessException e) {
            System.err.println("Erreur lors de la mise à jour de la date d'entretien : " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }


    @Override
    public List<DemandePers> findAllListDemandePers(String sessionId, Integer etat) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        //  gérer null = pas de filtre
            return jdbcTemplate.query(
                    "exec DB_RH..x_ListeDemandePers @etat = ?",
                    new Object[]{etat},
                    new allListDemandePersMapper()
            );
    }

    public List<Recrutement> findAllListRecrutement(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
            return jdbcTemplate.query(
                    "exec DB_RH..x_ListeRecrutement",
                    new RecrutementMapper()
            );
    }

  /*  @Override
    public List<List<Object>> findAllDetailRecru(String sessionId, String coderecu) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = "{call DB_RH..x_DetailRecru(?)}"; // ? = paramètre @codeRecu

        List<DetailRecru> details = jdbcTemplate.query(sql,
                new Object[]{coderecu},      // ici le paramètre obligatoire
                new DetailRecruMapper());

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add(new ArrayList<>(details));

        return resultList;
    } */


    @Override
    public List<List<Object>> findAllDetailRecru(String sessionId, String coderecu)  {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        jdbcTemplate.execute("USE DB_RH");

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("dbo")
                .withProcedureName("x_DetailRecru")
                .declareParameters(new SqlParameter("coderecu", Types.VARCHAR))
                .returningResultSet("result1", new DetailRecruMapper())
                .returningResultSet("result2", new OffreMapper())
                .returningResultSet("result3", new CvMapper());

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("coderecu", coderecu);

        Map<String, Object> results = jdbcCall.execute(inParams);

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add((List<Object>) results.get("result1"));
        resultList.add((List<Object>) results.get("result2"));
        resultList.add((List<Object>) results.get("result3"));

        return resultList;
    }










    @Override
    public List<List<Object>> findAllCvRecru(String sessionId, String recrut) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = "{call DB_RH..x_ListeCV(?)}"; // le ? = paramètre @recrut

        List<CV> cvs = jdbcTemplate.query(sql,
                new Object[]{recrut},   // ici le paramètre
                new CvMapper());

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add(new ArrayList<>(cvs)); // mettre la liste dans une "List<List<Object>>"

        return resultList;
    }

    @Override
    public List<CV> findAllCvGlobal(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = "SELECT CV_CODE, CV_NOM, CV_PRENOM, CV_CONTACT1, CV_CONTACT_2" +
                ", CV_EMAIL, CV_DATE, CV_CV, CV_MOTIVATION " +
                "FROM DB_RH..RH_CV";

        // On utilise ton mapper existant
        return jdbcTemplate.query(sql, new CvGlobalMapper());
    }

    @Override
    public List<DepartementPoste> findAllDepartementPoste(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = "SELECT CODE_DEP, LIB_DEP, CODE_POSTE, POSTE_LIBELLE FROM DB_RH..listeDepartementPoste";

        // On utilise ton mapper existant
        return jdbcTemplate.query(sql, new DepartementPosteMapper());
    }
    @Override
    public List<Offre> findAllOffre(String sessionId, String coderecr) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = "SELECT OFFRE_CODE, cab.CAB_TYPE AS OFFRE_TYPE, OFFRE_DATE, OFFRE_ENTITE," +
                " OFFRE_CODE_RECR, OFFRE_PIECE FROM" +
                " DB_RH..RH_LIGNE_OFFRE ofr INNER JOIN DB_RH..RH_CABINET cab ON(ofr.OFFRE_ENTITE = cab.CAB_NOM)" +
                " WHERE OFFRE_CODE_RECR = ?";

        // On utilise ton mapper existant
        return jdbcTemplate.query(sql, new OffreMapper(),  coderecr);
    }
    @Override
    public List<Cabinet> findAllCabinet(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = "SELECT CAB_NOM FROM DB_RH..RH_CABINET";

        // On utilise ton mapper existant
        return jdbcTemplate.query(sql, new CabinetMapper());
    }

    @Override
    public List<List<Object>> findcodenouveauDemande(String sessionId, String prefixe, String table) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = "{call DB_RH..x_nouveauCodeProc(?, ?, ?)}"; // 2 IN + 1 OUT

        List<List<Object>> resultList = jdbcTemplate.execute(

                (Connection con) -> {
                    CallableStatement cs = con.prepareCall(sql);
                    cs.setString(1, prefixe); // IN param
                    cs.setString(2, table);   // IN param
                    cs.registerOutParameter(3, Types.VARCHAR); // OUT param
                    return cs;
                },
                (CallableStatement cs) -> {
                    cs.execute();
                    String code = cs.getString(3); // récupérer OUT param
                    List<Object> innerList = new ArrayList<>();
                    innerList.add(Map.of("codenouveaudemande", code));
                    List<List<Object>> outerList = new ArrayList<>();
                    outerList.add(innerList);
                    return outerList;
                }
        );

        return resultList;
    }


    @Override
    public int insererDemande(DemandePers d, byte[] fiche, String sessionId) throws IOException {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "INSERT INTO DB_RH..RH_DEMANDE " +
                "(DEMANDE_CODE, DEMANDE_DATE, DEMANDE_PRISE_FONCTION, DEMANDE_DEPARTEMENT, " +
                "DEMANDE_MOTIF, DEMANDE_CONTRAT, DEMANDE_POSTE, DEMANDE_EFFECTIF, DEMANDE_ETAT, DEMANDE_FICHE) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        java.sql.Date demandeDate = new java.sql.Date(d.getDemande_date().getTime());
        java.sql.Date priseFonction = new java.sql.Date(d.getDemande_prise_fonction().getTime());

        return jdbcTemplate.update(sql,
                d.getDemande_code(),
                demandeDate,
                priseFonction,
                d.getDemande_departement(),
                d.getDemande_motif(),
                d.getDemande_contrat(),
                d.getDemande_poste(),
                d.getDemande_effectif(),
                d.getDemande_etat(),
                fiche
        );
    }

    @Override
    public int updateDemande(DemandePers d, String sessionId) throws IOException {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "UPDATE DB_RH..RH_DEMANDE " +
                "SET DEMANDE_DATE = ?, DEMANDE_PRISE_FONCTION = ?, DEMANDE_DEPARTEMENT = ?, " +
                "DEMANDE_MOTIF = ?, DEMANDE_CONTRAT = ?, DEMANDE_POSTE = ?, DEMANDE_EFFECTIF = ?, " +
                "DEMANDE_FICHE = ?" +
                "WHERE DEMANDE_CODE = ?";

        java.sql.Date demandeDate = new java.sql.Date(d.getDemande_date().getTime());
        java.sql.Date priseFonction = new java.sql.Date(d.getDemande_prise_fonction().getTime());

        return jdbcTemplate.update(sql,
                demandeDate,
                priseFonction,
                d.getDemande_departement(),
                d.getDemande_motif(),
                d.getDemande_contrat(),
                d.getDemande_poste(),
                d.getDemande_effectif(),
                d.getDemande_fiche(),
                d.getDemande_code()
        );
    }

    @Override
    public int insererCV(CV c, byte[] cv,  byte[] lm, String sessionId) throws IOException {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "INSERT INTO DB_RH..RH_CV " +
                "(CV_CODE, CV_NOM, CV_PRENOM, CV_CONTACT1," +
                "CV_CONTACT_2, CV_EMAIL, CV_DATE, CV_CV, CV_MOTIVATION) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        java.sql.Date cv_date = new java.sql.Date(c.getCv_date().getTime());

        return jdbcTemplate.update(sql,
                c.getCv_code(),
                c.getCv_nom(),
                c.getCv_prenom(),
                c.getCv_contact1(),
                c.getCv_contact_2(),
                c.getCv_email(),
                cv_date,
                cv,
                lm
        );
    }
    @Override
    public int insererLigneCV(CV c, String sessionId) throws IOException {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "INSERT INTO DB_RH..RH_LIGNE_CV " +
                "VALUES (?, ?, 0, 0,null, null, null, null, null,getDate(),null,null,null)";

        return jdbcTemplate.update(sql,
                c.getCv_code(),
                c.getCv_recru_code()

        );
    }

    @Override
    public int insererOffre(Offre o, byte[] piece, String sessionId) throws IOException {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "INSERT INTO DB_RH..RH_LIGNE_OFFRE " +
                "VALUES (?, ?, ?, ?, ?)";
        java.sql.Date offre_date = new java.sql.Date(o.getOffre_date().getTime());
        return jdbcTemplate.update(sql,
                o.getOffre_code(),
                offre_date,
                o.getOffre_entite(),
                o.getOffre_code_recr(),
                piece
        );
    }

    @Override
    public int updateOffre(Offre o, byte[] piece, String sessionId) {

        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "UPDATE DB_RH..RH_LIGNE_OFFRE set OFFRE_DATE = ?, OFFRE_ENTITE = ?, OFFRE_PIECE = ? where OFFRE_CODE = ? AND OFFRE_CODE_RECR = ?";
        java.sql.Date offre_date = new java.sql.Date(o.getOffre_date().getTime());
        return jdbcTemplate.update(sql,
                offre_date,
                o.getOffre_entite(),
                piece,
                o.getOffre_code(),
                o.getOffre_code_recr()

        );
    }
    @Override
    public int updateCV(CV c, byte[] cv, byte[] motivation, String sessionId) {

        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "UPDATE DB_RH..RH_CV set CV_NOM = ?, CV_PRENOM = ?, CV_CONTACT1 = ?, " +
                " CV_CONTACT_2 = ?, CV_EMAIL = ?,  CV_CV = ?,  CV_MOTIVATION = ?,  CV_DATE = ? " +
                "where  CV_CODE = ?";
        java.sql.Date cv_date = new java.sql.Date(c.getCv_date().getTime());
        return jdbcTemplate.update(sql,
                c.getCv_nom() ,
                c.getCv_prenom(),
                c.getCv_contact1(),
                c.getCv_contact_2(),
                c.getCv_email(),
                cv,
                motivation,
                cv_date,
                c.getCv_code()
        );
    }

    @Override
    public int updateDemandeToApprouve(String sessionId, String codeDemande) {

        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "UPDATE DB_RH..RH_DEMANDE set DEMANDE_ETAT = 1 where DEMANDE_CODE = ?";
        return jdbcTemplate.update(sql, codeDemande);
    }

    @Override
    public int updateDemandeToRefuser(String sessionId, String codeDemande) {

        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "UPDATE DB_RH..RH_DEMANDE set DEMANDE_ETAT = 0 where DEMANDE_CODE = ?";
        return jdbcTemplate.update(sql, codeDemande);
    }

    @Override
    public int deleteOffre(String sessionId, String codeOffre, String entite, String codeRecru) {

        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "DELETE FROM DB_RH..RH_LIGNE_OFFRE WHERE OFFRE_CODE = ? AND OFFRE_ENTITE = ? AND OFFRE_CODE_RECR = ?";
        return jdbcTemplate.update(sql, codeOffre, entite, codeRecru);
    }
    @Override
    public int deleteDemande(String sessionId, String codeDemande) {

        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "DELETE FROM DB_RH..RH_DEMANDE WHERE DEMANDE_CODE = ?";
        return jdbcTemplate.update(sql, codeDemande);
    }

   /* @Override
    public int insertCVtest(String sessionId, String cv_code, String recru_code, Timestamp cv_ligne_date_test, Timestamp cv_ligne_date_entretien) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "UPDATE DB_RH..RH_LIGNE_CV set CV_LIGNE_DATE_TEST = ?, CV_LIGNE_DATE_ENTRETIEN=?,CV_LIGNE_SELECTIONNE = 1 WHERE CV_LIGNE_CODE = ? and CV_LIGNE_CODE_RECR = ?";
        return jdbcTemplate.update(sql, cv_ligne_date_test,cv_ligne_date_entretien,cv_code,recru_code);
    } */

    @Override
    public int insertCVtest(String sessionId, String cv_code, String recru_code,
                            Timestamp cv_ligne_date_test, Timestamp cv_ligne_date_entretien) {

        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = """
        UPDATE DB_RH..RH_LIGNE_CV
        SET
            CV_LIGNE_DATE_TEST =
                ISNULL(?, CV_LIGNE_DATE_TEST),
            CV_LIGNE_DATE_ENTRETIEN =
                ISNULL(?, CV_LIGNE_DATE_ENTRETIEN),
            CV_LIGNE_SELECTIONNE = 2
        WHERE
            CV_LIGNE_CODE = ?
            AND CV_LIGNE_CODE_RECR = ?
        """;

        return jdbcTemplate.update(sql, ps -> {
            if (cv_ligne_date_test != null)
                ps.setTimestamp(1, cv_ligne_date_test);
            else
                ps.setNull(1, Types.TIMESTAMP);

            if (cv_ligne_date_entretien != null)
                ps.setTimestamp(2, cv_ligne_date_entretien);
            else
                ps.setNull(2, Types.TIMESTAMP);

            ps.setString(3, cv_code);
            ps.setString(4, recru_code);
        });
    }

  /*  @Override
    public List<List<Object>> findAllListParams(String sessionId, String departement_code,String poste_code) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = "{call DB_RH..x_afficheMise_A_Dispo(?,?)}"; // le ? = paramètre @recrut

        List<CV> cvs = jdbcTemplate.query(sql,
                new Object[]{departement_code,poste_code},   // ici le paramètre
                new CvMapper());

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add(new ArrayList<>(cvs)); // mettre la liste dans une "List<List<Object>>"

        return resultList;
    } */

  /*  @Override
    public List<List<Object>> findAllListParams(
            String sessionId,
            String departement_code,
            String poste_code) {

        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = "{call DB_RH..x_afficheMise_A_Dispo(?, ?)}";

        List<paramsMiseAdispo> cvs = jdbcTemplate.query(
                con -> {
                    CallableStatement cs = con.prepareCall(sql);
                    cs.setString(1, departement_code);
                    cs.setString(2, poste_code);
                    return cs;
                },
                new paramsMiseAdispoMapper()
        );

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add(new ArrayList<>(cvs));

        return resultList;
    } */

    @Override
    public List<List<Object>> findAllListParams(
            String sessionId,
            String departement_code,
            String poste_code) {

        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        jdbcTemplate.execute("USE DB_RH");

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("dbo")
                .withProcedureName("x_afficheMise_A_Dispo")
                .declareParameters(
                        new SqlParameter("dep", Types.VARCHAR),
                        new SqlParameter("poste", Types.VARCHAR)
                )
                .returningResultSet("result1", new paramsMiseAdispoMapper());

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("dep", departement_code);
        inParams.put("poste", poste_code);

        Map<String, Object> results = jdbcCall.execute(inParams);

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add((List<Object>) results.get("result1"));

        return resultList;
    }

    @Override
    public int insert_params(String sessionId, String dep, String code_poste,String categorie,String materiel) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "INSERT INTO DB_RH..RH_MISE_A_DISPO (RH_DEP, RH_POSTE, RH_CATEGORIE, RH_MATERIEL) VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql, dep, code_poste,categorie,materiel);
    }

    @Override
    public void deleteParams(String sessionId, String dep, String code_poste,String categorie,String materiel) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "DELETE FROM DB_RH..RH_MISE_A_DISPO WHERE RH_DEP = ? AND RH_POSTE =? AND RH_CATEGORIE =? AND RH_MATERIEL=?";
        jdbcTemplate.update(sql, dep, code_poste,categorie,materiel);
    }

    @Override
    public List<List<Object>> findAllListEmployes(String sessionId, Integer etat) {

        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        jdbcTemplate.execute("USE DB_RH");

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("dbo")
                .withProcedureName("x_ListeEmploye")
                .declareParameters(
                        new SqlParameter("etat", Types.INTEGER)

                )
                .returningResultSet("result1", new EmployeMapper());

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("etat", etat);

        Map<String, Object> results = jdbcCall.execute(inParams);

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add((List<Object>) results.get("result1"));

        return resultList;
    }

    public int updateMatricule(String sessionId, String matricule, String code_recrutement) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "update DB_RH..RH_EMPLOYE set EMPL_MATRICULE = ? where EMPL_CODE = ?";
        return jdbcTemplate.update(sql,matricule,code_recrutement);
    }

    @Override
    public List<List<Object>> findAllListIntegration(String sessionId) {

        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        jdbcTemplate.execute("USE DB_RH");

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("dbo")
                .withProcedureName("x_ListeIntegration")
                .returningResultSet("result1", new IntegrationMapper());

        Map<String, Object> inParams = new HashMap<>();

        Map<String, Object> results = jdbcCall.execute(inParams);

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add((List<Object>) results.get("result1"));

        return resultList;
    }
    
    @Override
    public int insertEmploye(String sessionId, String empl_matricule, String empl_nom, String empl_prenom, String empl_date_naiss, String empl_departement, String empl_poste, String empl_contrat, String empl_prise_fonction, String empl_date_fin_essai1, String empl_date_fin_essai2) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "INSERT INTO DB_RH..RH_EMPLOYE (EMPL_MATRICULE, EMPL_NOM, EMPL_PRENOM, EMPL_DATENAISS, EMPL_CODE_CV, EMPL_DEP, EMPL_POSTE, EMPL_PRISE_FONCTION,EMPL_CONTRAT, EMPL_CODE_RECR, EMPL_DATE_FIN_ESSAI1,EMPL_DATE_FIN_ESSAI2, EMPL_ETAT) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql, empl_matricule, empl_nom, empl_prenom, empl_date_naiss, null,empl_departement, empl_poste, empl_prise_fonction, empl_contrat, null, empl_date_fin_essai1, empl_date_fin_essai2,1);
    }

    @Override
    public int updateEmploye(String sessionId, String empl_matricule, String empl_nom, String empl_prenom, String empl_date_naiss, String empl_departement, String empl_poste, String empl_contrat, String empl_prise_fonction, String empl_date_fin_essai1, String empl_date_fin_essai2) {

        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = "UPDATE DB_RH..RH_EMPLOYE SET EMPL_MATRICULE = ?,EMPL_NOM = ?,EMPL_PRENOM = ?,EMPL_DATENAISS = ?,EMPL_DEP = ?, EMPL_POSTE = ?, EMPL_PRISE_FONCTION = ?, EMPL_CONTRAT = ?, EMPL_DATE_FIN_ESSAI1 = ?, EMPL_DATE_FIN_ESSAI2 = ? WHERE EMPL_MATRICULE = ?";

        return jdbcTemplate.update(sql, empl_matricule, empl_nom, empl_prenom, empl_date_naiss, empl_departement, empl_poste, empl_prise_fonction, empl_contrat, empl_date_fin_essai1, empl_date_fin_essai2, empl_matricule);
    }

    public int deleteEmploye(String sessionId, String empl_matricule) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = "DELETE FROM DB_RH..RH_EMPLOYE WHERE EMPL_MATRICULE = ?";
        return jdbcTemplate.update(sql, empl_matricule);
    }

    @Override
    public List<List<Object>> findAllDetailIntegr(String sessionId, String codeInteg ,String matricule)  {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        jdbcTemplate.execute("USE DB_RH");

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("dbo")
                .withProcedureName("x_DetailIntegration")
                .declareParameters(
                        new SqlParameter("codeInteg", Types.VARCHAR),
                        new SqlParameter("matricule", Types.VARCHAR)
                )
                .returningResultSet("result1", new DetailIntegrationMapper())
                .returningResultSet("result2", new DetailIntegrFormationMapper())
                .returningResultSet("result3", new DetailMatDispoMapper());

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("codeInteg", codeInteg);
        inParams.put("matricule", matricule != null ? matricule : "");

        Map<String, Object> results = jdbcCall.execute(inParams);

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add((List<Object>) results.get("result1"));
        resultList.add((List<Object>) results.get("result2"));
        resultList.add((List<Object>) results.get("result3"));


        return resultList;
    }

    @Override
    public int updateDateEtape(String sessionId, String ligne_etape_code, String ligne_etape_etape, Date ligne_etape_date) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = "update DB_RH..RH_LIGNE_ETAPE set LIGNE_ETAPE_DATE= ? where LIGNE_ETAPE_CODE= ? and LIGNE_ETAPE_ETAPE = ?";

        return jdbcTemplate.update(sql, ligne_etape_date, ligne_etape_code, ligne_etape_etape);
    }

    @Override
    public int updateDateMatDispo(String sessionId, String mise_dispo_matr, String mise_dispo_cat, String mise_dispo_materiel, Date mise_dispo_date_effect) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "update DB_RH..RH_MISE_A_DISPO_LIGNE set MISE_DISPO_DATE_EFFECT = ? where MISE_DISPO_MATR  = ? and MISE_DISPO_CAT = ? and MISE_DISPO_MATERIEL = ?";

        return jdbcTemplate.update(sql, mise_dispo_date_effect, mise_dispo_matr, mise_dispo_cat, mise_dispo_materiel);
    }

    @Override
    public List<Categorie> findAllListCategorie(String sessionId) {

        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        jdbcTemplate.execute("USE DB_RH");

        String sql = """
        SELECT *
        FROM DB_RH..RH_CATEGORIE
        """;

        return jdbcTemplate.query(sql, new CategorieMapper());
    }

    @Override
    public int updateDateFinCdd(String sessionId, String demandeCode ,int moisFinCDD) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "UPDATE DB_RH..RH_RECRUT SET RECRU_ESSAI = ? WHERE RECRU_DEMANDE = ?";
        return jdbcTemplate.update(sql, moisFinCDD,demandeCode);
    }


  /*  @Override
    public List<List<Object>> findAllListParamsFormation(String sessionId, String departement_code, String poste_code) {

        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        jdbcTemplate.execute("USE DB_RH");

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("dbo")
                .withProcedureName("x_afficheForm_embauche")
                .declareParameters(
                        new SqlParameter("dep", Types.VARCHAR),
                        new SqlParameter("poste", Types.VARCHAR)
                )
                .returningResultSet("result1", new paramsFormationMapper());

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("dep", departement_code);
        inParams.put("poste", poste_code);

        Map<String, Object> results = jdbcCall.execute(inParams);

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add((List<Object>) results.get("result1"));

        return resultList;
    } */

    @Override
    public Map<String, Object> findAllListParamsFormation(String sessionId, String departement_code, String poste_code) {

        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        jdbcTemplate.execute("USE DB_RH");
        Map<String, Object> result = new HashMap<>();
        List<String> columnNames = new ArrayList<>();
        List<Map<String, Object>> dataResult = new ArrayList<>();

        try {

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withSchemaName("dbo")
                    .withProcedureName("x_afficheForm_embauche")
                    .declareParameters(
                            new SqlParameter("dep", Types.VARCHAR),
                            new SqlParameter("poste", Types.VARCHAR)
                    )
                    .returningResultSet("result1", (rs, rowNum) -> {
                        ResultSetMetaData metaData = rs.getMetaData();
                        int columnCount = metaData.getColumnCount();
                        Map<String, Object> row = new HashMap<>();

                        for (int i = 1; i <= columnCount; i++) {
                            String columnName = metaData.getColumnName(i);
                            if (!columnNames.contains(columnName)) { columnNames.add(columnName); }
                            Object value = rs.getObject(i);
                            row.put(columnName, value != null ? value : 0);
                        }
                        return row;
                    });

            Map<String, Object> inParams = new HashMap<>();
            inParams.put("dep", departement_code);
            inParams.put("poste", poste_code);
            Map<String, Object> results = jdbcCall.execute(inParams);

            dataResult = (List<Map<String, Object>>) results.get("result1");

            result.put("columnNames", columnNames);
            result.put("dataResult", dataResult);

        } catch (Exception e) {
            e.printStackTrace();
            result.put("error", e.getMessage());
        }
        return result;
    }



    @Override
    public int insert_paramsFormation(String sessionId, String form_code, String code_poste,String dep) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "INSERT INTO DB_RH..RH_FORMATION_EMB (FORM_DEP, FORM_POSTE, FORM_CODE) VALUES (?,?,?)";
        return jdbcTemplate.update(sql, dep, code_poste,form_code);
    }

    @Override
    public void deleteParamsFormation(String sessionId, String form_code, String code_poste,String dep) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "DELETE FROM DB_RH..RH_FORMATION_EMB WHERE FORM_DEP = ? AND FORM_POSTE =? AND FORM_CODE =?";
        jdbcTemplate.update(sql, dep, code_poste,form_code);
    }

    @Override
    public int updateDateForm(String sessionId, String matricule, String form_code, String libelle_form, Date date) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = "update DB_RH..RH_FORMATION_LIGNE set FORM_DATEREELLE = ? where FORM_MATRICULE = ? and FORM_CODE =?";

        return jdbcTemplate.update(sql, date, matricule, form_code);
    }





}
