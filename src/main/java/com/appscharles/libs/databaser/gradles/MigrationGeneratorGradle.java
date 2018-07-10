package com.appscharles.libs.databaser.gradles;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.extractors.SimpleArgsExtractor;
import com.appscharles.libs.databaser.generators.MigrationGenerator;
import io.ebean.annotation.Platform;

import java.io.File;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 09.07.2018
 * Time: 11:57
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class MigrationGeneratorGradle {

    public static void main(String[] args) throws DatabaserException {
       MigrationGenerator migrationGenerator = new MigrationGenerator(
               SimpleArgsExtractor.getArg("version", args),
                Platform.valueOf(SimpleArgsExtractor.getArg("platform", args)),
                new File(SimpleArgsExtractor.getArg("toDir", args)));
        migrationGenerator.generate();
    }
}
