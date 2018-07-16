package com.appscharles.libs.databaser.managers.server.stages.newDatabase;

import com.appscharles.libs.databaser.managers.server.stages.newDatabase.business.models.NewDatabaseFields;
import com.appscharles.libs.fxer.exceptions.FxerException;
import com.appscharles.libs.fxer.factories.FXStageFactory;
import com.appscharles.libs.fxer.factories.IFXStageFactory;
import com.appscharles.libs.fxer.stages.FXStage;

/**
 * The type Server manager.
 */
public class NewDatabase {

    /**
     * Launch.
     *
     * @throws FxerException the fxer exception
     */
    public static NewDatabaseFields launch() throws FxerException {
        IFXStageFactory stageFactory = new FXStageFactory("/com/appscharles/libs/databaser/managers/server/stages/newDatabase/NewDatabaseView.fxml",
                "com/appscharles/libs/databaser/managers/server/stages/newDatabase/translations/NewDatabase");
        stageFactory.setIcon("/com/appscharles/libs/databaser/managers/server/ServerManagerIcon.png");
        stageFactory.addStylesheet("/com/appscharles/libs/databaser/managers/server/stages/newDatabase/styles/Root.css");
        NewDatabaseController controller = new NewDatabaseController();
        stageFactory.setController(controller);
        FXStage stage = stageFactory.create();
        stage.setResizable(false);
        stage.showAndWaitFX();
        return controller.getResult();
    }
}
