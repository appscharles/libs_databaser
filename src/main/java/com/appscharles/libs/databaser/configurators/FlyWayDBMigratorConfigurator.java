package com.appscharles.libs.databaser.configurators;

import io.ebean.config.ServerConfig;

import java.util.Properties;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 10.07.2018
 * Time: 16:19
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
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
