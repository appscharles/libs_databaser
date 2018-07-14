package com.appscharles.libs.databaser.managers;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

import java.io.Serializable;
import java.util.List;

/**
 * The type Abstract db session.
 */
public abstract class AbstractDBSession extends AbstractDBSessionName {

    /**
     * Save.
     *
     * @param entity the entity
     * @throws DatabaserException the databaser exception
     */
    public static void save(Object entity) throws DatabaserException {
        save(entity, DB.getDefaultSessionFactoryName());
    }

    /**
     * Delete.
     *
     * @param entity the entity
     * @throws DatabaserException the databaser exception
     */
    public static void delete(Object entity) throws DatabaserException {
        delete(entity, DB.getDefaultSessionFactoryName());
    }

    /**
     * Get t.
     *
     * @param <T>         the type parameter
     * @param entityClass the entity class
     * @param id          the id
     * @return the t
     * @throws DatabaserException the databaser exception
     */
    public static <T> T get(Class entityClass, Serializable id) throws DatabaserException {
        return get(entityClass, id, DB.getDefaultSessionFactoryName());
    }

    /**
     * Gets all.
     *
     * @param <T>         the type parameter
     * @param entityClass the entity class
     * @return the all
     * @throws DatabaserException the databaser exception
     */
    public static <T extends List> T getAll(Class entityClass) throws DatabaserException {
        return getAll(entityClass, DB.getDefaultSessionFactoryName());
    }

}
