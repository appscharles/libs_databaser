package com.appscharles.libs.databaser.managers.server.business.services;

import com.appscharles.libs.databaser.managers.server.ServerManagerController;
import com.appscharles.libs.reger.creators.AutostartCreator;
import com.appscharles.libs.reger.exceptions.RegerException;
import com.appscharles.libs.reger.removers.AutostartUserRemover;

import java.io.File;

/**
 * The type Toggle automation start.
 */
public class ToggleAutomationStart {

    private ServerManagerController serverManagerController;

    /**
     * Instantiates a new Toggle automation start.
     *
     * @param serverManagerController the server manager controller
     */
    public ToggleAutomationStart(ServerManagerController serverManagerController) {
        this.serverManagerController = serverManagerController;
    }

    public void toggleAutomationStart() throws RegerException {
        if (this.serverManagerController.checkboxAutomationStartServerWithSystem.isSelected()){
            AutostartCreator.create(this.serverManagerController.serverManagerConfiguration.getAppName()+ "_h2database.jar", new File(this.serverManagerController.serverManagerConfiguration.getServerDir(), "h2database.bat"), this.serverManagerController.serverRunnerManager.serverRunner.getCommand());
        } else {
            AutostartUserRemover.remove(this.serverManagerController.serverManagerConfiguration.getAppName() + "_h2database.jar");
        }
    }
}
