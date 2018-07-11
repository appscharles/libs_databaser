package com.appscharles.libs.databaser.runners;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.configurators.H2ServerConfigurator;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.jarer.exceptions.JarerException;
import com.appscharles.libs.jarer.removers.AutostartUserRemover;
import com.appscharles.libs.processer.exceptions.ProcesserException;
import com.appscharles.libs.processer.managers.WinKillManager;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 11.07.2018
 * Time: 07:44
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class H2ServerRunnerTest extends TestCase {

    @Test
    public void shouldRunJarH2Server() throws IOException, DatabaserException {
        File serverDir = this.temp.newFolder("serverDir");
        Integer port = 3421;
        IServerRunner runner = new H2ServerRunner(port);
        runner.setServerDir(serverDir);
        runner.enableTestMode();
        runner.run();
    }

    @Test(expected = DatabaserException.class)
    public void shouldThrowException() throws IOException, DatabaserException, InterruptedException, ProcesserException {
        File serverDir = this.temp.newFolder("serverDir22");
        Integer port = 3429;
        IServerRunner runner = new H2ServerRunner(port);
        runner.setServerDir(serverDir);
        runner.run();
        Thread.sleep(2000);
        IServerRunner runner2 = new H2ServerRunner(port);
        runner2.setServerDir(serverDir);
        runner2.enableTestMode();
        runner2.run();
        new WinKillManager().killCommandLineContains(serverDir.getAbsolutePath().replace("\\", "\\\\"));
    }

    @Test
    public void shouldRunJarH2ServerAndAddRegistryKey() throws IOException, DatabaserException, JarerException {
        File serverDir = this.temp.newFolder("serverDir");
        Integer port = 3421;
        IServerRunner runner = new H2ServerRunner(port);
        runner.setServerDir(serverDir);
        runner.enableTestMode();
        runner.enableAutostart();
        runner.run();
        AutostartUserRemover.remove(H2ServerConfigurator.getAutostartRegKey());
    }
}