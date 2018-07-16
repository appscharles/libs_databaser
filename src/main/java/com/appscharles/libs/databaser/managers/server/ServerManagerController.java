package com.appscharles.libs.databaser.managers.server;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.managers.server.business.configurations.ServerManagerConfiguration;
import com.appscharles.libs.databaser.managers.server.business.managers.CheckboxesManager;
import com.appscharles.libs.databaser.managers.server.business.managers.DisableControllersManager;
import com.appscharles.libs.databaser.managers.server.business.managers.ServerRunnerManager;
import com.appscharles.libs.databaser.managers.server.business.managers.TableDatabasesManager;
import com.appscharles.libs.databaser.managers.server.business.models.AvailableDatabaseItem;
import com.appscharles.libs.databaser.managers.server.business.services.NewDatabaseService;
import com.appscharles.libs.databaser.managers.server.business.services.StartServerService;
import com.appscharles.libs.databaser.managers.server.business.services.StopServerService;
import com.appscharles.libs.databaser.managers.server.business.services.ToggleAutomationStart;
import com.appscharles.libs.dialoger.factories.ExceptionDialogFactory;
import com.appscharles.libs.fxer.controllers.AbstractControllerFX;
import com.appscharles.libs.reger.exceptions.RegerException;
import com.appscharles.libs.reger.validators.AutostartUserValidator;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.WindowEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Server manager controller.
 */
public class ServerManagerController extends AbstractControllerFX {

    private static final Logger logger = LogManager.getLogger(ServerManagerController.class);

    /**
     * The Resource bundle.
     */
    public ResourceBundle resourceBundle;

    /**
     * The Checkbox automation start server with system.
     */
    @FXML
    public CheckBox checkboxAutomationStartServerWithSystem;

    /**
     * The Table databases.
     */
    @FXML
    public TableView<AvailableDatabaseItem> tableDatabases;

    /**
     * The Button new database.
     */
    @FXML
    public Button buttonNewDatabase;

    /**
     * The Button start server.
     */
    @FXML
    public Button buttonStartServer;

    /**
     * The Button stop server.
     */
    @FXML
    public Button buttonStopServer;

    /**
     * The Column database name.
     */
    @FXML
    public TableColumn<String, AvailableDatabaseItem> columnDatabaseName;

    /**
     * The Column size.
     */
    @FXML
    public TableColumn<AvailableDatabaseItem, Long> columnSize;

    /**
     * The Column options.
     */
    @FXML
    public TableColumn<AvailableDatabaseItem, Button> columnOptions;

    /**
     * The Server manager configuration.
     */
    public ServerManagerConfiguration serverManagerConfiguration;

    /**
     * The Available databases.
     */
    public ObservableList<AvailableDatabaseItem> availableDatabases;

    public TableDatabasesManager tableDatabasesManager;

    public DisableControllersManager disableControllersManager;

    public ServerRunnerManager serverRunnerManager;

    public CheckboxesManager checkboxesManager;

    /**
     * Instantiates a new Server manager controller.
     *
     * @param serverManagerConfiguration the server manager configuration
     */
    public ServerManagerController(ServerManagerConfiguration serverManagerConfiguration) {
        this.serverManagerConfiguration = serverManagerConfiguration;
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        Platform.runLater(() -> {
            this.fXStage.setTitle(this.resourceBundle.getString("stage.title"));
        });
        this.tableDatabasesManager = new TableDatabasesManager(this);
        this.tableDatabasesManager.initialize();
        this.tableDatabasesManager.setCellValueFactories();
        this.disableControllersManager = new DisableControllersManager(this);
        try {
            this.disableControllersManager.initialize();

        } catch (DatabaserException e) {
            logger.error(e);
            ExceptionDialogFactory.create(this.resourceBundle.getString("view.dialog.exception.title"), e.getMessage(), e).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build().showAndWait();

        }
        this.serverRunnerManager = new ServerRunnerManager(this);
        this.serverRunnerManager.initialize();
        this.checkboxesManager =  new CheckboxesManager(this);

        try {
             this.checkboxesManager.initialize();
        } catch (DatabaserException e) {
            logger.error(e);
            ExceptionDialogFactory.create(this.resourceBundle.getString("view.dialog.exception.title"), e.getMessage(), e).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build().showAndWait();
        }
    }

    @Override
    public void onShown(WindowEvent event) {
        this.tableDatabasesManager.loadAvailableDatabases();
    }

    /**
     * New database.
     */
    @FXML
    public void newDatabase(){
        try {
            new NewDatabaseService(this).newDatabase();
        } catch (DatabaserException e) {
            logger.error(e);
            ExceptionDialogFactory.create(this.resourceBundle.getString("view.dialog.exception.title"), e.getMessage(), e).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build().showAndWait();
        }
    }

    /**
     * Start server.
     */
    @FXML
    public void startServer(){
        try {
            new StartServerService(this).startServer();
        } catch (DatabaserException e) {
            logger.error(e);
            ExceptionDialogFactory.create(this.resourceBundle.getString("view.dialog.exception.title"), e.getMessage(), e).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build().showAndWait();
            this.buttonStartServer.setDisable(true);
        }
    }

    /**
     * Stop server.
     */
    @FXML
    public void stopServer(){
        try {
            new StopServerService(this).stopServer();
        } catch (DatabaserException e) {
            logger.error(e);
            ExceptionDialogFactory.create(this.resourceBundle.getString("view.dialog.exception.title"), e.getMessage(), e).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build().showAndWait();

        }
    }

    /**
     * Toggle automation start.
     */
    @FXML
    public void toggleAutomationStart(){
        try {
            new ToggleAutomationStart(this).toggleAutomationStart();
        } catch (RegerException e) {
            logger.error(e);
            ExceptionDialogFactory.create(this.resourceBundle.getString("view.dialog.exception.title"), e.getMessage(), e).setIconStageResource("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png").build().showAndWait();

        }
    }
}
