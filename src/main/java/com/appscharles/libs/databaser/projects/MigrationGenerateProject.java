package com.appscharles.libs.databaser.projects;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.generators.MigrationsGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Migration generate project.
 */
public class MigrationGenerateProject {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws DatabaserException the databaser exception
     */
    public static void main(String[] args) throws DatabaserException {
        System.out.println("Start");
        System.out.println(System.getProperty("migration_generate_migrations_dir"));
        File migrationsDir = new File(System.getProperty("migration_generate_migrations_dir"));
        List<String> packagesToScan = new ArrayList<>(Arrays.asList(System.getProperty("migration_generate_packages_to_scan").split(";")));
        MigrationsGenerator generator = new MigrationsGenerator(migrationsDir);
        for (String packageToScan : packagesToScan) {
            generator.addPackageToScan(packageToScan);
        }
        generator.generate();
    }
}
