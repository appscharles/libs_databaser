package com.appscharles.libs.databaser.managers;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * The type Abstract db session name.
 */
public abstract class AbstractDBSessionName {

    /**
     * Save.
     *
     * @param entity             the entity
     * @param sessionFactoryName the session factory name
     * @throws DatabaserException the databaser exception
     */
    public static void save(Object entity, String sessionFactoryName) throws DatabaserException {
        DB.getSessionFactory(sessionFactoryName).commit((session -> {
            session.saveOrUpdate(entity);
        }));
    }

    /**
     * Delete.
     *
     * @param entity             the entity
     * @param sessionFactoryName the session factory name
     * @throws DatabaserException the databaser exception
     */
    public static void delete(Object entity, String sessionFactoryName) throws DatabaserException {
        DB.getSessionFactory(sessionFactoryName).commit((session -> {
            session.delete(entity);
        }));
    }

    /**
     * Get t.
     *
     * @param <T>                the type parameter
     * @param entityClass        the entity class
     * @param id                 the id
     * @param sessionFactoryName the session factory name
     * @return the t
     * @throws DatabaserException the databaser exception
     */
    public static <T> T get(Class entityClass, Serializable id, String sessionFactoryName) throws DatabaserException {
        Session session = DB.getSessionFactory(sessionFactoryName).openSession();
        return (T) session.get(entityClass, id);
    }

    /**
     * Gets all.
     *
     * @param <T>                the type parameter
     * @param entityClass        the entity class
     * @param id                 the id
     * @param sessionFactoryName the session factory name
     * @return the all
     * @throws DatabaserException the databaser exception
     */
    public static <T extends List> T getAll(Class entityClass, String sessionFactoryName) throws DatabaserException {
        Session session = DB.getSessionFactory(sessionFactoryName).openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        Query<T> q = session.createQuery(criteriaQuery);
        return (T) q.getResultList();
    }
}
