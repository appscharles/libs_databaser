package com.appscharles.libs.databaser.factories;

import com.appscharles.libs.databaser.builders.H2ConfigurationHibernateBuilder;
import com.appscharles.libs.databaser.builders.SessionFactoryBuilder;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

/**
 * The type H 2 session factory.
 */
public class H2SessionFactory extends AbstractSessionFactory {

    /**
     * Instantiates a new H 2 session factory.
     *
     * @param configuration the configuration
     */
    public H2SessionFactory(Configuration configuration) {
        super(configuration);
    }

    /**
     * Instantiates a new H 2 session factory.
     *
     * @param databaseURL the database url
     * @param username    the username
     * @param password    the password
     */
    public H2SessionFactory(String databaseURL, String username, String password) {
        super(H2ConfigurationHibernateBuilder.create(databaseURL, username, password).build());
    }

    public Session openSession() throws DatabaserException {
        if (this.sessionFactory == null) {
            this.sessionFactory = SessionFactoryBuilder.create(this.configuration).addAnnotationClasses(this.annotationClasses).build();
        }
        return this.sessionFactory.openSession();
    }
}