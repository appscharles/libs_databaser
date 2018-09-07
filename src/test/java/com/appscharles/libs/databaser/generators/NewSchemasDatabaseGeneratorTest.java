package com.appscharles.libs.databaser.generators;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.ioer.services.DirDeleter;
import com.appscharles.libs.ioer.services.FileReader;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 07.09.2018
 * Time: 12:14
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class NewSchemasDatabaseGeneratorTest extends TestCase {

    @Test
    public void shouldCreateDllFile() throws IOException, DatabaserException {
        File tempDatabase = new File("E:\\others\\tests\\libs\\databaser\\GeneratorDll\\Database");
        File tempMigrations = new File("E:\\others\\tests\\libs\\databaser\\GeneratorDll\\Migrations");
        DirDeleter.delete(tempDatabase);
        DirDeleter.delete(tempMigrations);
        NewSchemasDatabaseGenerator generator = new NewSchemasDatabaseGenerator(tempDatabase, "myDB", Arrays.asList("com.appscharles.libs.databaser.programs.tester"), tempMigrations);
        generator.generate();
        Assert.assertTrue(tempMigrations.list().length>0);
        String sql = FileReader.read(new File(tempMigrations, tempMigrations.list()[0]));
        System.out.println(sql);
        Assert.assertTrue(sql.isEmpty() == false);
    }
}