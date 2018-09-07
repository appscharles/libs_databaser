package com.appscharles.libs.databaser.generators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.google.common.io.Files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Migrations generator.
 */
public class MigrationsGenerator {

    private File migrationsDir;

    private File tempDir;

    private List<String> packagesToScan;

    /**
     * Instantiates a new Migrations generator.
     *
     * @param migrationsDir the migrations dir
     */
    public MigrationsGenerator(File migrationsDir) {
        this(migrationsDir, null);
    }

    /**
     * Instantiates a new Migrations generator.
     *
     * @param migrationsDir the migrations dir
     * @param packageToScan the package to scan
     */
    public MigrationsGenerator(File migrationsDir, String packageToScan) {
        this.packagesToScan = new ArrayList<>();
        this.migrationsDir = migrationsDir;
        if (packageToScan != null){
            this.packagesToScan.add(packageToScan);
        }
        this.tempDir = Files.createTempDir();
    }

    /**
     * Generate.
     *
     * @throws DatabaserException the databaser exception
     */
    public void generate() throws DatabaserException {
        PreviousSchemasDatabaseGenerator generator = new PreviousSchemasDatabaseGenerator(this.tempDir, "GenerateDll", this.migrationsDir);
        generator.generate();
        NewSchemasDatabaseGenerator schemasGenerator = new NewSchemasDatabaseGenerator(this.tempDir, "GenerateDll", this.packagesToScan,  this.migrationsDir);
        schemasGenerator.generate();
    }

    /**
     * Add package to scan migrations generator.
     *
     * @param packageToScan the package to scan
     * @return the migrations generator
     */
    public MigrationsGenerator addPackageToScan(String packageToScan){
        this.packagesToScan.add(packageToScan);
        return this;
    }
}
