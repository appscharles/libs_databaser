package com.appscharles.libs.databaser.builders;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

/**
 * The type Schemas generator configuration hibernate builder.
 */
public class SchemasGeneratorConfigurationHibernateBuilder {

    private Configuration configuration;

    private String databaseURL;

    private String hbm2ddl;

    private SchemasGeneratorConfigurationHibernateBuilder() {
        this.configuration = new Configuration();
    }

    /**
     * Create schemas generator configuration hibernate builder.
     *
     * @param databaseURL the database url
     * @param hbm2ddl     the hbm 2 ddl
     * @return the schemas generator configuration hibernate builder
     */
    public static SchemasGeneratorConfigurationHibernateBuilder create(String databaseURL , String hbm2ddl){
        SchemasGeneratorConfigurationHibernateBuilder instance = new SchemasGeneratorConfigurationHibernateBuilder();
        instance.databaseURL = databaseURL;
        instance.hbm2ddl = hbm2ddl;
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
        props.put(Environment.URL, this.databaseURL);
        props.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
        props.put("hibernate.hbm2ddl.auto", this.hbm2ddl);
        this.configuration.addProperties(props);
        return this.configuration;
    }
}
