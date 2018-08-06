package com.appscharles.libs.databaser.managers.server.stages.changePassword;

import com.appscharles.libs.databaser.changers.DatabasePasswordChanger;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.managers.server.business.models.AvailableDatabaseItem;
import com.appscharles.libs.databaser.managers.server.stages.changePassword.validators.ChangePasswordValidator;
import com.appscharles.libs.databaser.managers.server.stages.changeUser.ChangeUserController;
import com.appscharles.libs.dialoger.factories.ExceptionDialogFactory;
import com.appscharles.libs.fxer.controllers.AbstractControllerFX;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Server manager controller.
 */
public class ChangePasswordController extends AbstractControllerFX {

    private static final Logger logger = LogManager.getLogger(ChangeUserController.class);

    @FXML
    public TextField fieldUser;

    @FXML
    public PasswordField fieldPassword;

    @FXML
    public PasswordField fieldNewPassword;

    @FXML
    public PasswordField fieldConfirmPassword;


    /**
     * The Resource bundle.
     */
    public ResourceBundle resourceBundle;

    public  AvailableDatabaseItem availableDatabaseItem;

    public ChangePasswordController(AvailableDatabaseItem availableDatabaseItem) {
        this.availableDatabaseItem = availableDatabaseItem;
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        Platform.runLater(() -> {
            this.fXStage.setTitle(this.resourceBundle.getString("stage.title"));
        });
    }

    @FXML
    public void change(){
        try {
            if (new ChangePasswordValidator(this).isValid()){
                DatabasePasswordChanger.change(this.availableDatabaseItem.getDatabaseFile().getParentFile(), this.availableDatabaseItem.getDatabaseName(), this.fieldUser.getText().trim(), this.fieldPassword.getText().trim(), this.fieldNewPassword.getText().trim());
                this.fXStage.close();
            }
        } catch (DatabaserException e) {
            logger.error(e, e);
            ExceptionDialogFactory.create(this.resourceBundle.getString("view.dialog.exception.title"), e.getMessage(), e).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build().showAndWait();
        }
    }

    @FXML void cancel(){
        this.fXStage.close();
    }
}
