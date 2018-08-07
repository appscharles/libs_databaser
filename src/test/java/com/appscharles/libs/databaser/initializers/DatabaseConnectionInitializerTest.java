package com.appscharles.libs.databaser.initializers;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.logger.configurators.Log4j2Console;
import com.appscharles.libs.processer.exceptions.ProcesserException;
import com.appscharles.libs.processer.managers.WinKillManager;
import org.apache.logging.log4j.Level;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 06.08.2018
 * Time: 20:49
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class DatabaseConnectionInitializerTest extends TestCase {

    @Test
    public void shouldGetInitializedDataForConnection() throws IOException, DatabaserException, ProcesserException {
        new Log4j2Console(Level.TRACE).config();
        File serverDir = this.temp.newFolder("serverDir_shouldRunWindow");
        new WinKillManager().killCommandLineContains("serverDir_shouldRunWindow");
        File appDir = this.temp.newFolder("appDir");
        IDatabaseConfigurationInitializer initializer = new DatabaseConfigurationInitializer(
                "123AA", new File(appDir, "database.properties"),
                1231, serverDir, "myApp", "com/appscharles/libs/databaser/programs/tester/dBMigrations",
                (sessionFactory) ->{
                    sessionFactory.addPackageToScan("com.appscharles.libs.databaser");
                });
        ((DatabaseConfigurationInitializer) initializer).setTest(true);
        initializer.initialize();
    }
}