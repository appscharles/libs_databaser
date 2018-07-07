package com.appscharles.libs.databaser.creators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.validators.DatabaseExistValidator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The type Database creator.
 */
public class DatabaseCreator implements IDatabaseCreator {

    private final String databaseUrl;

    private final String username;

    private final String password;

    /**
     * Instantiates a new Database creator.
     *
     * @param databaseUrl the database url
     * @param username    the username
     * @param password    the password
     */
    public DatabaseCreator(String databaseUrl, String username, String password) {
        this.databaseUrl = databaseUrl;
        this.username = username;
        this.password = password;
    }

    public void create() throws DatabaserException {
        if (DatabaseExistValidator.exist(this.databaseUrl)){
            throw new DatabaserException("Database not created, because it is exist. [0001-000]");
        }
        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + this.databaseUrl, this.username, this.password)) {
        } catch (SQLException e) {
            throw new DatabaserException(e);
        }
    }
}
