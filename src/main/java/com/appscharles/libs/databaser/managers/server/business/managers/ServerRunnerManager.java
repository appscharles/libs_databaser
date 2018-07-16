package com.appscharles.libs.databaser.managers.server.business.managers;

import com.appscharles.libs.databaser.managers.server.ServerManagerController;
import com.appscharles.libs.databaser.runners.IServerRunner;
import com.appscharles.libs.databaser.runners.ServerRunner;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 16.07.2018
 * Time: 15:24
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class ServerRunnerManager {

    private ServerManagerController serverManagerController;

    public IServerRunner serverRunner;

    /**
     * Instantiates a new Start server service.
     *
     * @param serverManagerController the server manager controller
     */
    public ServerRunnerManager(ServerManagerController serverManagerController) {
        this.serverManagerController = serverManagerController;
    }

    public void initialize() {
        this.serverRunner = new ServerRunner(this.serverManagerController.serverManagerConfiguration.getServerPort(), this.serverManagerController.serverManagerConfiguration.getAppName());
        this.serverRunner.setServerDir(this.serverManagerController.serverManagerConfiguration.getServerDir());
    }
}
