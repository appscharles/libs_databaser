package com.appscharles.libs.databaser.validators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

/**
 * The type H 2 migration validator.
 */
public class MigrationValidator extends AbstractMigrationValidator {

    private static final Logger logger = LogManager.getLogger(ServerRunningValidator.class);

    private String resourceMigrationPath;

    /**
     * Instantiates a new H 2 migration validator.
     *
     * @param databaseUrl           the database url
     * @param username              the username
     * @param password              the password
     * @param resourceMigrationPath the resource migration path
     */
    public MigrationValidator(String databaseUrl, String username, String password, String resourceMigrationPath) {
        super(databaseUrl, username, password);
        this.resourceMigrationPath = resourceMigrationPath;
    }

    @Override
    public Boolean isValid(){
        Flyway flyway = new Flyway();
        flyway.setLocations("classpath:" + this.resourceMigrationPath);
        flyway.setDataSource("jdbc:h2:"+this.databaseUrl + ";IFEXISTS=TRUE", this.username, this.password);
        flyway.setSqlMigrationPrefix("v");
        try {
            flyway.validate();
            return true;
        } catch (FlywayException e) {
            logger.debug(e, e);
            return false;
        }
    }
}
