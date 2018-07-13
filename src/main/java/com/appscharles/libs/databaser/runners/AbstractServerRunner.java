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

    /**
     * Instantiates a new Abstract server runner.
     *
     * @param tcpPort   the tcp port
     * @param serverDir the server dir
     */
    public AbstractServerRunner(Integer tcpPort, File serverDir) {
        this.tcpPort = tcpPort;
        this.serverDir = serverDir;
        this.autostart = false;
        this.serverRunningTimeout = DEFAULT_TIMEOUT_SERVER_RUNNING;
        this.runForce = false;
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
}
