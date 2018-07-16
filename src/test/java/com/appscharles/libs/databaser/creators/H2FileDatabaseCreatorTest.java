package com.appscharles.libs.databaser.creators;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.validators.H2FileDatabaseExistValidator;
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
public class H2FileDatabaseCreatorTest extends TestCase {

    @Test
    public void shouldCreateDatabaseFile() throws IOException, DatabaserException {
        File serverDir = this.temp.newFolder("serverFir_createDatabaseFile");
        new H2FileDatabaseCreator("myDatabase", "root", "secret", serverDir).create();
        Assert.assertTrue(H2FileDatabaseExistValidator.exist("myDatabase", serverDir));
    }
}