package com.appscharles.libs.databaser.validators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

/**
 * The interface Permission validator.
 */
public interface IPermissionValidator {

    /**
     * Is access boolean.
     *
     * @return the boolean
     * @throws DatabaserException the databaser exception
     */
    Boolean isAccess() throws DatabaserException;
}
