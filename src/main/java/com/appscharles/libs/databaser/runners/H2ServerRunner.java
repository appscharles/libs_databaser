package com.appscharles.libs.databaser.runners;

import com.appscharles.libs.databaser.configurators.H2ServerConfigurator;
import com.appscharles.libs.databaser.creators.H2ServerJarCreator;
import com.appscharles.libs.databaser.creators.IServerJarCreator;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.jarer.creators.AutostartCreator;
import com.appscharles.libs.jarer.exceptions.JarerException;
import com.appscharles.libs.processer.callers.CommanderCaller;
import com.appscharles.libs.processer.callers.CommanderResult;
import com.appscharles.libs.processer.callers.ICommanderCaller;
import com.appscharles.libs.processer.exceptions.ProcesserException;
import com.appscharles.libs.processer.managers.WinKillManager;

import java.io.File;
import java.io.IOException;

/**
 * The type H 2 server runner.
 */
public class H2ServerRunner extends AbstractServerRunner {

    /**
     * Instantiates a new H 2 server runner.
     *
     * @param port the port
     */
    public H2ServerRunner(Integer port) {
        super(port, H2ServerConfigurator.getH2ServerDir());
    }

    @Override
    public void run() throws DatabaserException {
        try {
            new WinKillManager().killCommandLineContains(this.serverDir.getAbsolutePath().replace("\\", "\\\\"));
            IServerJarCreator creator = new H2ServerJarCreator();
            creator.setServerDir(this.serverDir);
            creator.create();
            String command = "java -jar " + new File(this.serverDir, H2ServerConfigurator.getH2ServerFileName()).getAbsolutePath()
                    + " --port=" + this.port + " --serverDir=" + this.serverDir.getAbsolutePath();
            if (this.autostart){
                AutostartCreator.create(H2ServerConfigurator.getAutostartRegKey(),
                        new File(this.serverDir, H2ServerConfigurator.getAutostartBatFileName()),
                        command);
            }
            if (this.testMode) {
                command += " --testMode=true";
                ICommanderCaller commanderCaller = new CommanderCaller();
                CommanderResult result = commanderCaller.call(command);
                if (result.isError()) {
                    throw new DatabaserException("Error " + H2ServerConfigurator.getH2ServerFileName(), new DatabaserException(result.getOutput()));
                }
            } else {
                Runtime.getRuntime().exec(command);
            }
        } catch (ProcesserException | IOException | JarerException e) {
            throw new DatabaserException(e);
        }
    }
}
