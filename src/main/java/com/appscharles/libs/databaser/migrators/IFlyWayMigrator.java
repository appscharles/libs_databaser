package com.appscharles.libs.databaser.migrators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

/**
 * The interface Fly way migrator.
 */
public interface IFlyWayMigrator {

    /**
     * Migrate.
     *
     * @throws DatabaserException the databaser exception
     */
    void migrate() throws DatabaserException;
}
