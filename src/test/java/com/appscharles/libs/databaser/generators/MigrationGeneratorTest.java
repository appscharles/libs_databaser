package com.appscharles.libs.databaser.generators;

import com.appscharles.libs.databaser.TestCase;
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
public class MigrationGeneratorTest extends TestCase {

    @Test
    public void shouldGenerateSQLMigrations() throws DatabaserException, IOException {
        File temp = this.temp.newFolder("shouldGenerateSQLMigrations");
        MigrationGenerator migrationGenerator = new MigrationGenerator(
                "1.0.0.0",
                Platform.H2,
                temp);
        migrationGenerator.generate();
        Assert.assertTrue(DirReader.getFiles(new File(temp, "dbmigration")).size() > 1);
    }
}