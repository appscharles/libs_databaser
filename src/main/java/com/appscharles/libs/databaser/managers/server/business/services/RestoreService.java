package com.appscharles.libs.databaser.managers.server.business.services;

import com.appscharles.libs.databaser.managers.server.ServerManagerController;
import com.appscharles.libs.databaser.managers.server.business.models.AvailableDatabaseItem;
import com.appscharles.libs.dialoger.factories.AlertFactory;
import com.appscharles.libs.dialoger.factories.ExceptionDialogFactory;
import com.appscharles.libs.dialoger.factories.FileChooserFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.message.DbException;
import org.h2.tools.Restore;

import java.io.File;

/**
 * The type Archiving service.
 */
public class RestoreService {

    private static final Logger logger = LogManager.getLogger(RestoreService.class);

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
    public RestoreService(ServerManagerController serverManagerController, ActionEvent menuButtonOnActionEvent, AvailableDatabaseItem availableDatabaseItem) {
        this.serverManagerController = serverManagerController;
        this.menuButtonOnActionEvent = menuButtonOnActionEvent;
        this.availableDatabaseItem = availableDatabaseItem;
    }

    /**
     * Archiving.
     */
    public void restore() {
        try {
            FileChooser chooser = FileChooserFactory.create(this.serverManagerController.resourceBundle.getString("view.backup_service.file_chooser.save"))
                    .addExtensionFilter(this.serverManagerController.resourceBundle.getString("view.backup_service.file_chooser.extension_filter.file") + " ZIP (*.zip)", "*.zip")
                    .build();
            File fileZip = chooser.showOpenDialog(this.serverManagerController.getFXStage());
            if (fileZip != null){
                Restore.execute(fileZip.getAbsolutePath(), this.availableDatabaseItem.getDatabaseFile().getParentFile().getAbsolutePath(), this.availableDatabaseItem.getDatabaseName());
                AlertFactory.create(Alert.AlertType.INFORMATION, this.serverManagerController.resourceBundle.getString("view.dialog.information.title"), this.serverManagerController.resourceBundle
                        .getString("view.dialog.information.restore_completed_successfully")).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build().showAndWait();
            }
        } catch (DbException e) {
            logger.error(e);
            ExceptionDialogFactory.create(this.serverManagerController.resourceBundle.getString("view.dialog.exception.title"), this.serverManagerController.resourceBundle.getString("view.dialog.exception.content_text.can_not_restore_database"), e).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build().showAndWait();
        }
    }
}
