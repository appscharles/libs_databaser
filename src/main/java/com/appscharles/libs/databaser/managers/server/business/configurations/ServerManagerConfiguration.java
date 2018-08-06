package com.appscharles.libs.databaser.managers.server.business.configurations;

import com.appscharles.libs.databaser.managers.configurations.AbstractManagerConfiguration;

import java.io.File;

/**
 * The type Server manager configuration.
 */
public class ServerManagerConfiguration extends AbstractManagerConfiguration {

    /**
     * Instantiates a new Server manager configuration.
     *
     * @param serverPort the server port
     * @param appName    the app name
     * @param serverDir  the server dir
     */
    public ServerManagerConfiguration(Integer serverPort, String appName, File serverDir) {
        super(serverPort, appName, serverDir, false);
    }
}
