package com.appscharles.libs.databaser.creators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

/**
 * The interface Database creator.
 */
public interface IDatabaseCreator {

    /**
     * Create.
     *
     * @throws DatabaserException the databaser exception
     */
    void create() throws DatabaserException;
}
