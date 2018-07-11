package com.appscharles.libs.databaser.validators;

/**
 * The type Abstract migration validator.
 */
public abstract class AbstractMigrationValidator implements IMigrationValidator {

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
     * Instantiates a new Abstract migration validator.
     *
     * @param databaseUrl the database url
     * @param username    the username
     * @param password    the password
     */
    public AbstractMigrationValidator(String databaseUrl, String username, String password) {
        this.databaseUrl = databaseUrl;
        this.username = username;
        this.password = password;
    }
}
