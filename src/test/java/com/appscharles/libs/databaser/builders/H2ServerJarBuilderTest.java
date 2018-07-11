package com.appscharles.libs.databaser.builders;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.configurators.H2ServerConfigurator;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 11.07.2018
 * Time: 06:56
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class H2ServerJarBuilderTest extends TestCase {

    @Test
    public void shouldCreateH2ServerJar() throws IOException, DatabaserException {
        File jarFile = this.temp.newFile(H2ServerConfigurator.getH2ServerFileName());
        H2ServerJarBuilder.create(jarFile).build();
        Assert.assertTrue(jarFile.exists());
    }
}