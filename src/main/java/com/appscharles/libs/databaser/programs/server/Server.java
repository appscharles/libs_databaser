package com.appscharles.libs.databaser.programs.server;

import com.appscharles.libs.databaser.builders.ServerH2Builder;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.servers.IServer;
import com.appscharles.libs.dialoger.factories.ExceptionDialogFactory;
import com.appscharles.libs.fxer.controls.UTF8Control;
import com.appscharles.libs.fxer.parsers.ArgsParser;
import com.appscharles.libs.logger.configurators.Log4j2ConsoleFileRoller;
import com.appscharles.libs.logger.services.LoggerConfigurator;
import com.google.devtools.common.options.OptionsParsingException;
import com.sun.javafx.application.PlatformImpl;
import javafx.scene.control.Alert;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ResourceBundle;

/**
 * The type Server.
 */
public class Server {

    /**
     * The constant NAME.
     */
    public static final String NAME = "h2server";

    /**
     * The constant VERSION.
     */
    public static final String VERSION = "1.0.0.0-dev0";

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws DatabaserException the databaser exception
     */
    public static void main(String[] args) throws DatabaserException {
        Boolean testMode = false;
        try {
            ServerOptions options = ArgsParser.parse(args, ServerOptions.class);
            ServerOptionsChecker.check(options);
            testMode = Boolean.valueOf(options.testMode);
            IServer server = ServerH2Builder.create(Integer.valueOf(options.port), new File(".")).build();
            server.start();
            if (Boolean.valueOf(options.testMode)) {
                Thread.sleep(Integer.valueOf(options.testTimeoutExit));
                server.stop();
                System.exit(0);
            }
        } catch (OptionsParsingException | DatabaserException | InterruptedException e) {
            LoggerConfigurator.config(new Log4j2ConsoleFileRoller(Level.INFO).setLogsDir(new File(".", "logs")));
            Logger logger = LogManager.getLogger(Server.class);
            logger.error(e);
            if (testMode == false){
                try {
                    PlatformImpl.startup(() -> {
                    });
                    PlatformImpl.runAndWait(() -> {
                        ResourceBundle resourceBundle = ResourceBundle.getBundle("com/appscharles/libs/databaser/programs/server/translations/Server", new UTF8Control());
                        Alert alert = ExceptionDialogFactory.create(resourceBundle.getString("exception_dialog.title"), resourceBundle.getString("exception_dialog.content_text"), e).setIconStageResource("/com/appscharles/libs/databaser/programs/server/ServerIcon.png").build();
                        alert.showAndWait();
                    });
                    System.exit(0);
                } catch (Exception e1) {
                    throw new DatabaserException(e1);
                }
            }
            throw new DatabaserException(e);
        }
    }
}
