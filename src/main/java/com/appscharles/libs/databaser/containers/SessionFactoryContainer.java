package com.appscharles.libs.databaser.containers;

import com.appscharles.libs.databaser.factories.ISessionFactory;

/**
 * The type Session factory container.
 */
public class SessionFactoryContainer {

    private String name;

    private ISessionFactory sessionFactory;

    private Boolean defaultSessionFactory;

    /**
     * Instantiates a new Session factory container.
     *
     * @param name                  the name
     * @param sessionFactory        the session factory
     * @param defaultSessionFactory the default session factory
     */
    public SessionFactoryContainer(String name, ISessionFactory sessionFactory, Boolean defaultSessionFactory) {
        this.name = name;
        this.sessionFactory = sessionFactory;
        this.defaultSessionFactory = defaultSessionFactory;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets session factory.
     *
     * @return the session factory
     */
    public ISessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Sets session factory.
     *
     * @param sessionFactory the session factory
     */
    public void setSessionFactory(ISessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Gets default session factory.
     *
     * @return the default session factory
     */
    public Boolean getDefaultSessionFactory() {
        return defaultSessionFactory;
    }

    /**
     * Sets default session factory.
     *
     * @param defaultSessionFactory the default session factory
     */
    public void setDefaultSessionFactory(Boolean defaultSessionFactory) {
        this.defaultSessionFactory = defaultSessionFactory;
    }
}
