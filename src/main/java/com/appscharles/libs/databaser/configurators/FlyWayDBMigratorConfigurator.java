package com.appscharles.libs.databaser.configurators;

import io.ebean.config.ServerConfig;

import java.util.Properties;

/**
 * The type Fly way db migrator configurator.
 */
public class FlyWayDBMigratorConfigurator implements IDBMigratorConfigurator {
    @Override
    public ServerConfig config() {
        ServerConfig config = new ServerConfig();
        Properties properties = new Properties();
        properties.put("ebean.migration.applyPrefix", "V");
        config.loadFromProperties(properties);
        return config;
    }
}
