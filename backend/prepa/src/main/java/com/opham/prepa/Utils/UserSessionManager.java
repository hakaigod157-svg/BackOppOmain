package com.opham.prepa.Utils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserSessionManager {

    private final Map<String, JdbcTemplate> activeUserSessions = new HashMap<>();

    public void setUserJdbcTemplate(String sessionId, JdbcTemplate jdbcTemplate) {
        activeUserSessions.put(sessionId, jdbcTemplate);
        System.out.println("Session créée pour l'utilisateur avec le sessionId: " + sessionId);
    }

    public JdbcTemplate getUserJdbcTemplate(String sessionId) {
        JdbcTemplate jdbcTemplate = activeUserSessions.get(sessionId);
        if (jdbcTemplate == null) {
            throw new IllegalStateException("No active session for the user");
        }
        return jdbcTemplate;
    }

    public void removeUserSession(String sessionId) {
        activeUserSessions.remove(sessionId);
        System.out.println("Session supprimée pour le sessionId: " + sessionId);
    }

    public void clear() {
        activeUserSessions.clear();
        System.out.println("Toutes les sessions ont été supprimées");
    }
}