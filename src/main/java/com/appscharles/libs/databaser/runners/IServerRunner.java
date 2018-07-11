package com.appscharles.libs.databaser.runners;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

import java.io.File;

/**
 * The interface Server runner.
 */
public interface IServerRunner {

    /**
     * Run.
     *
     * @throws DatabaserException the databaser exception
     */
    void run() throws DatabaserException;

    /**
     * Sets server dir.
     *
     * @param serverDir the server dir
     */
    void setServerDir(File serverDir);


    /**
     * Enable test mode.
     */
    void enableTestMode();

    /**
     * Enable autostart.
     */
    void enableAutostart();
}
