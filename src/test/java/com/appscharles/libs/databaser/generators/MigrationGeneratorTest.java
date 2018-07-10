package com.appscharles.libs.databaser.generators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.ioer.services.DirReader;
import io.ebean.annotation.Platform;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 10.07.2018
 * Time: 11:23
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class MigrationGeneratorTest {

    @Test
    public void shouldGenerateSQLMigrations() throws DatabaserException, IOException {
        MigrationGenerator migrationGenerator = new MigrationGenerator(
                Platform.H2,
                new File("src/main/java/com/appscharles/libs/databaser/programs/manager"));
        migrationGenerator.generate();
        Assert.assertTrue(DirReader.getFiles(new File("src/main/java/com/appscharles/libs/databaser/programs/manager/dbmigration")).size() > 1);
    }
}