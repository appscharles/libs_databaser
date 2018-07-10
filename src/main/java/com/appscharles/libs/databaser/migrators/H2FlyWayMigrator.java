package com.appscharles.libs.databaser.migrators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import org.flywaydb.core.Flyway;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 10.07.2018
 * Time: 15:50
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class H2FlyWayMigrator extends AbstractFlyWayMigrator {

    private String resourceMigrationPath;
    /**
     * Instantiates a new Database creator.
     *
     * @param databaseUrl the database url
     * @param username    the username
     * @param password    the password
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
