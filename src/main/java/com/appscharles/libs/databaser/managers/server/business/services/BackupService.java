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
import org.h2.tools.Backup;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Archiving service.
 */
public class BackupService {

    private static final Logger logger = LogManager.getLogger(BackupService.class);

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
    public BackupService(ServerManagerController serverManagerController, ActionEvent menuButtonOnActionEvent, AvailableDatabaseItem availableDatabaseItem) {
        this.serverManagerController = serverManagerController;
        this.menuButtonOnActionEvent = menuButtonOnActionEvent;
        this.availableDatabaseItem = availableDatabaseItem;
    }

    /**
     * Archiving.
     */
    public void backup() {
        try {
            FileChooser chooser = FileChooserFactory.create(this.serverManagerController.resourceBundle.getString("view.backup_service.file_chooser.save"))
                    .addExtensionFilter(this.serverManagerController.resourceBundle.getString("view.backup_service.file_chooser.extension_filter.file") + " ZIP (*.zip)", "*.zip")
                    .build();
           chooser.setInitialFileName(this.serverManagerController.resourceBundle.getString("view.backup_service.file_chooser.initial_file_name") + "_" +  this.serverManagerController.serverManagerConfiguration.getAppName() + "_" +this.availableDatabaseItem.getDatabaseName() + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
            File fileZip = chooser.showSaveDialog(this.serverManagerController.getFXStage());
            if (fileZip != null){
                Backup.execute(fileZip.getAbsolutePath(), this.availableDatabaseItem.getDatabaseFile().getParentFile().getAbsolutePath(), this.availableDatabaseItem.getDatabaseName(), true);
                AlertFactory.create(Alert.AlertType.INFORMATION, this.serverManagerController.resourceBundle.getString("view.dialog.information.title"), this.serverManagerController.resourceBundle
                        .getString("view.dialog.information.backup_completed_successfully")).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build().showAndWait();
            }
        } catch (SQLException e) {
            logger.error(e, e);
            ExceptionDialogFactory.create(this.serverManagerController.resourceBundle.getString("view.dialog.exception.title"), e.getMessage(), e).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build().showAndWait();
        }
    }
}
