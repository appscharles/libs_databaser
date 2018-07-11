package com.appscharles.libs.databaser.servers;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

/**
 * The interface Server.
 */
public interface IServer {

    /**
     * Start.
     *
     * @throws DatabaserException the databaser exception
     */
    void start() throws DatabaserException;

    /**
     * Stop.
     *
     * @throws DatabaserException the databaser exception
     */
    void stop() throws DatabaserException;
}
