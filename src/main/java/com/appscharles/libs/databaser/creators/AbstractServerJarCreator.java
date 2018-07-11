package com.appscharles.libs.databaser.creators;

import java.io.File;

/**
 * The type Abstract server jar creator.
 */
public abstract class AbstractServerJarCreator implements IServerJarCreator {

    /**
     * The Server dir.
     */
    protected File serverDir;

    /**
     * Instantiates a new Abstract server jar creator.
     *
     * @param serverDir the server dir
     */
    public AbstractServerJarCreator(File serverDir) {
        this.serverDir = serverDir;
    }


    public void setServerDir(File serverDir) {
        this.serverDir = serverDir;
    }
}
