package com.appscharles.libs.databaser.changers;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.validators.FileDatabaseExistValidator;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The type H 2 database user changer.
 */
public class DatabaseUserChanger {

    /**
     * Change.
     *
     * @param dBDir    the d b dir
     * @param dBName   the d b name
     * @param oldUser  the old user
     * @param password the password
     * @param newUser  the new user
     * @throws DatabaserException the databaser exception
     */
    public static void change(File dBDir, String dBName, String oldUser, String password, String newUser) throws DatabaserException {
        if (FileDatabaseExistValidator.exist(dBName, dBDir) == false) {
            throw new DatabaserException("Could not change user, because database is not exist. [0010-000]");
        }
        try (Connection connection = DriverManager.getConnection("jdbc:h2:file:" + dBDir.getAbsolutePath() + File.separator + dBName + ";IFEXISTS=TRUE", oldUser, password)) {
            connection.createStatement().execute("ALTER USER " + oldUser + " RENAME TO " + newUser + "");
            connection.createStatement().execute("ALTER USER " + newUser + " SET PASSWORD '" + password + "'");
        } catch (SQLException e) {
            throw new DatabaserException(e);
        }
    }
}
