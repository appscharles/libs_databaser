package com.appscharles.libs.databaser.managers;

import com.appscharles.libs.databaser.containers.SessionFactoryContainer;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.factories.ISessionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Sf manager.
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
     * @param sessionFactoryName    the session factory name
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
     * Gets default session factory.
     *
     * @return the default session factory
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

    /**
     * Gets default session factory name.
     *
     * @return the default session factory name
     * @throws DatabaserException the databaser exception
     */
    public static String getDefaultSessionFactoryName() throws DatabaserException {
        for (Map.Entry<String, SessionFactoryContainer> entry : sessionFactoryContainers.entrySet()) {
            if (entry.getValue().getDefaultSessionFactory()) {
                return entry.getKey();
            }
        }
        throw new DatabaserException("Not found default session factory.");
    }

    /**
     * Gets session factory.
     *
     * @param sessionFactoryName the session factory name
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

    public static Boolean existSessionFactory(String sessionFactoryName) throws DatabaserException {
        for (Map.Entry<String, SessionFactoryContainer> entry : sessionFactoryContainers.entrySet()) {
            if (entry.getKey().equals(sessionFactoryName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets default session factory.
     *
     * @param sessionFactoryName the session factory name
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
     * Close session factory.
     *
     * @param sessionFactoryName the session factory name
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
     * Close default session factory.
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
     * Close session factories.
     *
     * @throws DatabaserException the databaser exception
     */
    public static void closeSessionFactories() throws DatabaserException {
        for (Map.Entry<String, SessionFactoryContainer> entry : sessionFactoryContainers.entrySet()) {
            entry.getValue().getSessionFactory().closeSessionFactory();
        }
    }
}
