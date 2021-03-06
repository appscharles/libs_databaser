package com.appscharles.libs.databaser.runners;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.validators.ServerRunningValidator;
import com.appscharles.libs.jarer.exceptions.JarerException;
import com.appscharles.libs.processer.exceptions.ProcesserException;
import com.appscharles.libs.processer.managers.WinKillManager;
import com.appscharles.libs.reger.exceptions.RegerException;
import com.appscharles.libs.reger.removers.AutostartUserRemover;
import com.appscharles.libs.reger.validators.AutostartUserValidator;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 13.07.2018
 * Time: 12:29
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class ServerRunnerTest extends TestCase {

    @Test
    public void shouldRunServer() throws DatabaserException, IOException, ProcesserException, JarerException, RegerException {
        new WinKillManager().killCommandLineContains("serverDirTest");
        File serverDirTest = this.temp.newFolder("serverDirTest");
        Integer port = 2422;
        IServerRunner runner = new ServerRunner(port, "myApp");
        runner.enableRunForce();
        runner.setServerDir(serverDirTest);
        runner.enableAutostart();
        runner.start();
        Assert.assertTrue(ServerRunningValidator.isRunning("tcp://localhost:"+ port));
        runner.stop();
        Assert.assertTrue(AutostartUserValidator.exist("myApp_h2database.jar"));
        AutostartUserRemover.remove("myApp_h2database.jar");
    }
}