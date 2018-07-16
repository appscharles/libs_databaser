package com.appscharles.libs.databaser.managers.server.business.managers;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.managers.server.ServerManagerController;
import com.appscharles.libs.databaser.validators.ServerRunningValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type New database service.
 */
public class DisableControllersManager {

    private static final Logger logger = LogManager.getLogger(ServerManagerController.class);

    public ServerManagerController serverManagerController;

    /**
     * Instantiates a new New database service.
     *
     * @param serverManagerController the server manager controller
     */
    public DisableControllersManager(ServerManagerController serverManagerController) {
        this.serverManagerController = serverManagerController;
    }

    public void initialize() throws DatabaserException {
        this.serverManagerController.buttonStopServer.disableProperty()
                .bind(this.serverManagerController.buttonStartServer.disableProperty().not());
        this.serverManagerController.buttonNewDatabase.disableProperty()
                .bind(this.serverManagerController.buttonStartServer.disableProperty());
        if (ServerRunningValidator.isRunning("tcp://localhost:" + this.serverManagerController.serverManagerConfiguration.getServerPort(), this.serverManagerController.serverManagerConfiguration.getServerDir())){
            this.serverManagerController.buttonStartServer.setDisable(true);
        }
    }
}
