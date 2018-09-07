package com.appscharles.libs.databaser.generators;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.ioer.services.FileReader;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 07.09.2018
 * Time: 13:39
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class MigrationsGeneratorTest extends TestCase {

    @Test
    public void shouldCreateDllFiles() throws IOException, DatabaserException, InterruptedException {
        File migrations = this.temp.newFolder();
        MigrationsGenerator generator = new MigrationsGenerator(migrations, "com.appscharles.libs.databaser.programs.tester");
        generator.generate();
        Assert.assertTrue(migrations.list().length ==1);
        Assert.assertTrue(FileReader.read(new File(migrations, migrations.list()[0])).isEmpty() == false);
        Thread.sleep(2000);
        generator = new MigrationsGenerator(migrations, "com.appscharles.libs.databaser.programs.seconder");
        generator.generate();
        Thread.sleep(2000);
        Assert.assertTrue(migrations.list().length ==2);
        Assert.assertTrue(FileReader.read(new File(migrations, migrations.list()[1])).isEmpty() == false);
    }
}