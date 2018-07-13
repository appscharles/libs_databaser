package com.appscharles.libs.databaser.builders;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

/**
 * The type H 2 configuration hibernate builder.
 */
public class H2ConfigurationHibernateBuilder {

    private Configuration configuration;

    private String databaseURL;

    private String user;

    private String password;

    private H2ConfigurationHibernateBuilder() {
        this.configuration = new Configuration();
    }

    /**
     * Create h 2 configuration hibernate builder.
     *
     * @param databaseURL the database url
     * @param user        the user
     * @param password    the password
     * @return the h 2 configuration hibernate builder
     */
    public static H2ConfigurationHibernateBuilder create(String databaseURL , String user, String password){
        H2ConfigurationHibernateBuilder instance = new H2ConfigurationHibernateBuilder();
        instance.databaseURL = databaseURL;
        instance.user = user;
        instance.password = password;
        return instance;
    }

    /**
     * Build configuration.
     *
     * @return the configuration
     */
    public Configuration build(){
        Properties props = new Properties();
        props.put(Environment.DRIVER, "org.h2.Driver");
        props.put(Environment.URL, "jdbc:h2:"+ this.databaseURL +";IFEXISTS=TRUE");
        props.put(Environment.USER, this.user);
        props.put(Environment.PASS, this.password);
        props.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
        this.configuration.addProperties(props);
        return this.configuration;
    }
}
