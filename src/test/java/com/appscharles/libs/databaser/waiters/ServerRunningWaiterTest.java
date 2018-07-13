package com.appscharles.libs.databaser.waiters;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.runners.IServerRunner;
import com.appscharles.libs.databaser.runners.ServerRunner;
import com.appscharles.libs.processer.exceptions.ProcesserException;
import com.appscharles.libs.processer.managers.WinKillManager;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 13.07.2018
 * Time: 12:18
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class ServerRunningWaiterTest extends TestCase {

    @Test(expected = DatabaserException.class)
    public void shouldException() throws DatabaserException {
        ServerRunningWaiter.waitForRunInLocalhost(2342, 1000L);
    }

    @Test
    public void shouldWaitForRunningServer() throws DatabaserException, ProcesserException, IOException {
        new WinKillManager().killCommandLineContains("serverDirTest");
        File serverDir = this.temp.newFolder("serverDirTest");
        IServerRunner runner = new ServerRunner(2342);
        runner.enableRunForce();
        runner.setServerDir(serverDir);
        runner.start();
        ServerRunningWaiter.waitForRunInLocalhost(2342, 3000L);
        runner.stop();
    }

}