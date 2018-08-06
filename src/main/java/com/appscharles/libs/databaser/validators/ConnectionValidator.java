package com.appscharles.libs.databaser.validators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

/**
 * The type Connection validator.
 */
public class ConnectionValidator {

    /**
     * Is valid boolean.
     *
     * @param databaseUrl           the database url
     * @param user                  the user
     * @param password              the password
     * @param resourceMigrationPath the resource migration path
     * @return the boolean
     * @throws DatabaserException the databaser exception
     */
    public static Boolean isValid(String databaseUrl, String user, String password, String resourceMigrationPath) throws DatabaserException {
        if (ServerRunningValidator.isRunning(databaseUrl) == false) {
            return false;
        }
        if (DatabaseExistValidator.exist(databaseUrl) == false) {
            return false;
        }

        if (new PermissionValidator(databaseUrl, user, password).isAccess() == false) {
            return false;
        }

        MigrationValidator validator = new MigrationValidator(databaseUrl, user  ,password, resourceMigrationPath);
        if (validator.isValid() == false){
            return false;
        }

        return true;
    }
}
