package com.appscharles.libs.databaser.validators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.extractors.URLServerExtractor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The type H 2 server launched validator.
 */
public class ServerRunningValidator {

    private static final Logger logger = LogManager.getLogger(ServerRunningValidator.class);

    /**
     * Is launched boolean.
     *
     * @param databaseURL the database url
     * @return the boolean
     * @throws MalformedURLException the malformed url exception
     */
    public static Boolean isRunning(String databaseURL) throws DatabaserException {
        String serverURL = URLServerExtractor.extract(databaseURL);
        new File("isRunning.mv.db").delete(); // if not delete, validate is not god
        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + serverURL + "/isRunning")) {
            new File("isRunning.mv.db").deleteOnExit(); // if not delete, file only exist in directory
            return true;
        } catch (SQLException e) {
            if (e.getMessage().contains("[90067")){
                return false;
            }
            logger.debug(e);
            return false;
        }
    }
}
