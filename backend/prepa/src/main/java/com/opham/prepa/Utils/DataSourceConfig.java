package com.opham.prepa.Utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class DataSourceConfig {

    // Map to store connection pools per user
    private ConcurrentHashMap<String, HikariDataSource> dataSourceMap = new ConcurrentHashMap<>();

    public DataSource getDataSource(String username, String password, String ip_server) {
        // Generate a unique key based on the hashed combination of username and password
        String key = generateUniqueKey(username, password);

        // Check if a connection pool already exists for this user
        return dataSourceMap.computeIfAbsent(key, k -> {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setDriverClassName("com.sybase.jdbc4.jdbc.SybDriver");
            hikariConfig.setJdbcUrl("jdbc:sybase:Tds:" + ip_server + ":2025/Equagestion");
            hikariConfig.setUsername(username);
            hikariConfig.setPassword(password);
            hikariConfig.addDataSourceProperty("networkTimeout", 0);
            hikariConfig.setMaximumPoolSize(50); // For example, a maximum of 20 connections per user
            hikariConfig.setIdleTimeout(60000); // 1 minutes of inactivity
            hikariConfig.setConnectionTimeout(30000); // Connection attempt timeout (30 seconds)
            hikariConfig.setMaxLifetime(300000);// 5mn
            hikariConfig.setPoolName("HikariPool-" + username);
            hikariConfig.setMinimumIdle(1);
            return new HikariDataSource(hikariConfig); // Create and return a new DataSource
        });
    }

    // Method to close the user's connection pool when they log out
    public void closeDataSource(String username, String password) {
        String key = generateUniqueKey(username, password);
        DataSource dataSource = dataSourceMap.remove(key);
        if (dataSource instanceof HikariDataSource) {
            ((HikariDataSource) dataSource).close(); // Properly close the pool
        }
    }

    // Helper method to generate a unique key by hashing the username and password combination
    private String generateUniqueKey(String username, String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest((username + ":" + password).getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating unique key", e);
        }
    }
}