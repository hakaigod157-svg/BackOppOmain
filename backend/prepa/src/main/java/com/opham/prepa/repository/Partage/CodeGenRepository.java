package com.opham.prepa.repository.Partage;

import com.opham.prepa.Utils.UserSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CodeGenRepository {
    @Autowired
    UserSessionManager userSessionManager = new UserSessionManager();

    private final JdbcTemplate jdbcTemplate;

    public CodeGenRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getLastCode(String sessionId, String prefix, String table, String colonne) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = "SELECT MAX(" + colonne + ") FROM " + table + " WHERE " + colonne + " LIKE ?";
        return jdbcTemplate.queryForObject(sql, String.class, prefix + "%");
    }
}