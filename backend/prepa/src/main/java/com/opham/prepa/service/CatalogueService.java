package com.opham.prepa.service;
import com.opham.prepa.Utils.UserSessionManager;
import com.opham.prepa.mapper.Catalogue.CatalogueMapper;
import com.opham.prepa.mapper.Catalogue.listCodeBarreMapper;
import com.opham.prepa.mapper.Catalogue.listLaboMapper;
import com.opham.prepa.model.Catalogue.catalogue;
import com.opham.prepa.model.Catalogue.listCodeBarre;
import com.opham.prepa.model.Catalogue.listLabo;
import com.opham.prepa.repository.Catalogue.CatalogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.*;
@Repository

public class CatalogueService implements CatalogueRepository {
    private UserSessionManager userSessionManager;
    @Autowired
    public void ValidationCommandeService(UserSessionManager userSessionManager) {

        this.userSessionManager = userSessionManager;
    }

    @Override
    public List<catalogue> findCatalogue(String sessionId, String clientcode, int moiscode, int anneecode, String tarifcode, int distribue) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("exec x_EtatDist_catalogue ?,?,?,?,?",
                new CatalogueMapper(),clientcode,moiscode,anneecode,tarifcode,distribue);
    }

    @Override
    public int findTestCatalogue(String sessionId, int moiscode, int anneecode) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.queryForObject("select count(*) from Equagestion..xCAT_CLT where xCAT_MOIS=?and xCAT_ANNEE=?",
                Integer.class, moiscode, anneecode);
    }

    @Override
    public int genereCatalogue(String sessionId, int moiscode ,int anneecode) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "exec x_GenerCatalogue ?,?";
        return jdbcTemplate.update(sql, moiscode,anneecode);
    }

    @Override
    public List<listLabo> findListLabo(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("SELECT  FOCODE,FONOM FROM Equagestion..FFO WHERE isnull(FO_CODE_BARRE,'')<>''",
                new listLaboMapper());
    }

    @Override
    public List<listCodeBarre> findListCodeBarre(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("SELECT  *  FROM DBSUIVI..x_CODE_BARRE",
                new listCodeBarreMapper());
    }

 /*   @Override
    public int insertCodeBarre(String sessionId, String labo ,String article) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "exec DBSUIVI..x_CreateCodeBarre ?,?";
        return jdbcTemplate.update(sql, labo,article);
    } */

    @Override
    public int insertCodeBarre(String sessionId, String labo, String article) {

        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = "exec DBSUIVI..x_CreateCodeBarre ?,?";

        jdbcTemplate.execute(sql, (PreparedStatement ps) -> {
            ps.setString(1, labo);
            ps.setString(2, article);
            ps.execute();
            return null;
        });

        return 1;
    }

}
