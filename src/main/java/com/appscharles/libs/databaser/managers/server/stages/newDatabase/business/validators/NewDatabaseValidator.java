package com.appscharles.libs.databaser.managers.server.stages.newDatabase.business.validators;

import com.appscharles.libs.databaser.managers.server.stages.newDatabase.NewDatabaseController;
import com.appscharles.libs.dialoger.factories.AlertFactory;
import javafx.scene.control.Alert;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 16.07.2018
 * Time: 20:49
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class NewDatabaseValidator {

    private NewDatabaseController newDatabaseController;

    public NewDatabaseValidator(NewDatabaseController newDatabaseController) {
        this.newDatabaseController = newDatabaseController;
    }

    public Boolean isValid(){
        String dBName = this.newDatabaseController.fieldName.getText().trim();
        String user = this.newDatabaseController.fieldUser.getText().trim();
        String password = this.newDatabaseController.fieldPassword.getText().trim();
        String confirmPassword = this.newDatabaseController.fieldConfirmPassword.getText().trim();
        if (dBName.isEmpty() || user.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            AlertFactory.create(Alert.AlertType.WARNING, this.newDatabaseController.resourceBundle.getString("view.dialog.warning.title"), this.newDatabaseController.resourceBundle
                    .getString("view.dialog.warning.fill_in_all_fields")).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build().showAndWait();
            return false;
        }
        if (password.equals(confirmPassword) == false){
            AlertFactory.create(Alert.AlertType.WARNING, this.newDatabaseController.resourceBundle.getString("view.dialog.warning.title"), this.newDatabaseController.resourceBundle
                    .getString("view.dialog.warning.entered_passwords_are_different")).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build().showAndWait();
            return false;
        }
        return true;
    }
}
