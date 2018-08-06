package com.appscharles.libs.databaser.managers.server.business.services;

import com.appscharles.libs.databaser.managers.server.ServerManagerController;
import com.appscharles.libs.databaser.managers.server.business.models.AvailableDatabaseItem;
import com.appscharles.libs.databaser.managers.server.stages.changeUser.ChangeUser;
import com.appscharles.libs.dialoger.factories.ExceptionDialogFactory;
import com.appscharles.libs.fxer.exceptions.FxerException;
import javafx.event.ActionEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Archiving service.
 */
public class ChangeUserService {

    private static final Logger logger = LogManager.getLogger(ChangeUserService.class);

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
    public ChangeUserService(ServerManagerController serverManagerController, ActionEvent menuButtonOnActionEvent, AvailableDatabaseItem availableDatabaseItem) {
        this.serverManagerController = serverManagerController;
        this.menuButtonOnActionEvent = menuButtonOnActionEvent;
        this.availableDatabaseItem = availableDatabaseItem;
    }

    /**
     * Archiving.
     */
    public void changeUser() {
        try {
            ChangeUser.launch(this.availableDatabaseItem);
        } catch (FxerException e) {
            logger.error(e, e);
            ExceptionDialogFactory.create(this.serverManagerController.resourceBundle.getString("view.dialog.exception.title"), e.getMessage(), e).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build().showAndWait();
        }

    }
}
