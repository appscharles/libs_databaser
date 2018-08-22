package com.appscharles.libs.databaser.managers.server.stages.newDatabase;

import com.appscharles.libs.databaser.managers.server.stages.newDatabase.business.models.NewDatabaseFields;
import com.appscharles.libs.databaser.managers.server.stages.newDatabase.business.validators.NewDatabaseValidator;
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
public class NewDatabaseController extends AbstractStageControllerFX {

    private static final Logger logger = LogManager.getLogger(NewDatabaseController.class);

    @FXML
    public TextField fieldName;

    @FXML
    public TextField fieldUser;

    @FXML
    public PasswordField fieldPassword;

    @FXML
    public PasswordField fieldConfirmPassword;

    /**
     * The Resource bundle.
     */
    public ResourceBundle resourceBundle;

    private NewDatabaseFields result;

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        Platform.runLater(() -> {
            this.fXStage.setTitle(this.resourceBundle.getString("stage.title"));
        });
    }

    @FXML
    public void add(){
        if (new NewDatabaseValidator(this).isValid()){
            this.result = new NewDatabaseFields(this.fieldName.getText().trim(), this.fieldUser.getText().trim(), this.fieldPassword.getText().trim());
            this.fXStage.close();
        }
    }

    @FXML
    public void cancel(){
        this.fXStage.close();
    }

    /**
     * Getter for property 'result'.
     *
     * @return Value for property 'result'.
     */
    public NewDatabaseFields getResult() {
        return this.result;
    }
}
