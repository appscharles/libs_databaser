package com.appscharles.libs.databaser.gradles;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.generators.MigrationGenerator;
import com.appscharles.libs.fxer.extractors.SimpleArgsExtractor;
import io.ebean.annotation.Platform;

import java.io.File;

/**
 * The type Migration generator gradle.
 */
public class MigrationGeneratorGradle {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws DatabaserException the databaser exception
     */
    public static void main(String[] args) throws DatabaserException {
       MigrationGenerator migrationGenerator = new MigrationGenerator(
               SimpleArgsExtractor.getArg("version", args),
                Platform.valueOf(SimpleArgsExtractor.getArg("platform", args)),
                new File(SimpleArgsExtractor.getArg("toDir", args)));
        migrationGenerator.generate();
    }
}
