package com.appscharles.libs.databaser.managers.server.stages.changePassword.validators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.managers.server.stages.changePassword.ChangePasswordController;
import com.appscharles.libs.databaser.validators.H2PermissionValidator;
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
public class ChangePasswordValidator {

    private static final Logger logger = LogManager.getLogger(ChangePasswordValidator.class);

    private ChangePasswordController changePasswordController;

    public ChangePasswordValidator(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }

    public Boolean isValid() throws DatabaserException {
        String dBName = this.changePasswordController.availableDatabaseItem.getDatabaseName();
        String user = this.changePasswordController.fieldUser.getText().trim();
        String oldPassword = this.changePasswordController.fieldPassword.getText().trim();
        String newPassword = this.changePasswordController.fieldNewPassword.getText().trim();
        String confirmPassword = this.changePasswordController.fieldConfirmPassword.getText().trim();

        if (user.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()){
            AlertFactory.create(Alert.AlertType.WARNING, this.changePasswordController.resourceBundle.getString("view.dialog.warning.title"), this.changePasswordController.resourceBundle
                    .getString("view.dialog.warning.fill_in_all_fields")).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build().showAndWait();
            return false;
        }
        if (newPassword.equals(confirmPassword) == false){
            AlertFactory.create(Alert.AlertType.WARNING, this.changePasswordController.resourceBundle.getString("view.dialog.warning.title"), this.changePasswordController.resourceBundle
                    .getString("view.dialog.warning.entered_passwords_are_different")).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build().showAndWait();
            return false;
        }
        if (new H2PermissionValidator("file:" + this.changePasswordController.availableDatabaseItem.getDatabaseFile().getParentFile().getAbsolutePath() + File.separator + dBName, user, oldPassword).isAccess() == false){
            AlertFactory.create(Alert.AlertType.WARNING, this.changePasswordController.resourceBundle.getString("view.dialog.warning.title"), this.changePasswordController.resourceBundle
                    .getString("view.dialog.warning.incorrect_user_or_old_password")).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build().showAndWait();
            return false;
        }
        return true;
    }
}
