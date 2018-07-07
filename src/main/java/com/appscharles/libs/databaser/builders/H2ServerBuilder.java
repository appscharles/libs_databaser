package com.appscharles.libs.databaser.builders;

import com.appscharles.libs.databaser.servers.H2Server;
import com.appscharles.libs.databaser.servers.IServer;

import java.io.File;

/**
 * The type H 2 server builder.
 */
public class H2ServerBuilder {

    private Integer port;

    private File dBDir;

    private H2ServerBuilder(){

    }

    /**
     * Create h 2 server builder.
     *
     * @param port   the port
     * @param dBName the d b name
     * @param dBDir  the d b dir
     * @return the h 2 server builder
     */
    public static H2ServerBuilder create(Integer port,File dBDir){
        H2ServerBuilder instance = new H2ServerBuilder();
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
