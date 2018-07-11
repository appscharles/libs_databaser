package com.appscharles.libs.databaser.validators;

/**
 * The type Abstract permission validator.
 */
public abstract class AbstractPermissionValidator implements IPermissionValidator {

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
     * Instantiates a new Abstract permission validator.
     *
     * @param databaseUrl the database url
     * @param username    the username
     * @param password    the password
     */
    public AbstractPermissionValidator(String databaseUrl, String username, String password) {
        this.databaseUrl = databaseUrl;
        this.username = username;
        this.password = password;
    }
}
