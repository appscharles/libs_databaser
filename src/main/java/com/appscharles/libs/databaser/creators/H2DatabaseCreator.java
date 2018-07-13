package com.appscharles.libs.databaser.creators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.extractors.URLServerExtractor;
import com.appscharles.libs.databaser.validators.DatabaseExistValidator;
import com.appscharles.libs.databaser.validators.ServerRunningValidator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The type H 2 database creator.
 */
public class H2DatabaseCreator implements IDatabaseCreator {

    private final String databaseUrl;

    private final String username;

    private final String password;

    /**
     * Instantiates a new H 2 database creator.
     *
     * @param databaseUrl the database url
     * @param username    the username
     * @param password    the password
     */
    public H2DatabaseCreator(String databaseUrl, String username, String password) {
        this.databaseUrl = databaseUrl;
        this.username = username;
        this.password = password;
    }

    public void create() throws DatabaserException {
        if (ServerRunningValidator.isRunning(this.databaseUrl) == false){
            throw new DatabaserException("H2 server is not launched in: " + URLServerExtractor.extract(this.databaseUrl) + " [0004-001]");
        }
        if (DatabaseExistValidator.exist(this.databaseUrl)){
            throw new DatabaserException("Database not created, because it is exist. [0001-000]");
        }
        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + this.databaseUrl, this.username, this.password)) {
        } catch (SQLException e) {
            throw new DatabaserException(e);
        }
    }
}
