package com.appscharles.libs.databaser.managers;

import com.appscharles.libs.databaser.containers.SessionFactoryContainer;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.factories.ISessionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Session factory manager.
 */
public class SFManager {

    private static final Map<String, SessionFactoryContainer> sessionFactoryContainers = new HashMap<>();

    private static void undefaultSessionFactories() {
        sessionFactoryContainers.forEach((sessionFactoryName, sessionFactoryContainer)->{
            sessionFactoryContainer.setDefaultSessionFactory(false);
        });
    }
    
    /**
     * Add session factory.
     *
     * @param name                  the name
     * @param sessionFactory        the session factory
     * @param defaultSessionFactory the default session factory
     * @throws DatabaserException the databaser exception
     */
    public synchronized static void addSessionFactory(String sessionFactoryName, ISessionFactory sessionFactory, Boolean defaultSessionFactory) throws DatabaserException {
        if (sessionFactoryContainers.containsKey(sessionFactoryName)) {
            throw new DatabaserException("Session factory '" + sessionFactoryName + "' is exist.");
        }
        if (defaultSessionFactory) {
            undefaultSessionFactories();
        }
        sessionFactoryContainers.put(sessionFactoryName, new SessionFactoryContainer(sessionFactoryName, sessionFactory, defaultSessionFactory));
    }

    /**
     * Gets default.
     *
     * @return the default
     * @throws DatabaserException the databaser exception
     */
    public static ISessionFactory getDefaultSessionFactory() throws DatabaserException {
        for (Map.Entry<String, SessionFactoryContainer> entry : sessionFactoryContainers.entrySet()) {
            if (entry.getValue().getDefaultSessionFactory()) {
                return entry.getValue().getSessionFactory();
            }
        }
        throw new DatabaserException("Not found default session factory.");
    }

    public static String getDefaultSessionFactoryName() throws DatabaserException {
        for (Map.Entry<String, SessionFactoryContainer> entry : sessionFactoryContainers.entrySet()) {
            if (entry.getValue().getDefaultSessionFactory()) {
                return entry.getKey();
            }
        }
        throw new DatabaserException("Not found default session factory.");
    }

    /**
     * Get session factory.
     *
     * @param name the name
     * @return the session factory
     * @throws DatabaserException the databaser exception
     */
    public static ISessionFactory getSessionFactory(String sessionFactoryName) throws DatabaserException {
        for (Map.Entry<String, SessionFactoryContainer> entry : sessionFactoryContainers.entrySet()) {
            if (entry.getKey().equals(sessionFactoryName)) {
                return entry.getValue().getSessionFactory();
            }
        }
        throw new DatabaserException("Not found '" + sessionFactoryName + "' session factory.");
    }

    /**
     * Sets default.
     *
     * @param name the name
     * @throws DatabaserException the databaser exception
     */
    public synchronized static void setDefaultSessionFactory(String sessionFactoryName) throws DatabaserException {
        for (Map.Entry<String, SessionFactoryContainer> entry : sessionFactoryContainers.entrySet()) {
            if (entry.getKey().equals(sessionFactoryName)) {
                undefaultSessionFactories();
                entry.getValue().setDefaultSessionFactory(true);
                return;
            }
        }
        throw new DatabaserException("Not found '" + sessionFactoryName + "' session factory.");
    }

    /**
     * Close.
     *
     * @param name the name
     * @throws DatabaserException the databaser exception
     */
    public static void closeSessionFactory(String sessionFactoryName) throws DatabaserException {
        for (Map.Entry<String, SessionFactoryContainer> entry : sessionFactoryContainers.entrySet()) {
            if (entry.getKey().equals(sessionFactoryName)) {
                entry.getValue().getSessionFactory().closeSessionFactory();
                return;
            }
        }
        throw new DatabaserException("Not found '" + sessionFactoryName + "' session factory.");
    }

    /**
     * Close default.
     *
     * @throws DatabaserException the databaser exception
     */
    public static void closeDefaultSessionFactory() throws DatabaserException {
        for (Map.Entry<String, SessionFactoryContainer> entry : sessionFactoryContainers.entrySet()) {
            if (entry.getValue().getDefaultSessionFactory()) {
                entry.getValue().getSessionFactory().closeSessionFactory();
            return;
            }
        }
        throw new DatabaserException("Not found default session factory.");
    }

    /**
     * Close.
     *
     * @throws DatabaserException the databaser exception
     */
    public static void closeSessionFactories() throws DatabaserException {
        for (Map.Entry<String, SessionFactoryContainer> entry : sessionFactoryContainers.entrySet()) {
            entry.getValue().getSessionFactory().closeSessionFactory();
        }
    }
}
