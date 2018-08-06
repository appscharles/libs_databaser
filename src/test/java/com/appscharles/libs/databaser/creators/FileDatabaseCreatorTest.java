package com.appscharles.libs.databaser.creators;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.validators.FileDatabaseExistValidator;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 16.07.2018
 * Time: 16:49
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class FileDatabaseCreatorTest extends TestCase {

    @Test
    public void shouldCreateDatabaseFile() throws IOException, DatabaserException {
        File serverDir = this.temp.newFolder("serverFir_createDatabaseFile");
        new DatabaseCreator("file:"+serverDir.getAbsolutePath() + File.separator+"myDatabase", "root", "secret").create();
        Assert.assertTrue(FileDatabaseExistValidator.exist("myDatabase", serverDir));
    }
}