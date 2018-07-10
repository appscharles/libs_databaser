package com.appscharles.libs.databaser.gradles;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

import java.util.Arrays;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 06.07.2018
 * Time: 20:05
 * Project name: updater
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class MigrationGeneratorPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {

        project.getExtensions().create("generateMigration", MigrationGeneratorPluginExtension.class);

        Task taskGenerateMigration = project.getTasks().create("generateMigration", GenerateMigrationTask.class);
        taskGenerateMigration.getOutputs().upToDateWhen((element)->{ return false; });

        MigrationGeneratorPluginExtension extension = project.getExtensions().findByType(MigrationGeneratorPluginExtension.class);
        if (extension.isDependsOnClean()){
            Task clean = project.getTasks().findByName("clean");
            taskGenerateMigration.shouldRunAfter(clean);
            clean.setDependsOn(Arrays.asList(taskGenerateMigration));
        }
    }
}