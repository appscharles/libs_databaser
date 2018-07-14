package com.appscharles.libs.databaser.factories;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Abstract session factory.
 */
public abstract class AbstractSessionFactory implements ISessionFactory{

    /**
     * The Session factory.
     */
    protected SessionFactory sessionFactory;

    /**
     * The Configuration.
     */
    protected Configuration configuration;

    protected List<Class> annotationClasses;

    protected List<String> packagesToScan;

    /**
     * Instantiates a new Abstract session factory.
     *
     * @param configuration the configuration
     */
    public AbstractSessionFactory(Configuration configuration) {
        this.configuration = configuration;
        this.annotationClasses = new ArrayList<>();
        this.packagesToScan = new ArrayList<>();
    }

    public void closeSessionFactory() throws DatabaserException {
        try {
            if (this.sessionFactory != null) {
                this.sessionFactory.close();
            }
        } catch (Exception e) {
            throw new DatabaserException(e);
        }
    }

    @Override
    public void addAnnotationClass(Class aClass) {
        this.annotationClasses.add(aClass);
    }

    @Override
    public void addPackageToScan(String packageName) {
        this.packagesToScan.add(packageName);
    }

}
