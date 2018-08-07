package com.appscharles.libs.databaser.managers.client.business.services;

import com.appscharles.libs.databaser.managers.client.ClientManagerController;
import com.appscharles.libs.proper.containers.CryptedProperties;
import com.appscharles.libs.proper.exceptions.ProperException;

/**
 * The type Data remember service.
 */
public class DataRememberService {

    private ClientManagerController controller;

    /**
     * Instantiates a new Data remember service.
     *
     * @param controller the controller
     */
    public DataRememberService(ClientManagerController controller) {
        this.controller = controller;
    }

    /**
     * Remember.
     *
     * @throws ProperException the proper exception
     */
    public void remember() throws ProperException {
        String serverAddress = this.controller.fieldServerAddress.getText().trim();
        String databaseName = this.controller.fieldDatabaseName.getText().trim();
        String user = this.controller.fieldUser.getText().trim();
        String password = this.controller.fieldPassword.getText().trim();
        CryptedProperties properties = new CryptedProperties();
        properties.load(this.controller.clientManagerConfiguration.getFileCryptedProperties(), this.controller.clientManagerConfiguration.getSaltPassword());
        properties.setProperty("libs.databaser.remember_server_address",serverAddress);
        properties.setProperty("libs.databaser.remember_database_name", databaseName);
        properties.setProperty("libs.databaser.remember_user", user);
       properties.setProperty("libs.databaser.remember_password", password);
        properties.store(this.controller.clientManagerConfiguration.getFileCryptedProperties(), this.controller.clientManagerConfiguration.getSaltPassword());
    }
}
