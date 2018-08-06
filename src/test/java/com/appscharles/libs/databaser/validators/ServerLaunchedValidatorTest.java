package com.appscharles.libs.databaser.validators;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.runners.IServerRunner;
import com.appscharles.libs.databaser.runners.ServerRunner;
import com.appscharles.libs.processer.exceptions.ProcesserException;
import com.appscharles.libs.processer.managers.WinKillManager;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * The type H 2 server launched validator test.
 */
public class ServerLaunchedValidatorTest extends TestCase {

    /**
     * Should validate h 2 server launched.
     *
     * @throws IOException          the io exception
     * @throws DatabaserException   the databaser exception
     * @throws InterruptedException the interrupted exception
     * @throws ProcesserException   the processer exception
     */
    @Test
    public void shouldValidateH2ServerLaunched() throws IOException, DatabaserException, InterruptedException, ProcesserException {
        Integer port = 3421;
        Assert.assertFalse(ServerRunningValidator.isRunning("tcp://localhost:" + port));
        File serverDir = this.temp.newFolder("serverDir");
        new WinKillManager().killCommandLineContains(serverDir.getAbsolutePath().replace("\\", "\\\\"));
        IServerRunner runner = new ServerRunner(3421, "myApp");
        runner.enableRunForce();
        runner.setServerDir(serverDir);
        runner.start();
        Assert.assertTrue(ServerRunningValidator.isRunning("tcp://localhost:" + port));
    runner.stop();
    }

    /**
     * Should validate h 2 server launched with jar.
     *
     * @throws IOException          the io exception
     * @throws DatabaserException   the databaser exception
     * @throws InterruptedException the interrupted exception
     * @throws ProcesserException   the processer exception
     */
    @Test
    public void shouldValidateH2ServerLaunchedWithJar() throws IOException, DatabaserException, InterruptedException, ProcesserException {
        File serverDir = this.temp.newFolder("serverDir");
        Integer port = 3422;
        IServerRunner runner = new ServerRunner(port, "myApp");
        runner.enableRunForce();
        runner.setServerDir(serverDir);
        runner.start();
        Thread.sleep(2000);
        Assert.assertTrue(ServerRunningValidator.isRunning("tcp://localhost:" + port));
        runner.stop();
    }

}