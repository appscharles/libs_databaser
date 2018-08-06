package com.appscharles.libs.databaser.managers.client.business.services;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.managers.client.ClientManagerController;
import com.appscharles.libs.databaser.migrators.FlyWayMigrator;
import com.appscharles.libs.databaser.validators.DatabaseExistValidator;
import com.appscharles.libs.databaser.validators.MigrationValidator;
import com.appscharles.libs.databaser.validators.PermissionValidator;
import com.appscharles.libs.databaser.validators.ServerRunningValidator;
import com.appscharles.libs.dialoger.factories.AlertConfirmationFactory;
import com.appscharles.libs.dialoger.factories.AlertFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 06.08.2018
 * Time: 13:58
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class DatabaseConnectService {

    private ClientManagerController controller;

    public DatabaseConnectService(ClientManagerController controller) {

        this.controller = controller;
    }

    public Boolean connect() throws DatabaserException {
        String serverAddress = this.controller.fieldServerAddress.getText().trim();
        Integer serverPort = this.controller.clientManagerConfiguration.getServerPort();
        String databaseName = this.controller.fieldDatabaseName.getText().trim();
        String user = this.controller.fieldUser.getText().trim();
        String password = this.controller.fieldPassword.getText().trim();
        String databaseUrl = "tcp://" + serverAddress + ":" + serverPort.toString() + "/" + databaseName;
        if (serverAddress.isEmpty() || databaseName.isEmpty() || user.isEmpty()) {
            AlertFactory.create(Alert.AlertType.WARNING, this.controller.resourceBundle.getString("view.dialog.warning.title"), this.controller.resourceBundle
                    .getString("view.dialog.warning.fill_in_all_fields")).setIconStageResource("/com/appscharles/libs/databaser/managers/client/ClientManagerIcon.png").build().showAndWait();
            return false;
        }

        if (ServerRunningValidator.isRunning(databaseUrl) == false) {
            AlertFactory.create(Alert.AlertType.ERROR, this.controller.resourceBundle.getString("view.dialog.exception.title"), this.controller.resourceBundle
                    .getString("view.dialog.exception.no_connection_server")).setIconStageResource("/com/appscharles/libs/databaser/managers/client/ClientManagerIcon.png").build().showAndWait();
            return false;
        }
        if (DatabaseExistValidator.exist(databaseUrl) == false) {
            AlertFactory.create(Alert.AlertType.ERROR, this.controller.resourceBundle.getString("view.dialog.exception.title"), this.controller.resourceBundle
                    .getString("view.dialog.exception.no_database")).setIconStageResource("/com/appscharles/libs/databaser/managers/client/ClientManagerIcon.png").build().showAndWait();
            return false;
        }

        if (new PermissionValidator(databaseUrl, user, password).isAccess() == false) {
            AlertFactory.create(Alert.AlertType.ERROR, this.controller.resourceBundle.getString("view.dialog.exception.title"), this.controller.resourceBundle
                    .getString("view.dialog.exception.no_permission")).setIconStageResource("/com/appscharles/libs/databaser/managers/client/ClientManagerIcon.png").build().showAndWait();
            return false;
        }

        MigrationValidator validator = new MigrationValidator(databaseUrl, user  ,password, this.controller.clientManagerConfiguration.getResourceMigrationPath());
        if (validator.isValid() == false){
            AlertConfirmationFactory factory = AlertConfirmationFactory.create(Alert.AlertType.CONFIRMATION, this.controller.resourceBundle.getString("view.dialog.confirmation.title_update_database"), this.controller.resourceBundle
                    .getString("view.dialog.confirmation.do_you_want_to_update_the_database")).setIconStageResource("/com/appscharles/libs/databaser/managers/client/ClientManagerIcon.png");
            Optional<ButtonType> result = factory.build().showAndWait();
            if (result.get() == factory.getButtonTypeYes()){
                FlyWayMigrator migrator = new FlyWayMigrator(databaseUrl, user  ,password, this.controller.clientManagerConfiguration.getResourceMigrationPath());
                migrator.migrate();
                AlertFactory.create(Alert.AlertType.INFORMATION, this.controller.resourceBundle.getString("view.dialog.information.title"), this.controller.resourceBundle
                        .getString("view.dialog.information.success_update_database")).setIconStageResource("/com/appscharles/libs/databaser/managers/client/ClientManagerIcon.png").build().showAndWait();
            } else {
                return false;
            }
        }

        return true;
    }
}
