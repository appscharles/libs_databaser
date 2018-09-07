package com.appscharles.libs.databaser.builders;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.github.fluent.hibernate.cfg.scanner.EntityScanner;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Session factory builder.
 */
public class SessionFactoryBuilder {

    private Configuration configuration;

    private List<Class> annotationClasses;

    private SessionFactoryBuilder() {
        this.annotationClasses = new ArrayList<>();
    }

    /**
     * Create session factory builder.
     *
     * @param configuration the configuration
     * @return the session factory builder
     */
    public static SessionFactoryBuilder create(Configuration configuration) {
        SessionFactoryBuilder instance = new SessionFactoryBuilder();
        instance.configuration = configuration;
        return instance;
    }

    /**
     * Build session factory.
     *
     * @return the session factory
     * @throws DatabaserException the databaser exception
     */
    public SessionFactory build() throws DatabaserException {
        this.annotationClasses.forEach((c) -> this.configuration.addAnnotatedClass(c));
        StandardServiceRegistry registry = null;
        try {
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
            registryBuilder.applySettings(this.configuration.getProperties());
            registry = registryBuilder.build();
            return this.configuration.buildSessionFactory(registry);
        } catch (Exception e) {
            if (registry != null) {
                StandardServiceRegistryBuilder.destroy(registry);
            }
            throw new DatabaserException(e);
        }
    }

    public StandardServiceRegistry buildStandardServiceRegistry() throws DatabaserException {
        StandardServiceRegistry registry = null;
        try {
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
            registryBuilder.applySettings(this.configuration.getProperties());
            registry = registryBuilder.build();
            return registry;
        } catch (Exception e) {
            if (registry != null) {
                StandardServiceRegistryBuilder.destroy(registry);
            }
            throw new DatabaserException(e);
        }
    }

    /**
     * Add annotation classes session factory builder.
     *
     * @param annotationClasses the annotation classes
     * @return the session factory builder
     */
    public SessionFactoryBuilder addAnnotationClasses(List<Class> annotationClasses) {
        this.annotationClasses.addAll(annotationClasses);
        return this;
    }

    /**
     * Add packages to scan session factory builder.
     *
     * @param packagesToScan the packages to scan
     * @return the session factory builder
     */
    public SessionFactoryBuilder addPackagesToScan(List<String> packagesToScan) {
        if (packagesToScan.size() > 0) {
            this.annotationClasses.addAll(EntityScanner.scanPackages(packagesToScan.toArray(new String[0])).result());
        }
        return this;
    }

    /**
     * Getter for property 'annotationClasses'.
     *
     * @return Value for property 'annotationClasses'.
     */
    public List<Class> getAnnotationClasses() {
        return this.annotationClasses;
    }
}
