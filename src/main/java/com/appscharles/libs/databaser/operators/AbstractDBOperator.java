package com.appscharles.libs.databaser.operators;

import com.appscharles.libs.databaser.exceptions.CallableThrowingConsumer;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.exceptions.ThrowingConsumer;
import com.appscharles.libs.databaser.managers.SFManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * The type Abstract db session name.
 */
public abstract class AbstractDBOperator {

    /**
     * Save.
     *
     * @param entity             the entity
     * @param sessionFactoryName the session factory name
     * @throws DatabaserException the databaser exception
     */
    public static void save(Object entity, String sessionFactoryName) throws DatabaserException {
        DBOperator.commit((session -> {
            session.saveOrUpdate(entity);
        }), sessionFactoryName);
    }

    /**
     * Delete.
     *
     * @param entity             the entity
     * @param sessionFactoryName the session factory name
     * @throws DatabaserException the databaser exception
     */
    public static void delete(Object entity, String sessionFactoryName) throws DatabaserException {
        DBOperator.commit((session -> {
            session.delete(entity);
        }), sessionFactoryName);
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
        Session session = SFManager.getSessionFactory(sessionFactoryName).openSession();
        return (T) session.get(entityClass, id);
    }

    /**
     * Gets all.
     *
     * @param <T>                the type parameter
     * @param entityClass        the entity class
     * @param sessionFactoryName the session factory name
     * @return the all
     * @throws DatabaserException the databaser exception
     */
    public static <T extends List> T getAll(Class entityClass, String sessionFactoryName) throws DatabaserException {
        Session session = SFManager.getSessionFactory(sessionFactoryName).openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        Query<T> q = session.createQuery(criteriaQuery);
        return (T) q.getResultList();
    }

    /**
     * Commit.
     *
     * @param session            the session
     * @param sessionFactoryName the session factory name
     * @throws DatabaserException the databaser exception
     */
    public static void commit(ThrowingConsumer<Session, DatabaserException> session, String sessionFactoryName) throws DatabaserException {
        Session s = SFManager.getSessionFactory(sessionFactoryName).openSession();
        Transaction t = s.beginTransaction();
        session.accept(s);
        t.commit();
        s.close();
    }

    public static <T> T session(CallableThrowingConsumer<Session, T,  DatabaserException> session, String sessionFactoryName) throws DatabaserException {
        Session s = SFManager.getSessionFactory(sessionFactoryName).openSession();
        Object sessionResult = session.accept(s);
        s.close();
        return (T)sessionResult;
    }
}
