package com.appscharles.libs.databaser.creators;

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
 * Time: 07:02
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class H2ServerJarCreatorTest extends TestCase {

    @Test
    public void shouldCreateJarFileInLocalization() throws IOException, DatabaserException {
        File temp = this.temp.newFolder("temp");
        IServerJarCreator creator = new H2ServerJarCreator();
        creator.setServerDir(temp);
        creator.create();
        Assert.assertTrue(new File(temp, H2ServerConfigurator.getH2ServerFileName()).exists());
    }
}