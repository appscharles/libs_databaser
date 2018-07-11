package com.appscharles.libs.databaser.runners;

import java.io.File;

/**
 * The type Abstract server runner.
 */
public abstract class AbstractServerRunner implements IServerRunner {

    /**
     * The Port.
     */
    protected Integer port;

    /**
     * The Server dir.
     */
    protected File serverDir;


    /**
     * The Test mode.
     */
    protected Boolean testMode;

    /**
     * The Autostart.
     */
    protected Boolean autostart;

    /**
     * Instantiates a new Abstract server runner.
     *
     * @param port      the port
     * @param serverDir the server dir
     */
    public AbstractServerRunner(Integer port, File serverDir) {
        this.port = port;
        this.serverDir = serverDir;
        this.testMode = false;
        this.autostart = false;
    }


    @Override
    public void setServerDir(File serverDir) {
        this.serverDir = serverDir;
    }

    @Override
    public void enableTestMode() {
        this.testMode = true;
    }

    @Override
    public void enableAutostart() {
        this.autostart = true;
    }
}
