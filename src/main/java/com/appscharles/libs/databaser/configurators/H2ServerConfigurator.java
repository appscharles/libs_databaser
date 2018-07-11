package com.appscharles.libs.databaser.configurators;

import java.io.File;

/**
 * The type H 2 server configurator.
 */
public class H2ServerConfigurator {

    /**
     * Get h 2 server dir file.
     *
     * @return the file
     */
    public static File getH2ServerDir(){
        return new File(System.getenv("ProgramFiles(X86)"), "Databaser");
    }

    /**
     * Get h 2 server file name string.
     *
     * @return the string
     */
    public static String getH2ServerFileName(){
        return "h2server.jar";
    }

    /**
     * Get h 2 server autostart file name string.
     *
     * @return the string
     */
    public static String getH2ServerAutostartFileName(){
        return "autostart.bat";
    }

    /**
     * Get autostart reg key string.
     *
     * @return the string
     */
    public static String getAutostartRegKey(){
        return "databaser";
    }

    /**
     * Get autostart bat file name string.
     *
     * @return the string
     */
    public static String getAutostartBatFileName(){
        return "databaser.bat";
    }
}
