package com.appscharles.libs.databaser.managers.server.business.services;

import com.appscharles.libs.databaser.creators.H2FileDatabaseCreator;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.managers.server.ServerManagerController;
import com.appscharles.libs.databaser.managers.server.stages.newDatabase.NewDatabase;
import com.appscharles.libs.databaser.managers.server.stages.newDatabase.business.models.NewDatabaseFields;
import com.appscharles.libs.databaser.validators.H2FileDatabaseExistValidator;
import com.appscharles.libs.dialoger.factories.AlertFactory;
import com.appscharles.libs.fxer.exceptions.FxerException;
import javafx.scene.control.Alert;

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
            NewDatabaseFields newDatabaseFields = NewDatabase.launch();
            if (newDatabaseFields != null){
                if (H2FileDatabaseExistValidator.exist(newDatabaseFields.getdBName(), this.serverManagerController.serverManagerConfiguration.getServerDir())){
                    Alert alert = AlertFactory.create(Alert.AlertType.WARNING, this.serverManagerController.resourceBundle.getString("view.dialog.warning.title"), this.serverManagerController.resourceBundle.getString("view.dialog.warning.name_database_already_exist")).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build();
                    alert.showAndWait();
                    return;
                }
                new H2FileDatabaseCreator(newDatabaseFields.getdBName(), newDatabaseFields.getUser(), newDatabaseFields.getPassword(), this.serverManagerController.serverManagerConfiguration.getServerDir()).create();
                this.serverManagerController.tableDatabasesManager.refresh();
            }
        } catch (FxerException e) {
            throw new DatabaserException(e);
        }
    }
}
