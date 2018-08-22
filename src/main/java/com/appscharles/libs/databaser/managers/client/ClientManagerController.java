package com.appscharles.libs.databaser.managers.client;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.managers.client.business.configurations.ClientManagerConfiguration;
import com.appscharles.libs.databaser.managers.client.business.services.DataRememberService;
import com.appscharles.libs.databaser.managers.client.business.services.DatabaseConnectService;
import com.appscharles.libs.databaser.managers.client.business.services.RememberDataLoadService;
import com.appscharles.libs.databaser.managers.server.ServerManager;
import com.appscharles.libs.databaser.managers.server.business.configurations.ServerManagerConfiguration;
import com.appscharles.libs.dialoger.factories.ExceptionDialogFactory;
import com.appscharles.libs.fxer.controllers.AbstractStageControllerFX;
import com.appscharles.libs.fxer.exceptions.FxerException;
import com.appscharles.libs.proper.exceptions.ProperException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Client manager controller.
 */
public class ClientManagerController extends AbstractStageControllerFX {

    private static final Logger logger = LogManager.getLogger(ClientManagerController.class);

    /**
     * The Resource bundle.
     */
    public ResourceBundle resourceBundle;

    /**
     * The Client manager configuration.
     */
    public ClientManagerConfiguration clientManagerConfiguration;

    @FXML
    public TextField fieldServerAddress;

    @FXML
    public TextField fieldDatabaseName;

    @FXML
    public TextField fieldUser;

    @FXML
    public PasswordField fieldPassword;

    private DatabaseConnectService databaseConnectService;

    private DataRememberService dataRememberService;

    /**
     * Instantiates a new Client manager controller.
     *
     * @param clientManagerConfiguration the client manager configuration
     */
    public ClientManagerController(ClientManagerConfiguration clientManagerConfiguration) {
        this.clientManagerConfiguration = clientManagerConfiguration;
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        Platform.runLater(() -> {
            this.fXStage.setTitle(this.resourceBundle.getString("stage.title"));
        });
        RememberDataLoadService rememberDataLoadService = new RememberDataLoadService(this);
        rememberDataLoadService.load();
        this.databaseConnectService = new DatabaseConnectService(this);
        this.dataRememberService = new DataRememberService(this);
    }

    @FXML
    private void serverManager(){
        ServerManagerConfiguration config = new ServerManagerConfiguration(this.clientManagerConfiguration.getServerPort(), this.clientManagerConfiguration.getAppName(), this.clientManagerConfiguration.getServerDir());
        config.setTest(this.clientManagerConfiguration.getTest());
        try {
            ServerManager.launch(config);
        } catch (FxerException e) {
            logger.error(e, e);
            ExceptionDialogFactory.create(this.resourceBundle.getString("view.dialog.exception.title"), e.getMessage(), e).setIconStageResource("/com/appscharles/libs/databaser/managers/client/ClientManagerIcon.png").build().showAndWait();
        }
    }

    @FXML
    public void cancel(){
        this.fXStage.close();
    }

    @FXML
    public void connect(){
        try {
            if (this.databaseConnectService.connect()){
                this.dataRememberService.remember();
                this.getFXStage().close();
            }
        } catch (DatabaserException | ProperException e) {
            logger.error(e, e);
            ExceptionDialogFactory.create(this.resourceBundle.getString("view.dialog.exception.title"), e.getMessage(), e).setIconStageResource("/com/appscharles/libs/databaser/managers/client/ClientManagerIcon.png").build().showAndWait();
        }

    }
}
