package com.appscharles.libs.databaser.validators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * The type Port using validator.
 */
public class PortUsingValidator {

    private static final Logger logger = LogManager.getLogger(ServerRunningValidator.class);


    /**
     * Is using boolean.
     *
     * @param host the host
     * @param port the port
     * @return the boolean
     * @throws DatabaserException the databaser exception
     */
    public static Boolean isUsing(String host, Integer port) throws DatabaserException {
        try {
            new ServerSocket(port).close();
            return false;
        } catch(IOException e) {
            logger.debug(e, e);
            return true;
        }
    }
}
