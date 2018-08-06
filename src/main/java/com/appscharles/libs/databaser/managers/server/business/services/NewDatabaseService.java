package com.appscharles.libs.databaser.managers.server.business.services;

import com.appscharles.libs.databaser.creators.DatabaseCreator;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.managers.server.ServerManagerController;
import com.appscharles.libs.databaser.managers.server.stages.newDatabase.NewDatabase;
import com.appscharles.libs.databaser.managers.server.stages.newDatabase.business.models.NewDatabaseFields;
import com.appscharles.libs.databaser.validators.FileDatabaseExistValidator;
import com.appscharles.libs.dialoger.factories.AlertFactory;
import com.appscharles.libs.fxer.exceptions.FxerException;
import javafx.scene.control.Alert;

import java.io.File;

/**
 * The type New database service.
 */
public class NewDatabaseService {

    private ServerManagerController serverManagerController;

    /**
     * Instantiates a new New database service.
     *
     * @param serverManagerController the server manager controller
     */
    public NewDatabaseService(ServerManagerController serverManagerController) {
        this.serverManagerController = serverManagerController;
    }

    public void newDatabase() throws DatabaserException {
        try {
            NewDatabaseFields newDatabaseFields = NewDatabase.launch(this.serverManagerController.serverManagerConfiguration);
            if (newDatabaseFields != null){
                if (FileDatabaseExistValidator.exist(newDatabaseFields.getdBName(), this.serverManagerController.serverManagerConfiguration.getServerDir())){
                    Alert alert = AlertFactory.create(Alert.AlertType.WARNING, this.serverManagerController.resourceBundle.getString("view.dialog.warning.title"), this.serverManagerController.resourceBundle.getString("view.dialog.warning.name_database_already_exist")).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build();
                    alert.showAndWait();
                    return;
                }
                String databaseURL = "file:"+this.serverManagerController.serverManagerConfiguration.getServerDir().getAbsolutePath() + File.separator + newDatabaseFields.getdBName();
                new DatabaseCreator(databaseURL, newDatabaseFields.getUser(), newDatabaseFields.getPassword()).create();
                this.serverManagerController.tableDatabasesManager.refresh();
            }
        } catch (FxerException e) {
            throw new DatabaserException(e);
        }
    }
}
