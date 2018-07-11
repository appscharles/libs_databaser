package com.appscharles.libs.databaser.factories;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import org.h2.tools.Server;

import java.io.File;
import java.sql.SQLException;

/**
 * The type Server factory.
 */
public class ServerFactory extends AbstractServerFactory {

    /**
     * Create server factory.
     *
     * @param port  the port
     * @param dBDir the d b dir
     * @return the server factory
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
