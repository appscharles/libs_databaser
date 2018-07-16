package com.appscharles.libs.databaser.managers.server.business.configurations;

import java.io.File;

/**
 * The type Server manager configuration.
 */
public class ServerManagerConfiguration {

    private File serverDir;

    private String appName;

    private Integer serverPort;

    private Boolean test;

    /**
     * Instantiates a new Server manager configuration.
     *
     * @param serverPort the server port
     * @param appName    the app name
     * @param serverDir  the server dir
     */
    public ServerManagerConfiguration(Integer serverPort, String appName, File serverDir) {
        this.serverPort = serverPort;
        this.appName = appName;
        this.serverDir = serverDir;
        this.test = false;
    }

    /**
     * Gets app name.
     *
     * @return the app name
     */
    public String getAppName() {
        return appName;
    }

    /**
     * Sets app name.
     *
     * @param appName the app name
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * Gets server dir.
     *
     * @return the server dir
     */
    public File getServerDir() {
        return serverDir;
    }

    /**
     * Sets server dir.
     *
     * @param serverDir the server dir
     */
    public void setServerDir(File serverDir) {
        this.serverDir = serverDir;
    }

    /**
     * Gets server port.
     *
     * @return the server port
     */
    public Integer getServerPort() {
        return serverPort;
    }

    /**
     * Sets server port.
     *
     * @param serverPort the server port
     */
    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * Getter for property 'test'.
     *
     * @return Value for property 'test'.
     */
    public Boolean getTest() {
        return test;
    }

    /**
     * Setter for property 'test'.
     *
     * @param test Value to set for property 'test'.
     */
    public void setTest(Boolean test) {
        this.test = test;
    }
}
