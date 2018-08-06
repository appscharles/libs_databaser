package com.appscharles.libs.databaser.migrators;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.creators.DatabaseCreator;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.runners.IServerRunner;
import com.appscharles.libs.databaser.runners.ServerRunner;
import com.appscharles.libs.processer.exceptions.ProcesserException;
import com.appscharles.libs.processer.managers.WinKillManager;
import org.flywaydb.core.internal.exception.FlywaySqlException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 10.07.2018
 * Time: 15:53
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class FlyWayMigratorTest extends TestCase {

    @Test
    public void shouldMigrate() throws IOException, DatabaserException, ProcesserException {
        new WinKillManager().killCommandLineContains("dBDir_shouldMigrate");
        File dBDir = this.temp.newFolder("dBDir_shouldMigrate");
        IServerRunner runner = new ServerRunner(12323, "myApp");
        runner.enableRunForce();
        runner.setServerDir(dBDir);
        runner.start();
        DatabaseCreator creator = new DatabaseCreator("tcp://localhost:"+12323+"/myDB", "root", "secret");
        creator.create();
        FlyWayMigrator migrator = new FlyWayMigrator("tcp://localhost:"+12323+"/myDB", "root"  ,"secret",
                "com/appscharles/libs/databaser/programs/tester/dBMigrations");
        migrator.migrate();
        runner.stop();
    }

    @Test(expected = FlywaySqlException.class)
    public void shouldThrowExceptionNotExistDatabase() throws IOException, DatabaserException, ProcesserException {
       new WinKillManager().killCommandLineContains("dBDir_shouldMigrate");
        File dBDir = this.temp.newFolder("dBDir_shouldMigrate");
        IServerRunner runner = new ServerRunner(12333, "myApp");
        runner.enableRunForce();
        runner.setServerDir(dBDir);
        runner.start();
        FlyWayMigrator migrator = new FlyWayMigrator("tcp://localhost:"+12333+"/myDB", "root"  ,"secret",
                "com/appscharles/libs/databaser/programs/tester/dbmigration");
        migrator.migrate();
        runner.stop();
    }
}