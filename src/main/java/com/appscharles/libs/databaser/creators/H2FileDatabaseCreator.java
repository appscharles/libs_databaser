package com.appscharles.libs.databaser.creators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.validators.H2FileDatabaseExistValidator;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The type H 2 file database creator.
 */
public class H2FileDatabaseCreator implements IDatabaseCreator {

    private final String dBName;

    private final String username;

    private final String password;

    private final File serverDir;

    /**
     * Instantiates a new H 2 file database creator.
     *
     * @param dBName    the d b name
     * @param username  the username
     * @param password  the password
     * @param serverDir the server dir
     */
    public H2FileDatabaseCreator(String dBName, String username, String password, File serverDir) {
        this.dBName = dBName;
        this.username = username;
        this.password = password;
        this.serverDir = serverDir;
    }

    public void create() throws DatabaserException {
        if (this.username.matches("[a-zA-Z]+") == false){
            throw new DatabaserException("Username can only contain letters. [0011-000]");
        }
        if (H2FileDatabaseExistValidator.exist(this.dBName, this.serverDir)){
            throw new DatabaserException("File h2 database not created, because it is exist. [0009-000]");
        }
        try (Connection connection = DriverManager.getConnection("jdbc:h2:file:"+ this.serverDir.getAbsolutePath() + File.separator + this.dBName, this.username, this.password)) {
        } catch (SQLException e) {
            throw new DatabaserException(e);
        }
    }
}
