package com.appscharles.libs.databaser.extractors;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 13.07.2018
 * Time: 11:02
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class H2JarExtractorTest extends TestCase {

    @Test
    public void shouldExtractH2Jar() throws IOException, DatabaserException {
        File jarFile = this.temp.newFile("h2.jar");
        new H2JarExtractor().toFile("/com/appscharles/libs/databaser/jars/h2database.jar", jarFile);
        Assert.assertTrue(jarFile.exists());
        Assert.assertTrue(jarFile.length() > 100);
    }
}