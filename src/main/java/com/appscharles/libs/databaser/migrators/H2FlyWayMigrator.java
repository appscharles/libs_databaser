package com.appscharles.libs.databaser.migrators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import org.flywaydb.core.Flyway;

/**
 * The type H 2 fly way migrator.
 */
public class H2FlyWayMigrator extends AbstractFlyWayMigrator {

    private String resourceMigrationPath;

    /**
     * Instantiates a new H 2 fly way migrator.
     *
     * @param databaseUrl           the database url
     * @param username              the username
     * @param password              the password
     * @param resourceMigrationPath the resource migration path
     */
    public H2FlyWayMigrator(String databaseUrl, String username, String password, String resourceMigrationPath) {
        super(databaseUrl, username, password);
        this.resourceMigrationPath = resourceMigrationPath;
    }

    public void migrate() throws DatabaserException{
        Flyway flyway = new Flyway();
        flyway.setLocations("classpath:" + this.resourceMigrationPath);
        flyway.setDataSource("jdbc:h2:"+this.databaseUrl + ";IFEXISTS=TRUE", this.username, this.password);
        flyway.migrate();
    }
}
