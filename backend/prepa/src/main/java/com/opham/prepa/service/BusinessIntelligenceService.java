package com.opham.prepa.service;
import com.opham.prepa.Utils.UserSessionManager;
import com.opham.prepa.mapper.BusinessIntelligence.*;
import com.opham.prepa.mapper.BusinessIntelligence.det_palier_thirdMapper;
import com.opham.prepa.model.BusinessIntelligence.list_ca_annuel;
import com.opham.prepa.model.BusinessIntelligence.list_view;
import com.opham.prepa.model.BusinessIntelligence.taux_palier;
import com.opham.prepa.repository.BusinessIntelligence.BusinessIntelligenceRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.*;

@Repository
public class BusinessIntelligenceService implements BusinessIntelligenceRepository {
    private final UserSessionManager userSessionManager;

    public BusinessIntelligenceService(UserSessionManager userSessionManager) {
        this.userSessionManager = userSessionManager;
    }


    @Override
    public List<list_view> listView(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("SELECT name FROM ObjectifDM..sysobjects WHERE type = 'P' and name = 'x_Palier' ORDER BY name",
                new list_viewMapper());
    }

    @Override
    public List<list_ca_annuel> listCaAnnuel(String sessionId, String codeArticle) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("select * from VIEW_CA_ANNEE where ARTICLE = ?",
                new list_ca_annuelMapper(),codeArticle);
    }


    @Override
    public List<List<Object>> listPalier(String sessionId, Double taux, Double primeMax) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        jdbcTemplate.execute("USE ObjectifDM");

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("dbo")
                .withProcedureName("x_Palier")
                .declareParameters(
                        new SqlParameter("taux", Types.DOUBLE),
                        new SqlParameter("primeMax", Types.DOUBLE)
                )
                .returningResultSet("result1", new list_palierMapper())
                .returningResultSet("result2", new list_palier_secondMapper())
                .returningResultSet("result3", new list_palier_thirdMapper())
                .returningResultSet("result4", new det_palierMapper())
                .returningResultSet("result5", new det_palier_secondMapper())
                .returningResultSet("result6", new det_palier_thirdMapper());


        Map<String, Object> inParams = new HashMap<>();
        inParams.put("taux", taux);
        inParams.put("primeMax", primeMax);

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
    public List<taux_palier> list_taux(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("SELECT  distinct mat_taux  from ObjectifDM..view_palier3 order by mat_taux",
                new taux_palierMapper());
    }
}
