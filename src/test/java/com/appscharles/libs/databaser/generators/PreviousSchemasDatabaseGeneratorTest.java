package com.appscharles.libs.databaser.generators;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 07.09.2018
 * Time: 11:43
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class PreviousSchemasDatabaseGeneratorTest extends TestCase {

    @Test
    public void shouldCreateDatabase() throws IOException, DatabaserException {
        File temp = this.temp.newFolder();
        File migrations = this.temp.newFolder();
        PreviousSchemasDatabaseGenerator generator = new PreviousSchemasDatabaseGenerator(temp, "myDB", migrations);
        generator.generate();
        Assert.assertTrue(new File(temp, "myDB.mv.db").exists());
    }
}