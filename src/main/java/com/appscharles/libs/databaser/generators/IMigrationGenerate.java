package com.appscharles.libs.databaser.generators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

/**
 * The interface Migration generate.
 */
public interface IMigrationGenerate {

    /**
     * Generate.
     *
     * @throws DatabaserException the databaser exception
     */
    void generate() throws DatabaserException;
}
