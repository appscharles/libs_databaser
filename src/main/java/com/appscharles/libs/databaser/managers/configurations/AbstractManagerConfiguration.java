package com.appscharles.libs.databaser.managers.configurations;

import com.appscharles.libs.databaser.runners.IServerSetDirectionable;

import java.io.File;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 06.08.2018
 * Time: 13:40
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public abstract class AbstractManagerConfiguration implements IServerPortable, IAppNameable, IServerSetDirectionable, IServerGetDirectionable, ITestable {

    private Integer serverPort;

    private String appName;

    private File serverDir;

    private Boolean test;

    public AbstractManagerConfiguration(Integer serverPort, String appName, File serverDir, Boolean test) {
        this.serverPort = serverPort;
        this.appName = appName;
        this.serverDir = serverDir;
        this.test = test;
    }

    /**
     * Getter for property 'serverPort'.
     *
     * @return Value for property 'serverPort'.
     */
    public Integer getServerPort() {
        return serverPort;
    }

    /**
     * Setter for property 'serverPort'.
     *
     * @param serverPort Value to set for property 'serverPort'.
     */
    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * Getter for property 'appName'.
     *
     * @return Value for property 'appName'.
     */
    public String getAppName() {
        return appName;
    }

    /**
     * Setter for property 'appName'.
     *
     * @param appName Value to set for property 'appName'.
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * Getter for property 'serverDir'.
     *
     * @return Value for property 'serverDir'.
     */
    public File getServerDir() {
        return serverDir;
    }

    /**
     * Setter for property 'serverDir'.
     *
     * @param serverDir Value to set for property 'serverDir'.
     */
    public void setServerDir(File serverDir) {
        this.serverDir = serverDir;
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
