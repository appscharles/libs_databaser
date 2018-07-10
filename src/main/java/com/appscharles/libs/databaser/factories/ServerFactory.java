package com.appscharles.libs.databaser.factories;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import org.h2.tools.Server;

import java.io.File;
import java.sql.SQLException;

/**
 * The type H 2 server factory.
 */
public class ServerFactory extends AbstractServerFactory {

    /**
     * Create h 2 server factory.
     *
     * @param port   the port
     * @param dBName the d b name
     * @param dBDir  the d b dir
     * @return the h 2 server factory
     */
    public static ServerFactory create(Integer port, File dBDir) {
        ServerFactory instance = new ServerFactory();
        instance.port = port;
        instance.dBDir = dBDir;
        return instance;
    }

    public Server build() throws DatabaserException {
        try {
            if (this.allowRemote){
                this.addArgument("-tcpAllowOthers");
            }
            this.addArgument("-tcpPort");
            this.addArgument(String.valueOf(this.port));
            this.addArgument("-baseDir");
            this.addArgument(this.dBDir.getAbsolutePath() + File.separator);
            return Server.createTcpServer(getPrepareArguments());
        } catch (SQLException e) {
            throw new DatabaserException(e);
        }
    }
}
