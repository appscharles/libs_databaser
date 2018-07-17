package com.appscharles.libs.databaser.managers.server.business.services;

import com.appscharles.libs.databaser.managers.server.ServerManagerController;
import com.appscharles.libs.databaser.managers.server.business.models.AvailableDatabaseItem;
import javafx.event.ActionEvent;

/**
 * The type Archiving service.
 */
public class ChangePasswordService {

    private ServerManagerController serverManagerController;
    private final ActionEvent menuButtonOnActionEvent;
    private final AvailableDatabaseItem availableDatabaseItem;

    /**
     * Instantiates a new Archiving service.
     *
     * @param serverManagerController the server manager controller
     * @param menuButtonOnActionEvent the menu button on action event
     * @param availableDatabaseItem   the available database item
     */
    public ChangePasswordService(ServerManagerController serverManagerController, ActionEvent menuButtonOnActionEvent, AvailableDatabaseItem availableDatabaseItem) {
        this.serverManagerController = serverManagerController;
        this.menuButtonOnActionEvent = menuButtonOnActionEvent;
        this.availableDatabaseItem = availableDatabaseItem;
    }

    /**
     * Archiving.
     */
    public void changePassword() {

    }
}
