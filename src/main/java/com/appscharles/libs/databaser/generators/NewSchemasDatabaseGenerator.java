package com.appscharles.libs.databaser.generators;

import com.appscharles.libs.databaser.builders.SchemaUpdateBuilder;
import com.appscharles.libs.databaser.builders.SchemasGeneratorConfigurationHibernateBuilder;
import com.appscharles.libs.databaser.builders.SessionFactoryBuilder;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.schema.TargetType;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

/**
 * The type New schemas database generator.
 */
public class NewSchemasDatabaseGenerator {

    private File databaseDir;

    private String databaseFilename;

    private List<String> packagesToScan;

    private File migrationsDir;

    /**
     * Instantiates a new New schemas database generator.
     *
     * @param databaseDir      the database dir
     * @param databaseFilename the database filename
     * @param packagesToScan   the packages to scan
     * @param migrationsDir    the migrations dir
     */
    public NewSchemasDatabaseGenerator(File databaseDir, String databaseFilename, List<String> packagesToScan, File migrationsDir) {
        this.databaseDir = databaseDir;
        this.databaseFilename = databaseFilename;
        this.packagesToScan = packagesToScan;
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
                .create(databaseUrl, "update").build();

        SessionFactoryBuilder builder = SessionFactoryBuilder.create(configuration);
        builder.addPackagesToScan(this.packagesToScan);

        StandardServiceRegistry standardServiceRegistry = builder.buildStandardServiceRegistry();
        MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
        for (Class<?> aClass : builder.getAnnotationClasses()) {
            metadataSources.addAnnotatedClass(aClass);
        }
        MetadataImplementor metadataImplementor = (MetadataImplementor) metadataSources.buildMetadata();
        Integer nextVersionMigration = new NextVersionMigrationGenerator(this.migrationsDir).generate();
        File outputFile = new File(this.migrationsDir.getAbsolutePath() + "/v" + nextVersionMigration + "__" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_migration.sql");
        SchemaUpdateBuilder.create(outputFile)
                .build()
                .execute( EnumSet.of(TargetType.SCRIPT), metadataImplementor);
        if (outputFile.exists() && outputFile.length() == 0){
            outputFile.delete();
        }
        SessionFactory sessionFactory = metadataImplementor.buildSessionFactory();
        sessionFactory.close();
    }
}
