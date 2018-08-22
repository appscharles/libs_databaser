package com.appscharles.libs.databaser.configurators;

import java.util.Properties;

/**
 * The type Hibernate c 3 p 0 configurator.
 */
public class HibernateC3P0Configurator {

    private static Properties properties;

    /**
     * Gets properties.
     *
     * @return the properties
     */
    public static Properties getProperties() {
        return properties;
    }

    /**
     * Sets properties.
     *
     * @param properties the properties
     */
    public static void setProperties(Properties properties) {
        HibernateC3P0Configurator.properties = properties;
    }
}
