package com.appscharles.libs.databaser.managers.server.business.managers;

import com.appscharles.libs.databaser.managers.server.ServerManagerController;
import com.appscharles.libs.databaser.managers.server.business.models.AvailableDatabaseItem;
import com.appscharles.libs.databaser.managers.server.business.services.*;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Menu options manager.
 */
public class MenuOptionsManager {

    private static final Logger logger = LogManager.getLogger(ServerManagerController.class);

    /**
     * The Server manager controller.
     */
    public ServerManagerController serverManagerController;
    private AvailableDatabaseItem availableDatabaseItem;

    /**
     * Instantiates a new Menu options manager.
     *
     * @param serverManagerController the server manager controller
     * @param availableDatabaseItem
     */
    public MenuOptionsManager(ServerManagerController serverManagerController, AvailableDatabaseItem availableDatabaseItem) {
        this.serverManagerController = serverManagerController;
        this.availableDatabaseItem = availableDatabaseItem;
    }

    /**
     * Build menu button.
     *
     * @return the menu button
     */
    public MenuButton build() {
        MenuItem menuItemBackup = new MenuItem(this.serverManagerController.resourceBundle.getString("view.menu_button.backup"));
        menuItemBackup.setOnAction((event) -> {
            new BackupService(this.serverManagerController, event, this.availableDatabaseItem).backup();
        });
        MenuItem menuItemRestore = new MenuItem(this.serverManagerController.resourceBundle.getString("view.menu_button.restore"));
        menuItemRestore.setOnAction((event) -> {
            new RestoreService(this.serverManagerController, event, this.availableDatabaseItem).restore();
        });
        MenuItem menuItemRemove = new MenuItem(this.serverManagerController.resourceBundle.getString("view.menu_button.remove"));
        menuItemRemove.setOnAction((event) -> {
            new RemoveService(this.serverManagerController, event, this.availableDatabaseItem).remove();
        });
        MenuItem menuItemChangeUser = new MenuItem(this.serverManagerController.resourceBundle.getString("view.menu_button.change_user"));
        menuItemChangeUser.setOnAction((event) -> {
            new ChangeUserService(this.serverManagerController, event, this.availableDatabaseItem).changeUser();
        });
        MenuItem menuItemChangePassword = new MenuItem(this.serverManagerController.resourceBundle.getString("view.menu_button.change_password"));
        menuItemChangePassword.setOnAction((event) -> {
            new ChangePasswordService(this.serverManagerController, event, this.availableDatabaseItem).changePassword();
        });
        MenuButton menuButton = new MenuButton(this.serverManagerController.resourceBundle.getString("view.button.options"), null, menuItemBackup, menuItemRestore, menuItemRemove, menuItemChangeUser, menuItemChangePassword);
        menuButton.setMaxWidth(Double.MAX_VALUE);
        menuButton.disableProperty().bind(this.serverManagerController.buttonStartServer.disableProperty());
        return menuButton;
    }
}
