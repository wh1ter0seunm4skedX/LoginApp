package com.b7av3.loginapp.model;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {

    private final JdbcTemplate jdbcTemplate;

    // Constructor injection for JdbcTemplate
    public DatabaseInitializer(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Initializes the database by creating the persistent_logins table if it does not exist.
     */
    @PostConstruct
    public void initialize() {
        jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS persistent_logins (" +
                        "username VARCHAR(64) NOT NULL," +
                        "series VARCHAR(64) PRIMARY KEY," +
                        "token VARCHAR(64) NOT NULL," +
                        "last_used TIMESTAMP NOT NULL)"
        );
    }
}
