package com.appscharles.libs.databaser.managers.server.stages.changePassword;

import com.appscharles.libs.databaser.managers.server.business.models.AvailableDatabaseItem;
import com.appscharles.libs.fxer.exceptions.FxerException;
import com.appscharles.libs.fxer.factories.FXStageFactory;
import com.appscharles.libs.fxer.factories.IFXStageFactory;
import com.appscharles.libs.fxer.stages.FXStage;

/**
 * The type Change password.
 */
public class ChangePassword {

    /**
     * Launch.
     *
     * @param availableDatabaseItem the available database item
     * @throws FxerException the fxer exception
     */
    public static void launch(AvailableDatabaseItem availableDatabaseItem) throws FxerException {
        IFXStageFactory stageFactory = new FXStageFactory("/com/appscharles/libs/databaser/managers/server/stages/changePassword/ChangePasswordView.fxml",
                "com/appscharles/libs/databaser/managers/server/stages/changePassword/translations/ChangePassword");
        stageFactory.setIcon("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png");
        stageFactory.addStylesheet("/com/appscharles/libs/databaser/managers/server/stages/changePassword/styles/Root.css");
        ChangePasswordController controller = new ChangePasswordController(availableDatabaseItem);
        stageFactory.setController(controller);
        FXStage stage = stageFactory.create();
        stage.setResizable(false);
        stage.showAndWaitFX();
    }
}
