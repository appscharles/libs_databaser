package com.appscharles.libs.databaser.managers.server.business.services;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.managers.server.ServerManagerController;

/**
 * The type Stop server service.
 */
public class StopServerService {

    private ServerManagerController serverManagerController;

    /**
     * Instantiates a new Stop server service.
     *
     * @param serverManagerController the server manager controller
     */
    public StopServerService(ServerManagerController serverManagerController) {
        this.serverManagerController = serverManagerController;
    }

    public void stopServer() throws DatabaserException {
        this.serverManagerController.serverRunnerManager.serverRunner.stop();
        this.serverManagerController.buttonStartServer.setDisable(false);
    }
}
