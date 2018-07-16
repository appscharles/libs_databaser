package com.appscharles.libs.databaser.runners;

import java.io.File;

/**
 * The type Abstract server runner.
 */
public abstract class AbstractServerRunner implements IServerRunner {

    private static final Long DEFAULT_TIMEOUT_SERVER_RUNNING = 20000L;
    /**
     * The Tcp port.
     */
    protected Integer tcpPort;

    /**
     * The Web port.
     */
    protected Integer webPort;

    /**
     * The Server dir.
     */
    protected File serverDir;

    /**
     * The Autostart.
     */
    protected Boolean autostart;

    protected Long serverRunningTimeout;

    protected Boolean runForce;

    protected String command;

    protected String appID;

    /**
     * Instantiates a new Abstract server runner.
     *
     * @param tcpPort   the tcp port
     * @param serverDir the server dir
     */
    public AbstractServerRunner(Integer tcpPort, String appID, File serverDir) {
        this.tcpPort = tcpPort;
        this.appID = appID;
        this.serverDir = serverDir;
        this.autostart = false;
        this.serverRunningTimeout = DEFAULT_TIMEOUT_SERVER_RUNNING;
        this.runForce = false;
        this.command = null;
    }

    @Override
    public void setServerDir(File serverDir) {
        this.serverDir = serverDir;
    }

    @Override
    public void enableAutostart() {
        this.autostart = true;
    }

    @Override
    public void enableWebConsole(Integer webPort) {
        this.webPort = webPort;
    }

    @Override
    public void setServerRunningTimeout(Long mills) {
        this.serverRunningTimeout = mills;
    }

    @Override
    public void enableRunForce() {
        this.runForce = true;
    }

    @Override
    public String getCommand() {
        return this.command;
    }
}
