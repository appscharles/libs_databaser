package com.appscharles.libs.databaser.managers.client.business.configurations;

import com.appscharles.libs.databaser.managers.configurations.AbstractManagerConfiguration;

import java.io.File;

/**
 * The type Client manager configuration.
 */
public class ClientManagerConfiguration extends AbstractManagerConfiguration {

    private String rememberServerAddress;

    private String rememberDatabaseName;

    private String rememberUser;

    private String rememberPassword;

    private String resourceMigrationPath;

    /**
     * Instantiates a new Client manager configuration.
     *
     * @param serverPort            the server port
     * @param appName               the app name
     * @param serverDir             the server dir
     * @param rememberServerAddress the remember server address
     * @param rememberDatabaseName  the remember database name
     * @param rememberUser          the remember user
     * @param rememberPassword      the remember password
     * @param resourceMigrationPath the resource migration path
     */
    public ClientManagerConfiguration(Integer serverPort, String appName, File serverDir, String rememberServerAddress, String rememberDatabaseName, String rememberUser, String rememberPassword, String resourceMigrationPath) {
        super(serverPort, appName, serverDir, false);
        this.rememberServerAddress = rememberServerAddress;
        this.rememberDatabaseName = rememberDatabaseName;
        this.rememberUser = rememberUser;
        this.rememberPassword = rememberPassword;
        this.resourceMigrationPath = resourceMigrationPath;
    }

    /**
     * Gets remember server address.
     *
     * @return the remember server address
     */
    public String getRememberServerAddress() {
        return rememberServerAddress;
    }

    /**
     * Sets remember server address.
     *
     * @param rememberServerAddress the remember server address
     */
    public void setRememberServerAddress(String rememberServerAddress) {
        this.rememberServerAddress = rememberServerAddress;
    }

    /**
     * Gets remember database name.
     *
     * @return the remember database name
     */
    public String getRememberDatabaseName() {
        return rememberDatabaseName;
    }

    /**
     * Sets remember database name.
     *
     * @param rememberDatabaseName the remember database name
     */
    public void setRememberDatabaseName(String rememberDatabaseName) {
        this.rememberDatabaseName = rememberDatabaseName;
    }

    /**
     * Gets remember user.
     *
     * @return the remember user
     */
    public String getRememberUser() {
        return rememberUser;
    }

    /**
     * Sets remember user.
     *
     * @param rememberUser the remember user
     */
    public void setRememberUser(String rememberUser) {
        this.rememberUser = rememberUser;
    }

    /**
     * Gets remember password.
     *
     * @return the remember password
     */
    public String getRememberPassword() {
        return rememberPassword;
    }

    /**
     * Sets remember password.
     *
     * @param rememberPassword the remember password
     */
    public void setRememberPassword(String rememberPassword) {
        this.rememberPassword = rememberPassword;
    }

    /**
     * Gets resource migration path.
     *
     * @return the resource migration path
     */
    public String getResourceMigrationPath() {
        return resourceMigrationPath;
    }

    /**
     * Sets resource migration path.
     *
     * @param resourceMigrationPath the resource migration path
     */
    public void setResourceMigrationPath(String resourceMigrationPath) {
        this.resourceMigrationPath = resourceMigrationPath;
    }
}
