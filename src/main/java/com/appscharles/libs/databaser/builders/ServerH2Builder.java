package com.appscharles.libs.databaser.builders;

import com.appscharles.libs.databaser.servers.ServerH2;
import com.appscharles.libs.databaser.servers.IServer;

import java.io.File;

/**
 * The type H 2 server builder.
 */
public class ServerH2Builder {

    private Integer port;

    private File dBDir;

    private ServerH2Builder(){

    }

    /**
     * Create h 2 server builder.
     *
     * @param port   the port
     * @param dBName the d b name
     * @param dBDir  the d b dir
     * @return the h 2 server builder
     */
    public static ServerH2Builder create(Integer port, File dBDir){
        ServerH2Builder instance = new ServerH2Builder();
        instance.port = port;
        instance.dBDir = dBDir;
        return instance;
    }

    /**
     * Build server.
     *
     * @return the server
     */
    public IServer build(){
        return new ServerH2(this.port, this.dBDir);
    }
}
