package com.appscharles.libs.databaser.servers;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.factories.ServerFactory;
import org.h2.tools.Server;

import java.io.File;
import java.sql.SQLException;

/**
 * The type H 2 server.
 */
public class ServerH2 extends AbstractServerH2 {

    /**
     * Instantiates a new H 2 server.
     *
     * @param port  the port
     * @param dBDir the d b dir
     */
    public ServerH2(Integer port, File dBDir) {
        super(port, dBDir);
        this.serverFactory = ServerFactory.create(this.port,this.dBDir);
    }

    @Override
    public void start() throws DatabaserException {
        try {
            Server server = this.serverFactory.build();
            server.start();
        } catch (SQLException e) {
            throw new DatabaserException(e);
        }
    }

    @Override
    public void stop() {
        if (this.server != null){
            this.server.stop();
        }
    }
}
