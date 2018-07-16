package com.appscharles.libs.databaser.managers;

import com.appscharles.libs.databaser.exceptions.CallableThrowingConsumer;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.exceptions.ThrowingConsumer;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 * The type Db.
 */
public class DB extends AbstractDB {

    /**
     * Save.
     *
     * @param entity the entity
     * @throws DatabaserException the databaser exception
     */
    public static void save(Object entity) throws DatabaserException {
        save(entity, SFManager.getDefaultSessionFactoryName());
    }

    /**
     * Delete.
     *
     * @param entity the entity
     * @throws DatabaserException the databaser exception
     */
    public static void delete(Object entity) throws DatabaserException {
        delete(entity, SFManager.getDefaultSessionFactoryName());
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
        return get(entityClass, id, SFManager.getDefaultSessionFactoryName());
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
        return getAll(entityClass, SFManager.getDefaultSessionFactoryName());
    }

    /**
     * Commit.
     *
     * @param session the session
     * @throws DatabaserException the databaser exception
     */
    public static void commit(ThrowingConsumer<Session, DatabaserException> session) throws DatabaserException {
        commit(session, SFManager.getDefaultSessionFactoryName());
    }

    /**
     * Session t.
     *
     * @param <T>     the type parameter
     * @param session the session
     * @return the t
     * @throws DatabaserException the databaser exception
     */
    public static <T> T session(CallableThrowingConsumer<Session, T,  DatabaserException> session) throws DatabaserException {
       return session(session, SFManager.getDefaultSessionFactoryName());
    }

}
