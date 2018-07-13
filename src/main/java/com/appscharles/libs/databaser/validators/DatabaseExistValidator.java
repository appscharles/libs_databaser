package com.appscharles.libs.databaser.validators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The type Database exist validator.
 */
public class DatabaseExistValidator {

    /**
     * Exist boolean.
     *
     * @param databaseUrl the database url
     * @return the boolean
     * @throws DatabaserException the databaser exception
     */
    public static Boolean exist(String databaseUrl) throws DatabaserException {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + databaseUrl + ";IFEXISTS=TRUE")) {
            return true;
        } catch (SQLException e) {
            if (e.getMessage().contains("[90013-")){
                return false;
            } else if (e.getMessage().contains("[28000-")){
                return true;
            }
           throw new DatabaserException(e);
        }
    }
}
