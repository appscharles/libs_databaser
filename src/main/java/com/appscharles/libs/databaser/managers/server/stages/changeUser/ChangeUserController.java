package com.appscharles.libs.databaser.managers.server.stages.changeUser;

import com.appscharles.libs.databaser.changers.DatabaseUserChanger;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.managers.server.business.models.AvailableDatabaseItem;
import com.appscharles.libs.databaser.managers.server.stages.changeUser.validators.ChangeUserValidator;
import com.appscharles.libs.dialoger.factories.ExceptionDialogFactory;
import com.appscharles.libs.fxer.controllers.AbstractStageControllerFX;
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
public class ChangeUserController extends AbstractStageControllerFX {

    private static final Logger logger = LogManager.getLogger(ChangeUserController.class);

    @FXML
    public TextField fieldUser;

    @FXML
    public PasswordField fieldPassword;

    @FXML
    public TextField fieldNewUser;


    /**
     * The Resource bundle.
     */
    public ResourceBundle resourceBundle;

    public  AvailableDatabaseItem availableDatabaseItem;

    public ChangeUserController(AvailableDatabaseItem availableDatabaseItem) {
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
            if (new ChangeUserValidator(this).isValid()){
                DatabaseUserChanger.change(this.availableDatabaseItem.getDatabaseFile().getParentFile(), this.availableDatabaseItem.getDatabaseName(), this.fieldUser.getText().trim(), this.fieldPassword.getText().trim(), this.fieldNewUser.getText().trim());
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
