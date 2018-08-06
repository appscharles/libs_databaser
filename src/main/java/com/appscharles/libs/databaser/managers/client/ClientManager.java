package com.appscharles.libs.databaser.managers.client;

import com.appscharles.libs.databaser.managers.client.business.configurations.ClientManagerConfiguration;
import com.appscharles.libs.fxer.exceptions.FxerException;
import com.appscharles.libs.fxer.factories.FXStageFactory;
import com.appscharles.libs.fxer.factories.IFXStageFactory;
import com.appscharles.libs.fxer.stages.FXStage;
import javafx.application.Platform;

/**
 * The type Server manager.
 */
public class ClientManager {

    /**
     * Launch.
     *
     * @throws FxerException the fxer exception
     */
    public static void launch(ClientManagerConfiguration clientManagerConfiguration) throws FxerException {
        IFXStageFactory stageFactory = new FXStageFactory("/com/appscharles/libs/databaser/managers/client/ClientManagerView.fxml",
                "com/appscharles/libs/databaser/managers/client/translations/ClientManager");
        stageFactory.setIcon("/com/appscharles/libs/databaser/managers/client/ClientManagerIcon.png");
        stageFactory.addStylesheet("/com/appscharles/libs/databaser/managers/client/styles/Root.css");
        stageFactory.setController(new ClientManagerController(clientManagerConfiguration));
        FXStage stage = stageFactory.create();
        stage.setResizable(false);
        if (clientManagerConfiguration.getTest()){
            Platform.setImplicitExit(false);
            stage.showFX();
            stage.closeFX();
        } else {
            stage.showAndWaitFX();
        }
    }
}
