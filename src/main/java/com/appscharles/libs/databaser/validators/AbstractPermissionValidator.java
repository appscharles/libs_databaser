package com.appscharles.libs.databaser.validators;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 10.07.2018
 * Time: 15:58
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public abstract class AbstractPermissionValidator implements IPermissionValidator {

    protected String databaseUrl;

    protected String username;

    protected String password;

    /**
     * Instantiates a new Database creator.
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
