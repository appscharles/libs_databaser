package com.appscharles.libs.databaser.generators;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Next version migration generator.
 */
public class NextVersionMigrationGenerator {

    private File migrationsDir;

    /**
     * Instantiates a new Next version migration generator.
     *
     * @param migrationsDir the migrations dir
     */
    public NextVersionMigrationGenerator(File migrationsDir) {
        this.migrationsDir = migrationsDir;
    }

    /**
     * Generate integer.
     *
     * @return the integer
     */
    public Integer generate() {
        Integer version = 0;
        for (String filename : this.migrationsDir.list()) {
            Integer versionFile = getVersionFile(filename);
            if (versionFile != null && versionFile > version){
                version = versionFile;
            }
        }
        return version + 1;
    }

    private Integer getVersionFile(String filename) {
        Pattern pattern = Pattern.compile("v([0-9]+)__");
        Matcher matcher = pattern.matcher(filename);
        while(matcher.find()) {
            return Integer.valueOf(matcher.group(1));
        }
        return null;
    }
}
