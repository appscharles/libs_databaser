package com.appscharles.libs.databaser.validators;

import com.appscharles.libs.databaser.configurators.H2ServerConfigurator;

import java.io.File;

/**
 * The type H 2 server jar validator.
 */
public class H2ServerJarValidator {

    /**
     * Exist boolean.
     *
     * @param serverDir the server dir
     * @return the boolean
     */
    public static Boolean exist(File serverDir){
        return new File(serverDir, H2ServerConfigurator.getH2ServerFileName()).exists();
    }
}
