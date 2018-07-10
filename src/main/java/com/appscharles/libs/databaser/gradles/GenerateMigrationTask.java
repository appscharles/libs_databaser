package com.appscharles.libs.databaser.gradles;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.generators.MigrationGenerator;
import io.ebean.annotation.Platform;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 06.07.2018
 * Time: 20:09
 * Project name: updater
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class GenerateMigrationTask extends DefaultTask {

    @TaskAction
    public void generateMigration() throws DatabaserException {
        MigrationGeneratorPluginExtension extension = getProject().getExtensions().findByType(MigrationGeneratorPluginExtension.class);
        if (extension == null) {
            extension = new MigrationGeneratorPluginExtension();
        }
        System.out.println("Generate migration to dir: " + extension.getToDir());
        MigrationGenerator migrationGenerator = new MigrationGenerator(Platform.valueOf(extension.getPlatform()), new File(extension.getToDir()));
        migrationGenerator.generate();
    }
}
