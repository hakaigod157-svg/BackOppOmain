package com.opham.prepa.service;

import com.opham.prepa.Utils.UserSessionManager;
import com.opham.prepa.mapper.Capa.AtteValMapper;
import com.opham.prepa.mapper.Capa.CapaMapper;
import com.opham.prepa.mapper.Capa.CauseRacMapper;
import com.opham.prepa.mapper.Capa.ktableMapper;
import com.opham.prepa.model.Capa.AtteVal;
import com.opham.prepa.model.Capa.Capa;
import com.opham.prepa.model.Capa.CauseRac;
import com.opham.prepa.model.Capa.ktable;
import com.opham.prepa.repository.Capa.CapaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
public class CapaService implements CapaRepository {
    private final UserSessionManager userSessionManager;

    @Autowired
    public CapaService(UserSessionManager userSessionManager) {

        this.userSessionManager = userSessionManager;
    }
    @Override
    public List<Capa> listCapa(String sessionId,String code, String description, String emetteur, String libelleDep, String type, Date date, Integer causevalid, Integer causeatte, Integer attevalid, Integer attemiseappli, Integer appli) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        StringBuilder sql = new StringBuilder("SELECT NCPS_SEQ,NCPS_CODE, NCPS_DATE, NCPS_MODEDETECT, NCPS_EMETTEUR, LIBELLE_DEP, NCPS_DESCRIPTION, NCPS_TYPE, NB_CAUSE_RACINE, NBACTION, NCPS_DEPARTEMENT, NCPS_ETAT, NCPS_VALIDE, LIBELLE_MODE_DETECT, NCPS_PROCESSUS_DETECT, LIBELLE_PROCESSUS_DETECT, CAUSE_VALID, CAUSE_ATTE, ATTE_VALID, ATTE_MISE_APPLI, APPLIQUE, LIBELE_TYPE FROM AMC..VIEW_SUIVI_AMELIORATION WHERE 1=1");

        List<Object> params = new ArrayList<>();

        if (code != null && !code.isEmpty()) {
            sql.append(" AND NCPS_CODE LIKE ?");
            params.add("%" + code + "%");
        }
        if (description != null && !description.isEmpty()) {
            sql.append(" AND NCPS_DESCRIPTION LIKE ?");
            params.add("%" + description + "%");
        }
        if (emetteur != null && !emetteur.isEmpty()) {
            sql.append(" AND NCPS_EMETTEUR LIKE ?");
            params.add("%" + emetteur + "%");
        }
        if (libelleDep != null && !libelleDep.isEmpty()) {
            sql.append(" AND LIBELLE_DEP LIKE ?");
            params.add("%" + libelleDep + "%");
        }
        if (type != null && !type.isEmpty()) {
            sql.append(" AND NCPS_TYPE LIKE ?");
            params.add("%" + type + "%");
        }
        if (date != null) {
            sql.append(" AND NCPS_DATE = ?");
            params.add(date);
        }

        if (causevalid != null) {
            sql.append(" AND CAUSE_VALID <> ?");
            params.add(causevalid);
        }
        if (causeatte != null) {
            sql.append(" AND CAUSE_ATTE <> ?");
            params.add(causeatte);
        }
        if (attevalid != null) {
            sql.append(" AND ATTE_VALID <> ?");
            params.add(attevalid);
        }
        if (attemiseappli != null) {
            sql.append(" AND ATTE_MISE_APPLI <> ?");
            params.add(attemiseappli);
        }
        if (appli != null) {
            sql.append(" AND APPLIQUE <> ?");
            params.add(appli);
        }

        sql.append(" ORDER BY NCPS_CODE ASC");

        return jdbcTemplate.query(sql.toString(), params.toArray(), new CapaMapper());
    }


    public void updateFNCPS(String sessionId,int ncpsSeq,String ncpsCode, Date ncpsDate, String ncpsDescription, String ncpsDepartement, String ncpsType, String ncpsModeDetect, String ncpsProcessusDetect) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        StringBuilder sql = new StringBuilder("UPDATE AMC..FNCPS SET NCPS_ETAT=1 , NCPS_VALIDE=1 ,");
        List<Object> params = new ArrayList<>();
        boolean first = true;

        if (ncpsCode != null && !ncpsCode.isEmpty()) {
            if (!first) sql.append(", ");
            sql.append("NCPS_CODE = ?");
            params.add(ncpsCode);
            first = false;
        }
        if (ncpsDate != null) {
            if (!first) sql.append(", ");
            sql.append("NCPS_DATE = ?");
            params.add(ncpsDate);
            first = false;
        }
        if (ncpsDescription != null && !ncpsDescription.isEmpty()) {
            if (!first) sql.append(", ");
            sql.append("NCPS_DESCRIPTION = ?");
            params.add(ncpsDescription);
            first = false;
        }
        if (ncpsDepartement != null && !ncpsDepartement.isEmpty()) {
            if (!first) sql.append(", ");
            sql.append("NCPS_DEPARTEMENT = ?");
            params.add(ncpsDepartement);
            first = false;
        }
        if (ncpsType != null && !ncpsType.isEmpty()) {
            if (!first) sql.append(", ");
            sql.append("NCPS_TYPE = ?");
            params.add(ncpsType);
            first = false;
        }
        if (ncpsModeDetect != null && !ncpsModeDetect.isEmpty()) {
            if (!first) sql.append(", ");
            sql.append("NCPS_MODEDETECT = ?");
            params.add(ncpsModeDetect);
            first = false;
        }
        if (ncpsProcessusDetect != null && !ncpsProcessusDetect.isEmpty()) {
            if (!first) sql.append(", ");
            sql.append("NCPS_PROCESSUS_DETECT = ?");
            params.add(ncpsProcessusDetect);
        }

        sql.append(" WHERE NCPS_SEQ = ?");
        params.add(ncpsSeq);

        jdbcTemplate.update(sql.toString(), params.toArray());

    }

    @Override
    public void insertFNCPS(String sessionId,String ncpsEmetteur, String ncpsDescription, String ncpsDepartement, Date ncpsDate, int ncpsEtat, int ncpsValide) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        StringBuilder sql = new StringBuilder("INSERT INTO AMC..FNCPS (NCPS_EMETTEUR, NCPS_DESCRIPTION, NCPS_DEPARTEMENT, NCPS_DATE, NCPS_ETAT, NCPS_VALIDE) VALUES (?, ?, ?, ?, ?, ?)");
        List<Object> params = new ArrayList<>();

        params.add(ncpsEmetteur);
        params.add(ncpsDescription);
        params.add(ncpsDepartement);
        params.add(ncpsDate);
        params.add(ncpsEtat);
        params.add(ncpsValide);

        jdbcTemplate.update(sql.toString(), params.toArray());
    }

    @Override
    public List<AtteVal> listatteval(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "SELECT NCPS_SEQ,NCPS_DATE, NCPS_EMETTEUR, NCPS_DEPARTEMENT, NCPS_DESCRIPTION FROM AMC..FNCPS WHERE NCPS_ETAT = 0";
        return jdbcTemplate.query(sql, new AtteValMapper());
    }

    @Override
    public List<CauseRac> findByCodeAction(String sessionId,int actionCauseseq) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        StringBuilder sql = new StringBuilder("SELECT ACTION_SEQ,ACTION_NCPSCODE, ACTION_VALIDE, ACTION_RESPONSABLE, ACTION_DATEPREV, ACTION_DATEEFFECT, ACTION_MISEAPPLI, ACTION_COMMENTAIRE, ACTION_CAUSESEQ, ACTION_DESCRIPTION, VALIDATION_LIBELLE, LIBELLE_TYPE,ACTION_DATE_ANAL_EFFIC,ACTION_DATE_RELLE_EFFIC FROM AMC..VIEW_ACTION WHERE 1=1");
        List<Object> params = new ArrayList<>();

        sql.append(" AND ACTION_CAUSESEQ = ?");
        params.add(actionCauseseq);
        return jdbcTemplate.query(sql.toString(), params.toArray(), new CauseRacMapper());
    }

    public void deleteFACTION(String sessionId,int actionSeq) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        StringBuilder sql = new StringBuilder("DELETE FROM AMC..FACTION WHERE ACTION_SEQ = ?");
        List<Object> params = new ArrayList<>();

        params.add(actionSeq);
        jdbcTemplate.update(sql.toString(), params.toArray());
    }

    @Override
    public void updateFACTION(String sessionId,int actionSeq, String actionResponsable, Date actionDateprev) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        StringBuilder sql = new StringBuilder("UPDATE AMC..FACTION SET ACTION_VALIDE = 1, ");
        List<Object> params = new ArrayList<>();
        boolean first = true;

        if (actionResponsable != null && !actionResponsable.isEmpty()) {
            if (!first) sql.append(", ");
            sql.append("ACTION_RESPONSABLE = ?");
            params.add(actionResponsable);
            first = false;
        }

        if (actionDateprev != null) {
            if (!first) sql.append(", ");
            sql.append("ACTION_DATEPREV = ?");
            params.add(actionDateprev);
            first = false;
        }

        sql.append(" WHERE ACTION_SEQ = ?");
        params.add(actionSeq);

        jdbcTemplate.update(sql.toString(), params.toArray());
    }

    @Override
    public void updateFACTION2(String sessionId,int actionSeq,Date actionDateanaleffic,Date actionDaterelleeffic){
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        StringBuilder sql = new StringBuilder("UPDATE AMC..FACTION SET ACTION_MISEAPPLI = 1,");
        List<Object> params = new ArrayList<>();
        boolean first = true;

        if (actionDateanaleffic != null) {
            if (!first) sql.append(", ");
            sql.append("ACTION_DATEEFFECT = ?");
            params.add(actionDateanaleffic);
            first = false;
        }

        if (actionDaterelleeffic != null) {
            if (!first) sql.append(", ");
            sql.append("ACTION_DATE_ANAL_EFFIC = ?");
            params.add(actionDaterelleeffic);
            first = false;
        }

        sql.append(" WHERE ACTION_SEQ = ?");
        params.add(actionSeq);

        jdbcTemplate.update(sql.toString(), params.toArray());
    }

    @Override
    public void updateFACTION3(String sessionId,int actionSeq,Date actionDaterelleeffic,String actionCommentaire){
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        StringBuilder sql = new StringBuilder("UPDATE AMC..FACTION SET ACTION_ANALYSE = 1,");
        List<Object> params = new ArrayList<>();
        boolean first = true;

        if (actionDaterelleeffic != null) {
            if (!first) sql.append(", ");
            sql.append("ACTION_DATE_RELLE_EFFIC = ?");
            params.add(actionDaterelleeffic);
            first = false;
        }

        if (actionCommentaire != null) {
            if (!first) sql.append(", ");
            sql.append("ACTION_COMMENTAIRE = ?");
            params.add(actionCommentaire);
            first = false;
        }

        sql.append(" WHERE ACTION_SEQ = ?");
        params.add(actionSeq);

        jdbcTemplate.update(sql.toString(), params.toArray());
    }

    @Override
    public List<ktable> findktable(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "SELECT TOP 1 SEQ,NOMTABLE, MOIS, ANNEE, VALEUR FROM AMC..KTABLE ORDER BY SEQ DESC";
        return jdbcTemplate.query(sql, new ktableMapper());
    }
    @Override
    public void updateKTABLE(String sessionId,int seqk) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "UPDATE AMC..KTABLE SET VALEUR = VALEUR + 1 WHERE SEQ = ?";
        jdbcTemplate.update(sql, seqk);
    }
    @Override
    public void insertKTABLE(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "INSERT INTO AMC..KTABLE (MOIS, ANNEE, VALEUR, NOMTABLE) VALUES (?, ?, ?, ?)";
        int moisActuel = LocalDate.now().getMonthValue();
        int anneeActuelle = LocalDate.now().getYear();

        int valeurInitiale = 1;
        String nomtable = "FNCPS";

        jdbcTemplate.update(sql, moisActuel, anneeActuelle, valeurInitiale, nomtable);
    }

}
