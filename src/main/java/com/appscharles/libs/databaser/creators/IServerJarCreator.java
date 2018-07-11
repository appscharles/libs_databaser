package com.appscharles.libs.databaser.creators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

import java.io.File;

/**
 * The interface Server jar creator.
 */
public interface IServerJarCreator {

    /**
     * Create.
     *
     * @throws DatabaserException the databaser exception
     */
    void create() throws DatabaserException;

    /**
     * Sets server dir.
     *
     * @param serverDir the server dir
     */
    void setServerDir(File serverDir);
}
