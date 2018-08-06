package com.appscharles.libs.databaser.changers;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.validators.FileDatabaseExistValidator;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The type H 2 database password changer.
 */
public class DatabasePasswordChanger {

    /**
     * Change.
     *
     * @param dBDir       the d b dir
     * @param dBName      the d b name
     * @param user        the user
     * @param oldPassword the old password
     * @param newPassword the new password
     * @throws DatabaserException the databaser exception
     */
    public static void change(File dBDir, String dBName, String user, String oldPassword, String newPassword) throws DatabaserException {
        if (FileDatabaseExistValidator.exist(dBName, dBDir) == false) {
            throw new DatabaserException("Could not change user, because database is not exist. [0010-000]");
        }
        try (Connection connection = DriverManager.getConnection("jdbc:h2:file:" + dBDir.getAbsolutePath() + File.separator + dBName + ";IFEXISTS=TRUE", user, oldPassword)) {
            connection.createStatement().execute("ALTER USER " + user + " SET PASSWORD '" + newPassword + "'");
        } catch (SQLException e) {
            throw new DatabaserException(e);
        }
    }
}
