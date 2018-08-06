package com.appscharles.libs.databaser.managers.server.stages.changeUser.validators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.managers.server.stages.changeUser.ChangeUserController;
import com.appscharles.libs.databaser.validators.PermissionValidator;
import com.appscharles.libs.dialoger.factories.AlertFactory;
import javafx.scene.control.Alert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 16.07.2018
 * Time: 20:49
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class ChangeUserValidator {

    private static final Logger logger = LogManager.getLogger(ChangeUserValidator.class);

    private ChangeUserController changeUserController;

    public ChangeUserValidator(ChangeUserController changeUserController) {
        this.changeUserController = changeUserController;
    }

    public Boolean isValid() throws DatabaserException {
        String dBName = this.changeUserController.availableDatabaseItem.getDatabaseName();
        String oldUser = this.changeUserController.fieldUser.getText().trim();
        String password = this.changeUserController.fieldPassword.getText().trim();
        String newUser = this.changeUserController.fieldNewUser.getText().trim();
        if (oldUser.isEmpty() || newUser.isEmpty() || password.isEmpty()){
            AlertFactory.create(Alert.AlertType.WARNING, this.changeUserController.resourceBundle.getString("view.dialog.warning.title"), this.changeUserController.resourceBundle
                    .getString("view.dialog.warning.fill_in_all_fields")).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build().showAndWait();
            return false;
        }
        if (newUser.matches("[a-zA-Z]+") == false){
            AlertFactory.create(Alert.AlertType.WARNING, this.changeUserController.resourceBundle.getString("view.dialog.warning.title"), this.changeUserController.resourceBundle
                    .getString("view.dialog.warning.only_contain_letters")).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build().showAndWait();
            return false;
        }
        if (new PermissionValidator("file:" + this.changeUserController.availableDatabaseItem.getDatabaseFile().getParentFile().getAbsolutePath() + File.separator + dBName, oldUser, password).isAccess() == false){
            AlertFactory.create(Alert.AlertType.WARNING, this.changeUserController.resourceBundle.getString("view.dialog.warning.title"), this.changeUserController.resourceBundle
                    .getString("view.dialog.warning.incorrect_old_user_or_password")).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build().showAndWait();
            return false;
        }
        return true;
    }
}
