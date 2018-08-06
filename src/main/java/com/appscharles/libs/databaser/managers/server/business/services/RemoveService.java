package com.appscharles.libs.databaser.managers.server.business.services;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.managers.server.ServerManagerController;
import com.appscharles.libs.databaser.managers.server.business.models.AvailableDatabaseItem;
import com.appscharles.libs.dialoger.factories.AlertConfirmationFactory;
import com.appscharles.libs.dialoger.factories.AlertFactory;
import com.appscharles.libs.dialoger.factories.ExceptionDialogFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.tools.DeleteDbFiles;

import java.io.File;
import java.util.Optional;

/**
 * The type Archiving service.
 */
public class RemoveService {

    private static final Logger logger = LogManager.getLogger(RemoveService.class);

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
    public RemoveService(ServerManagerController serverManagerController, ActionEvent menuButtonOnActionEvent, AvailableDatabaseItem availableDatabaseItem) {
        this.serverManagerController = serverManagerController;
        this.menuButtonOnActionEvent = menuButtonOnActionEvent;
        this.availableDatabaseItem = availableDatabaseItem;
    }

    /**
     * Archiving.
     */
    public void remove() {
        try {
            AlertConfirmationFactory factory = AlertConfirmationFactory.create(Alert.AlertType.CONFIRMATION, this.serverManagerController.resourceBundle.getString("view.dialog.confirmation.title"), this.serverManagerController.resourceBundle
                    .getString("view.dialog.confirmation.are_you_sure_remove_database")).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png");
         Optional<ButtonType> result = factory.build().showAndWait();
         if (result.get() == factory.getButtonTypeYes()){
             DeleteDbFiles.execute(this.availableDatabaseItem.getDatabaseFile().getParentFile().getAbsolutePath(), this.availableDatabaseItem.getDatabaseName(), true);
             for(File file : this.availableDatabaseItem.getDatabaseFile().getParentFile().listFiles()) {
                 if(file.getName().startsWith(this.availableDatabaseItem.getDatabaseName() + ".")){
                     if (file.exists() == false){
                         throw new DatabaserException(this.serverManagerController.resourceBundle.getString("exception.database_can_not_removed"));
                     }
                 }
             }
             AlertFactory.create(Alert.AlertType.INFORMATION, this.serverManagerController.resourceBundle.getString("view.dialog.information.title"), this.serverManagerController.resourceBundle
                     .getString("view.dialog.information.database_successfully_removed")).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build().showAndWait();
             this.serverManagerController.tableDatabasesManager.refresh();
         }
        } catch (DatabaserException e) {
            logger.error(e, e);
            ExceptionDialogFactory.create(this.serverManagerController.resourceBundle.getString("view.dialog.exception.title"), e.getMessage(), e).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build().showAndWait();
        }
    }
}
