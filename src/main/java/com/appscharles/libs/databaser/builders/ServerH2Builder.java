package com.appscharles.libs.databaser.builders;

import com.appscharles.libs.databaser.servers.H2Server;
import com.appscharles.libs.databaser.servers.IServer;

import java.io.File;

/**
 * The type Server h 2 builder.
 */
public class ServerH2Builder {

    private Integer port;

    private File dBDir;

    private ServerH2Builder(){

    }

    /**
     * Create server h 2 builder.
     *
     * @param port  the port
     * @param dBDir the d b dir
     * @return the server h 2 builder
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
        return new H2Server(this.port, this.dBDir);
    }
}
