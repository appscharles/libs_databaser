package com.appscharles.libs.databaser.configurators;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The type H 2 jar arguments configurator.
 */
public class H2JarArgumentsConfigurator {

    /**
     * Gets command arguments.
     *
     * @param tcpPort     the tcp port
     * @param webPort     the web port
     * @param tcpPassword the tcp password
     * @param serverDir   the server dir
     * @return the command arguments
     */
    public static String getCommandArguments(Integer tcpPort, Integer webPort, File serverDir) {
        List<String> arguments = new ArrayList<>();
        arguments.add("-tcpPort " + tcpPort);
        if (webPort != null) {
            arguments.add("-webPort " + webPort);
            arguments.add("-web");
        }
        arguments.add("-tcp");
        arguments.add("-tcpAllowOthers");
        arguments.add("-baseDir \"" + serverDir.getAbsolutePath() + "\"");
        return " " + String.join(" ", arguments);
    }
}
