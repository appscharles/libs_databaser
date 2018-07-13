package com.appscharles.libs.databaser.runners;

import com.appscharles.libs.databaser.configurators.H2JarArgumentsConfigurator;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.extractors.H2JarExtractor;
import com.appscharles.libs.databaser.validators.PortUsingValidator;
import com.appscharles.libs.databaser.validators.ServerRunningValidator;
import com.appscharles.libs.databaser.waiters.ServerRunningWaiter;
import com.appscharles.libs.processer.exceptions.ProcesserException;
import com.appscharles.libs.processer.managers.WinKillManager;
import com.appscharles.libs.reger.creators.AutostartCreator;
import com.appscharles.libs.reger.exceptions.RegerException;

import java.io.File;
import java.io.IOException;

/**
 * The type Server runner.
 */
public class ServerRunner extends AbstractServerRunner {

    /**
     * Instantiates a new Server runner.
     *
     * @param port the port
     */
    public ServerRunner(Integer port) {
        super(port, new File(System.getenv("ProgramFiles(X86)"), "Databaser"));
    }

    @Override
    public void start() throws DatabaserException {
      try {
          if (this.runForce){
              new WinKillManager().killCommandLineContains(this.serverDir.getAbsolutePath().replace("\\", "\\\\"));
          }
          if (ServerRunningValidator.isRunning("tcp://localhost:" + this.tcpPort)) {
              throw new DatabaserException("H2 Server is not running, because in port " + this.tcpPort + " is running server [0005-001]");
          } else if (PortUsingValidator.isUsing("localhost", this.tcpPort)) {
              throw new DatabaserException("Port " + this.tcpPort + " is using [0006-001]");
          }
          File h2JarFile = new File(this.serverDir, "h2database.jar");
          new H2JarExtractor().toFile("/com/appscharles/libs/databaser/jars/h2database.jar", h2JarFile);
          String command = "java -jar \"" + h2JarFile.getAbsolutePath() +"\"" + H2JarArgumentsConfigurator.getCommandArguments(this.tcpPort, this.webPort,this.serverDir);

          if (this.autostart) {
                AutostartCreator.create("h2database.jar", new File(this.serverDir, "h2database.bat"), command);
            }
           Runtime.getRuntime().exec(command);
            try {
                ServerRunningWaiter.waitForRunInLocalhost(this.tcpPort, this.serverRunningTimeout);
            } catch (DatabaserException e1) {
                throw new DatabaserException("H2 server not launched with command "+command+" [0008-001]", e1);
            }
        } catch (RegerException | IOException | ProcesserException e) {
            throw new DatabaserException(e);
        }
    }

    @Override
    public void stop() throws DatabaserException {
        try {
            new WinKillManager().killCommandLineContains(this.serverDir.getAbsolutePath().replace("\\", "\\\\"));
        } catch (ProcesserException e) {
        throw new DatabaserException(e);
        }
    }
}
