package com.appscharles.libs.databaser.generators;

import com.appscharles.libs.databaser.builders.SchemasGeneratorConfigurationHibernateBuilder;
import com.appscharles.libs.databaser.builders.SessionFactoryBuilder;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

/**
 * The type Previous schemas database generator.
 */
public class PreviousSchemasDatabaseGenerator {

    private File databaseDir;

    private String databaseFilename;

    private File migrationsDir;

    /**
     * Instantiates a new Previous schemas database generator.
     *
     * @param databaseDir      the database dir
     * @param databaseFilename the database filename
     * @param migrationsDir    the migrations dir
     */
    public PreviousSchemasDatabaseGenerator(File databaseDir, String databaseFilename,File migrationsDir) {
        this.databaseDir = databaseDir;
        this.databaseFilename = databaseFilename;
        this.migrationsDir = migrationsDir;
    }

    /**
     * Generate.
     *
     * @throws DatabaserException the databaser exception
     */
    public void generate() throws DatabaserException {
        if (databaseDir.exists() == false){
            this.databaseDir.mkdirs();
        }
        String databaseUrl = "jdbc:h2:file:" + this.databaseDir.getAbsolutePath() + "/" + this.databaseFilename;
        Configuration configuration= SchemasGeneratorConfigurationHibernateBuilder
                .create(databaseUrl, "create").build();
        SessionFactory sessionFactory = SessionFactoryBuilder.create(configuration).build();
        sessionFactory.close();
        Flyway flyway = new Flyway();
        flyway.setLocations("filesystem:" +this.migrationsDir.getAbsolutePath());
        flyway.setSqlMigrationPrefix("v");
        flyway.setDataSource(databaseUrl+ ";IFEXISTS=TRUE", "", "");
        flyway.migrate();
    }
}
