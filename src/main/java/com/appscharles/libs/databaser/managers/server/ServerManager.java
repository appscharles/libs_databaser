package com.appscharles.libs.databaser.managers.server;

import com.appscharles.libs.databaser.managers.server.business.configurations.ServerManagerConfiguration;
import com.appscharles.libs.fxer.exceptions.FxerException;
import com.appscharles.libs.fxer.factories.FXStageFactory;
import com.appscharles.libs.fxer.factories.IFXStageFactory;
import com.appscharles.libs.fxer.stages.FXStage;
import javafx.application.Platform;

/**
 * The type Server manager.
 */
public class ServerManager {

    /**
     * Launch.
     *
     * @throws FxerException the fxer exception
     */
    public static void launch(ServerManagerConfiguration serverManagerConfiguration) throws FxerException {
        IFXStageFactory stageFactory = new FXStageFactory("/com/appscharles/libs/databaser/managers/server/ServerManagerView.fxml",
                "com/appscharles/libs/databaser/managers/server/translations/ServerManager");
        stageFactory.setIcon("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png");
        stageFactory.addStylesheet("/com/appscharles/libs/databaser/managers/server/styles/Root.css");
        stageFactory.addStylesheet("/com/appscharles/libs/databaser/managers/server/styles/TableDatabase.css");
        stageFactory.setController(new ServerManagerController(serverManagerConfiguration));
        FXStage stage = stageFactory.create();
        stage.setResizable(false);
        if (serverManagerConfiguration.getTest()){
            Platform.setImplicitExit(false);
            stage.showFX();
            stage.closeFX();
        } else {
            stage.showAndWaitFX();
        }
    }
}
