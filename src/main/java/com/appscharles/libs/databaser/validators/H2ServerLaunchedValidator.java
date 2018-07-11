package com.appscharles.libs.databaser.validators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The type H 2 server launched validator.
 */
public class H2ServerLaunchedValidator {

    /**
     * Is launched boolean.
     *
     * @param serverUrl the server url
     * @return the boolean
     * @throws DatabaserException the databaser exception
     */
    public static Boolean isLaunched(String serverUrl) throws DatabaserException {
        if (serverUrl.endsWith("/") == false){
            serverUrl += "/";
        };
        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + serverUrl + "/~isLaunched;DB_CLOSE_ON_EXIT=TRUE;" )) {
            new File("~isLaunched.mv.db").delete();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
