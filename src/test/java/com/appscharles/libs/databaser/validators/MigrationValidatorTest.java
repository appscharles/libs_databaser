package com.appscharles.libs.databaser.validators;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.creators.DatabaseCreator;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.migrators.FlyWayMigrator;
import com.appscharles.libs.databaser.runners.IServerRunner;
import com.appscharles.libs.databaser.runners.ServerRunner;
import com.appscharles.libs.processer.exceptions.ProcesserException;
import com.appscharles.libs.processer.managers.WinKillManager;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 10.07.2018
 * Time: 16:22
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class MigrationValidatorTest extends TestCase {

    @Test
    public void shouldValidDatabase() throws IOException, DatabaserException, ProcesserException {
        File dBDir = this.temp.newFolder("dBDir_shouldIsNotValidDatabase");
        new WinKillManager().killCommandLineContains("dBDir_shouldIsNotValidDatabase");
        IServerRunner runner = new ServerRunner(12423, "myApp");
        runner.enableRunForce();
        runner.setServerDir(dBDir);
        runner.start();
        DatabaseCreator creator = new DatabaseCreator("tcp://localhost:"+12423+"/myDB", "root", "secret");
        creator.create();
        FlyWayMigrator migrator = new FlyWayMigrator("tcp://localhost:"+12423+"/myDB", "root"  ,"secret",
                "com/appscharles/libs/databaser/programs/tester/dBMigrations");
        migrator.migrate();
        MigrationValidator validator = new MigrationValidator("tcp://localhost:"+12423+"/myDB", "root"  ,"secret",
                "com/appscharles/libs/databaser/programs/tester/dBMigrations");
        Assert.assertTrue(validator.isValid());
        runner.stop();
    }

    @Test
    public void shouldIsNotValidDatabase() throws IOException, DatabaserException, ProcesserException {
        new WinKillManager().killCommandLineContains("dBDir_shouldIsNotValidDatabase");
        File dBDir = this.temp.newFolder("dBDir_shouldIsNotValidDatabase");
        IServerRunner runner = new ServerRunner(12523, "myApp");
        runner.enableRunForce();
        runner.setServerDir(dBDir);
        runner.start();
        DatabaseCreator creator = new DatabaseCreator("tcp://localhost:"+12523+"/myDB", "root", "secret");
        creator.create();
        MigrationValidator validator = new MigrationValidator("tcp://localhost:"+12523+"/myDB", "root"  ,"secret",
                "com/appscharles/libs/databaser/programs/tester/dBMigrations");
        Assert.assertFalse("Not found migrations? Maybe run `generateMigration` gradle task.", validator.isValid());
        runner.stop();
    }
}