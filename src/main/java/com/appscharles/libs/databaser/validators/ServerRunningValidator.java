package com.appscharles.libs.databaser.validators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.extractors.URLServerExtractor;
import com.appscharles.libs.processer.callers.CommanderCaller;
import com.appscharles.libs.processer.converters.PIDsConverter;
import com.appscharles.libs.processer.exceptions.ProcesserException;
import com.appscharles.libs.processer.searchers.CommandLineSearcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The type Server running validator.
 */
public class ServerRunningValidator {

    private static final Logger logger = LogManager.getLogger(ServerRunningValidator.class);

    /**
     * Is running boolean.
     *
     * @param databaseURL the database url
     * @return the boolean
     * @throws DatabaserException the databaser exception
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
            logger.debug(e, e);
            return false;
        }
    }

    /**
     * Is running boolean.
     *
     * @param databaseURL the database url
     * @param serverDir   the server dir
     * @return the boolean
     * @throws DatabaserException the databaser exception
     */
    public static Boolean isRunning(String databaseURL, File serverDir) throws DatabaserException {
        try {
            Boolean existProcess = new CommandLineSearcher(serverDir.getAbsolutePath().replace("\\", "\\\\"), new CommanderCaller(), new PIDsConverter()).search().size()> 0;
            Boolean serverIsRunning = ServerRunningValidator.isRunning(databaseURL);
            return existProcess && serverIsRunning;
        } catch (ProcesserException e) {
            throw new DatabaserException(e);
        }
    }


}
