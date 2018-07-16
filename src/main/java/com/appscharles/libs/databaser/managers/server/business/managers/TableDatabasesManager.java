package com.appscharles.libs.databaser.managers.server.business.managers;

import com.appscharles.libs.databaser.managers.server.ServerManagerController;
import com.appscharles.libs.databaser.managers.server.business.converters.BytesRepresentationConverter;
import com.appscharles.libs.databaser.managers.server.business.models.AvailableDatabaseItem;
import com.appscharles.libs.fxer.tables.cells.ActionButtonTableCell;
import com.appscharles.libs.fxer.tables.cells.UniversalTableCell;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * The type Table databases manager.
 */
public class TableDatabasesManager {

    private static final Logger logger = LogManager.getLogger(ServerManagerController.class);

    /**
     * The Server manager controller.
     */
    public ServerManagerController serverManagerController;

    /**
     * Instantiates a new Table databases manager.
     *
     * @param serverManagerController the server manager controller
     */
    public TableDatabasesManager(ServerManagerController serverManagerController) {
        this.serverManagerController = serverManagerController;
    }

    /**
     * Initialize.
     */
    public void initialize() {
        this.serverManagerController.availableDatabases = FXCollections.observableArrayList();
        this.serverManagerController.tableDatabases.setItems(this.serverManagerController.availableDatabases);
        loadAvailableDatabases();
    }

    /**
     * Sets cell value factories.
     */
    public void setCellValueFactories() {
        this.serverManagerController.columnDatabaseName.setCellValueFactory(new PropertyValueFactory<>("databaseName"));
        this.serverManagerController.columnSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        this.serverManagerController.columnSize.setCellFactory(new UniversalTableCell<AvailableDatabaseItem, Long>().forTableColumn((Long size) ->{
            return new Label(BytesRepresentationConverter.convert(size));
        }));

        this.serverManagerController.columnOptions.setCellFactory(ActionButtonTableCell.forTableColumn(this.serverManagerController.resourceBundle.getString("view.button.options"), (AvailableDatabaseItem availableDatabaseItem) -> {
            logger.trace("Open menu options");
            return availableDatabaseItem;
        }, (Button buttonOptions) ->{
            buttonOptions.disableProperty().bind(this.serverManagerController.buttonStartServer.disableProperty());
        }));
    }

    /**
     * Refresh.
     */
    public void refresh() {
        loadAvailableDatabases();
    }

    public void loadAvailableDatabases(){
        this.serverManagerController.availableDatabases.clear();
        for(File file : this.serverManagerController.serverManagerConfiguration.getServerDir().listFiles()) {
            if(file.getName().endsWith(".mv.db") || file.getName().endsWith(".h2.db")){
                if (file.getName().startsWith("isRunning.")){
                    continue;
                }
                String dBName = file.getName().replace(".mv.db", "").replace(".h2.db","");
                this.serverManagerController.availableDatabases.add(
                        new AvailableDatabaseItem(dBName, file.length())
                );
            }
        }
    }
}
