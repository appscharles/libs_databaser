package com.appscharles.libs.databaser.runners;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

/**
 * The interface Server runner.
 */
public interface IServerRunner extends IWebConsolable, IServerDirectionable, IServerAutostartable, IServerTimeoutable, IServerRunForceable {

    /**
     * Run.
     *
     * @throws DatabaserException the databaser exception
     */
    void start() throws DatabaserException;

    void stop() throws DatabaserException;

    /**
     * Sets server dir.
     *
     * @param serverDir the server dir
     */




}
