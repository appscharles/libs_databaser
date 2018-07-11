package com.appscharles.libs.databaser.servers;

import com.appscharles.libs.databaser.factories.IAllowRemoteDisableable;
import com.appscharles.libs.databaser.factories.IServerFactory;
import org.h2.tools.Server;

import java.io.File;

/**
 * The type H 2 abstract server.
 */
public abstract class H2AbstractServer implements IServer, IAllowRemoteDisableable {

    /**
     * The Server factory.
     */
    protected IServerFactory serverFactory;

    /**
     * The Server.
     */
    protected Server server;

    /**
     * The Port.
     */
    protected Integer port;

    /**
     * The D b dir.
     */
    protected File dBDir;

    /**
     * Instantiates a new H 2 abstract server.
     *
     * @param port  the port
     * @param dBDir the d b dir
     */
    public H2AbstractServer(Integer port, File dBDir) {
        this.port = port;
        this.dBDir = dBDir;
    }

    public H2AbstractServer disableAllowRemote(){
        this.serverFactory.disableAllowRemote();
        return this;
    }
}
