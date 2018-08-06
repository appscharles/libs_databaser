package com.appscharles.libs.databaser.managers.client.business.services;

import com.appscharles.libs.databaser.managers.client.ClientManagerController;
import com.appscharles.libs.databaser.managers.client.business.configurations.ClientManagerConfiguration;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 06.08.2018
 * Time: 13:34
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class RememberDataLoadService {

    private ClientManagerController controller;

    public RememberDataLoadService(ClientManagerController controller) {
        this.controller = controller;
    }

    public void load(){
        ClientManagerConfiguration config = this.controller.clientManagerConfiguration;
        this.controller.fieldServerAddress.setText(config.getRememberServerAddress());
        this.controller.fieldDatabaseName.setText(config.getRememberDatabaseName());
        this.controller.fieldUser.setText(config.getRememberUser());
        this.controller.fieldPassword.setText(config.getRememberPassword());
    }
}
