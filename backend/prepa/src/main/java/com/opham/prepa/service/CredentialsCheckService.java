package com.opham.prepa.service;
import com.opham.prepa.Utils.DataSourceConfig;
import com.opham.prepa.Utils.FileParser;
import com.opham.prepa.Utils.UserSessionManager;
import com.opham.prepa.model.Utils.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.UUID;

@Service
public class CredentialsCheckService {

    private final DataSourceConfig dataSourceConfig;
    private final UserSessionManager userSessionManager;

    @Autowired
    public CredentialsCheckService(DataSourceConfig dataSourceConfig, UserSessionManager userSessionManager) {
        this.dataSourceConfig = dataSourceConfig;
        this.userSessionManager = userSessionManager;
    }

    public Credentials checkCredentials(Credentials credentials) throws SQLException {
        String name = credentials.getName_server();
        String filepath = "interfaces";
        FileParser fileParser = new FileParser();
        String ip = fileParser.recupererIP(name, filepath);
        // Create a DataSource with the provided credentials
        DataSource dataSource = dataSourceConfig.getDataSource(credentials.getUsername(), credentials.getPassword(), ip);
        JdbcTemplate userJdbcTemplate = new JdbcTemplate(dataSource);

        // Execute a simple query to check the validity of the connection
        String sql = "SELECT COUNT(*) FROM FAR";
        Integer count = userJdbcTemplate.queryForObject(sql, Integer.class);

        // Check if the connection is valid
        if (count != null && count > 0) {
            return credentials; // Return the credentials if valid
        }
        return null; //
    }

    public void setSessionId(Credentials credentials, String sessionId) {
        // Create a new JdbcTemplate with valid credentials and store it in the UserSessionManager
        String name = credentials.getName_server();
        String filepath = "interfaces";
        FileParser fileParser = new FileParser();
        String ip = fileParser.recupererIP(name, filepath);

        DataSource dataSource = dataSourceConfig.getDataSource(credentials.getUsername(), credentials.getPassword(), ip);
        JdbcTemplate userJdbcTemplate = new JdbcTemplate(dataSource);

        // Set the JdbcTemplate for the session
        userSessionManager.setUserJdbcTemplate(sessionId, userJdbcTemplate);
    }

    public void logoutUser(String sessionId,String username, String password) {
        userSessionManager.removeUserSession(sessionId); // Remove the active session
        // Optionally: Close the connection if necessary
        dataSourceConfig.closeDataSource(username,password); // Implement the logic to close the DataSource based on sessionId
    }
}