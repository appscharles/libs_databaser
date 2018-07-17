package com.appscharles.libs.databaser.managers.server.stages.changeUser;

import com.appscharles.libs.databaser.managers.server.business.models.AvailableDatabaseItem;
import com.appscharles.libs.fxer.exceptions.FxerException;
import com.appscharles.libs.fxer.factories.FXStageFactory;
import com.appscharles.libs.fxer.factories.IFXStageFactory;
import com.appscharles.libs.fxer.stages.FXStage;

/**
 * The type Server manager.
 */
public class ChangeUser {

    /**
     * Launch.
     *
     * @throws FxerException the fxer exception
     * @param serverManagerConfiguration
     */
    public static void launch(AvailableDatabaseItem availableDatabaseItem) throws FxerException {
        IFXStageFactory stageFactory = new FXStageFactory("/com/appscharles/libs/databaser/managers/server/stages/changeUser/ChangeUserView.fxml",
                "com/appscharles/libs/databaser/managers/server/stages/changeUser/translations/ChangeUser");
        stageFactory.setIcon("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png");
        stageFactory.addStylesheet("/com/appscharles/libs/databaser/managers/server/stages/changeUser/styles/Root.css");
        ChangeUserController controller = new ChangeUserController(availableDatabaseItem);
        stageFactory.setController(controller);
        FXStage stage = stageFactory.create();
        stage.setResizable(false);
        stage.showAndWaitFX();
    }
}
