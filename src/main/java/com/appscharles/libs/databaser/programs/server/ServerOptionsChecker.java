package com.appscharles.libs.databaser.programs.server;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

/**
 * The type Server options checker.
 */
public class ServerOptionsChecker {

    /**
     * Check.
     *
     * @param options the options
     * @throws DatabaserException the databaser exception
     */
    public static void check(ServerOptions options) throws DatabaserException{
        if (options.port.isEmpty()){
            throw new DatabaserException("Argument 'port' is empty.");
        } else  if (options.serverDir.isEmpty()){
            throw new DatabaserException("Argument 'serverDir' is empty.");
        }
    }
}
