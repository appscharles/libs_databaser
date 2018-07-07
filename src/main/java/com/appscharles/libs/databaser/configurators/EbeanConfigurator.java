package com.appscharles.libs.databaser.configurators;

import io.ebean.config.ServerConfig;

import java.util.Properties;

/**
 * The type Ebean configurator.
 */
public class EbeanConfigurator {

    /**
     * Get config server config.
     *
     * @param username    the username
     * @param password    the password
     * @param databaseUrl the database url
     * @return the server config
     */
    public static ServerConfig getConfig(String username, String password, String databaseUrl){
        Properties properties = new Properties();
        properties.put("datasource.db.username", username);
        properties.put("datasource.db.password", password);
        properties.put("datasource.db.databaseUrl", "jdbc:h2:" + databaseUrl);
        properties.put("datasource.db.databaseDriver", "org.h2.Driver");
        ServerConfig config = new ServerConfig();
        config.loadFromProperties(properties);
        return config;
    }
}
