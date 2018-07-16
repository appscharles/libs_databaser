package com.appscharles.libs.databaser.managers.server.business.services;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.managers.server.ServerManagerController;

/**
 * The type Start server service.
 */
public class StartServerService {

    private ServerManagerController serverManagerController;

    /**
     * Instantiates a new Start server service.
     *
     * @param serverManagerController the server manager controller
     */
    public StartServerService(ServerManagerController serverManagerController) {
        this.serverManagerController = serverManagerController;
    }

    public void startServer() throws DatabaserException {
        this.serverManagerController.serverRunnerManager.serverRunner.start();
        this.serverManagerController.buttonStartServer.setDisable(true);
        this.serverManagerController.checkboxAutomationStartServerWithSystem.setDisable(false);
    }
}
