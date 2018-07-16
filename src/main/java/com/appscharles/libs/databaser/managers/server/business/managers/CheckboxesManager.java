package com.appscharles.libs.databaser.managers.server.business.managers;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.managers.server.ServerManagerController;
import com.appscharles.libs.reger.exceptions.RegerException;
import com.appscharles.libs.reger.validators.AutostartUserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Table databases manager.
 */
public class CheckboxesManager {

    private static final Logger logger = LogManager.getLogger(ServerManagerController.class);

    /**
     * The Server manager controller.
     */
    public ServerManagerController serverManagerController;

    /**
     * Instantiates a new Table databases manager.
     *
     * @param serverManagerController the server manager controller
     */
    public CheckboxesManager(ServerManagerController serverManagerController) {
        this.serverManagerController = serverManagerController;
    }

    /**
     * Initialize.
     */
    public void initialize() throws DatabaserException {
        try {
            this.serverManagerController.checkboxAutomationStartServerWithSystem.setSelected(AutostartUserValidator.exist(this.serverManagerController.serverManagerConfiguration.getAppName()+ "_h2database.jar"));
        } catch (RegerException e) {
            throw new DatabaserException();
        }

    }

}
