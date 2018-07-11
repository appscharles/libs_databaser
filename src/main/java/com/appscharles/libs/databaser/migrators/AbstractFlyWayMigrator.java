package com.appscharles.libs.databaser.migrators;

/**
 * The type Abstract fly way migrator.
 */
public abstract class AbstractFlyWayMigrator implements IFlyWayMigrator {

    /**
     * The Database url.
     */
    protected String databaseUrl;

    /**
     * The Username.
     */
    protected String username;

    /**
     * The Password.
     */
    protected String password;

    /**
     * Instantiates a new Abstract fly way migrator.
     *
     * @param databaseUrl the database url
     * @param username    the username
     * @param password    the password
     */
    public AbstractFlyWayMigrator(String databaseUrl, String username, String password) {
        this.databaseUrl = databaseUrl;
        this.username = username;
        this.password = password;
    }
}
