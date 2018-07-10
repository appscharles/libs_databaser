package com.appscharles.libs.databaser;

import com.appscharles.libs.databaser.builders.ServerH2Builder;
import com.appscharles.libs.databaser.creators.DatabaseCreator;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.servers.IServer;
import com.appscharles.libs.logger.configurators.Log4j2Console;
import com.appscharles.libs.logger.configurators.Log4jConsole;
import org.apache.logging.log4j.Level;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 07.07.2018
 * Time: 17:15
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class TestCase {

    @Before
    public void before(){
        new Log4j2Console(Level.DEBUG).config();
        new Log4jConsole(org.apache.log4j.Level.INFO).config();
    }

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    public IServer createDefaultEbean(Integer port, File dBDir) throws IOException, DatabaserException {
        IServer server = ServerH2Builder.create(port,dBDir).build();
        server.start();
        DatabaseCreator creator = new DatabaseCreator("tcp://localhost:"+port+"/myDB", "root", "secret");
        creator.create();
        return server;
    }
}
