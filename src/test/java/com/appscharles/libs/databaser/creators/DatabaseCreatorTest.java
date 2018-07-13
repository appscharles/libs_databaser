package com.appscharles.libs.databaser.creators;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.runners.IServerRunner;
import com.appscharles.libs.databaser.runners.ServerRunner;
import com.appscharles.libs.processer.exceptions.ProcesserException;
import com.appscharles.libs.processer.managers.WinKillManager;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 07.07.2018
 * Time: 17:14
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class DatabaseCreatorTest extends TestCase {

    @Test
    public void shouldTestCreateDatabaseIfNotExist() throws IOException, DatabaserException, SQLException, ProcesserException {
        new WinKillManager().killCommandLineContains("dbdbdb");
        File dbDir = this.temp.newFolder("dbdbdb dir");
        Integer port = 3425;
        IServerRunner runner = new ServerRunner(3425);
        runner.enableRunForce();
        runner.setServerDir(dbDir);
        runner.start();
        IDatabaseCreator creator = new H2DatabaseCreator("tcp://localhost:"+port+"/myDB", "root", "secret");
        creator.create();
        Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:"+port+"/myDB", "root", "secret");
        connection.close();
        runner.stop();
    }

    @Test(expected = DatabaserException.class)
    public void shouldThrowException() throws IOException, DatabaserException, SQLException {
        File dbDir = this.temp.newFolder("db Dir");
        IServerRunner runner = new ServerRunner(3456);
        runner.enableRunForce();
        runner.setServerDir(dbDir);
        runner.start();
        IDatabaseCreator creator = new H2DatabaseCreator("tcp://localhost:3456/myDB", "root", "secret");
        creator.create();
        creator.create();
        runner.stop();
    }
}