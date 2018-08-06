package com.appscharles.libs.databaser.managers.server.business.managers;

import com.appscharles.libs.databaser.converters.BytesRepresentationConverter;
import com.appscharles.libs.databaser.managers.server.ServerManagerController;
import com.appscharles.libs.databaser.managers.server.business.models.AvailableDatabaseItem;
import com.appscharles.libs.databaser.managers.server.business.services.*;
import com.appscharles.libs.fxer.builders.MenuButtonBuilder;
import com.appscharles.libs.fxer.tables.cells.UniversalTableCell;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;

/**
 * The type Table databases manager.
 */
public class TableDatabasesManager {

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
        this.serverManagerController.columnSize.setCellFactory(new UniversalTableCell<AvailableDatabaseItem, Long>().forTableColumn((Long size, AvailableDatabaseItem availableDatabaseItem) ->{
            return new Label(BytesRepresentationConverter.convert(size));
        }));
        this.serverManagerController.columnOptions.setCellFactory(new UniversalTableCell<AvailableDatabaseItem, Node>().forTableColumn((Node size, AvailableDatabaseItem availableDatabaseItem) ->{
            MenuButtonBuilder builder = MenuButtonBuilder.create(this.serverManagerController.resourceBundle.getString("view.button.options"))
                    .addMenuItem(this.serverManagerController.resourceBundle.getString("view.menu_button.backup"), (actionEvent)->{
                        new BackupService(this.serverManagerController, actionEvent, availableDatabaseItem).backup();
                    }).addMenuItem(this.serverManagerController.resourceBundle.getString("view.menu_button.restore"), (actionEvent)->{
                        new RestoreService(this.serverManagerController, actionEvent, availableDatabaseItem).restore();
                    }).addMenuItem(this.serverManagerController.resourceBundle.getString("view.menu_button.remove"), (actionEvent)->{
                        new RemoveService(this.serverManagerController, actionEvent, availableDatabaseItem).remove();
                    }).addMenuItem(this.serverManagerController.resourceBundle.getString("view.menu_button.change_user"), (actionEvent)->{
                        new ChangeUserService(this.serverManagerController, actionEvent, availableDatabaseItem).changeUser();
                    }).addMenuItem(this.serverManagerController.resourceBundle.getString("view.menu_button.change_password"), (actionEvent)->{
                        new ChangePasswordService(this.serverManagerController, actionEvent, availableDatabaseItem).changePassword();
                    });
            builder.getMenuButton().setMaxWidth(Double.MAX_VALUE);
            builder.getMenuButton().disableProperty().bind(this.serverManagerController.buttonStartServer.disableProperty());
            return builder.build();
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
        if (this.serverManagerController.serverManagerConfiguration.getServerDir().exists() == false){
            return;
        }
        for(File file : this.serverManagerController.serverManagerConfiguration.getServerDir().listFiles()) {
            if(file.getName().endsWith(".mv.db")){
                if (file.getName().startsWith("isRunning.")){
                    continue;
                }
                String dBName = file.getName().replace(".mv.db", "");
                this.serverManagerController.availableDatabases.add(new AvailableDatabaseItem(dBName,file, file.length()));
            }
        }
    }
}
