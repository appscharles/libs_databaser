package com.appscharles.libs.databaser.servers;

import com.appscharles.libs.databaser.factories.IAllowRemoteDisableable;
import com.appscharles.libs.databaser.factories.IServerFactory;
import org.h2.tools.Server;

import java.io.File;

/**
 * The type Abstract h 2 server.
 */
public abstract class AbstractH2Server implements IServer, IAllowRemoteDisableable {

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
     * Instantiates a new Abstract h 2 server.
     *
     * @param port   the port
     * @param dBName the d b name
     * @param dBDir  the d b dir
     */
    public AbstractH2Server(Integer port, File dBDir) {
        this.port = port;
        this.dBDir = dBDir;
    }

    /**
     * Disable allow remote h 2 server.
     *
     * @return the h 2 server
     */
    public AbstractH2Server disableAllowRemote(){
        this.serverFactory.disableAllowRemote();
        return this;
    }
}
