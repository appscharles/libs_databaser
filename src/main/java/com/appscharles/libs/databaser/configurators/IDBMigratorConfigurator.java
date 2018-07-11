package com.appscharles.libs.databaser.configurators;

import io.ebean.config.ServerConfig;

/**
 * The interface Idb migrator configurator.
 */
public interface IDBMigratorConfigurator {

    /**
     * Config server config.
     *
     * @return the server config
     */
    ServerConfig config();
}
