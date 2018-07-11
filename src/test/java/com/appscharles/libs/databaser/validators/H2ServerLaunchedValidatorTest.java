package com.appscharles.libs.databaser.validators;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.builders.ServerH2Builder;
import com.appscharles.libs.databaser.configurators.H2ServerConfigurator;
import com.appscharles.libs.databaser.creators.H2ServerJarCreator;
import com.appscharles.libs.databaser.creators.IServerJarCreator;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.runners.H2ServerRunner;
import com.appscharles.libs.databaser.runners.IServerRunner;
import com.appscharles.libs.databaser.servers.IServer;
import com.appscharles.libs.processer.exceptions.ProcesserException;
import com.appscharles.libs.processer.managers.WinKillManager;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 11.07.2018
 * Time: 08:27
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class H2ServerLaunchedValidatorTest extends TestCase {

    @Test
    public void shouldValidateH2ServerLaunched() throws IOException, DatabaserException, InterruptedException {
        Integer port = 3421;
        Assert.assertFalse(H2ServerLaunchedValidator.isLaunched("tcp://localhost:" + port));
        File serverDir = this.temp.newFolder("serverDir");
        IServer server = ServerH2Builder.create(port,  serverDir).build();
        server.start();
        Assert.assertTrue(H2ServerLaunchedValidator.isLaunched("tcp://localhost:" + port));
    }

    @Test
    public void shouldValidateH2ServerLaunchedWithJar() throws IOException, DatabaserException, InterruptedException, ProcesserException {
        File serverDir = this.temp.newFolder("serverDir");
        Integer port = 3422;
        IServerRunner runner = new H2ServerRunner(port);
        runner.setServerDir(serverDir);
        runner.run();
        Thread.sleep(2000);
        Assert.assertTrue(H2ServerLaunchedValidator.isLaunched("tcp://localhost:" + port));
        new WinKillManager().killCommandLineContains(serverDir.getAbsolutePath().replace("\\", "\\\\"));
    }

    @Test
    public void shouldValidateH2ServerLaunchedWithJar2() throws IOException, DatabaserException, InterruptedException {
        File serverDir = this.temp.newFolder("serverDir");
        Integer port = 3420;
        IServerJarCreator creator = new H2ServerJarCreator();
        creator.setServerDir(serverDir);
        creator.create();
        String command = "java -jar " + new File(serverDir, H2ServerConfigurator.getH2ServerFileName()).getAbsolutePath()
                + " --port=" + port + " --testMode=true";
        Runtime.getRuntime().exec(command);
        Thread.sleep(3000);
        Assert.assertTrue(H2ServerLaunchedValidator.isLaunched("tcp://localhost:" + port));
    }


}