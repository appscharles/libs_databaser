package com.appscharles.libs.databaser.managers.server;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.managers.server.business.configurations.ServerManagerConfiguration;
import com.appscharles.libs.fxer.exceptions.FxerException;
import com.appscharles.libs.logger.configurators.Log4j2Console;
import com.appscharles.libs.processer.exceptions.ProcesserException;
import com.appscharles.libs.processer.managers.WinKillManager;
import org.apache.logging.log4j.Level;
import org.junit.Test;

import java.io.File;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 16.07.2018
 * Time: 11:12
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class ServerManagerTest extends TestCase {

    @Test
    public void shouldRunWindow() throws FxerException, ProcesserException {
        new Log4j2Console(Level.TRACE).config();
        File serverDir = new File("E:\\dbs");
        new WinKillManager().killCommandLineContains("serverDir_shouldRunWindow");
        Integer port = 1452;
        ServerManagerConfiguration config = new ServerManagerConfiguration(port, "myApp", serverDir);
        config.setTest(true);
        ServerManager.launch(config);
    }
}