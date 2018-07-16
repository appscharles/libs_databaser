package com.appscharles.libs.databaser.validators;

import java.io.File;

/**
 * The type H 2 database file exist validator.
 */
public class H2FileDatabaseExistValidator {

    /**
     * Exist boolean.
     *
     * @param dBName    the d b name
     * @param serverDir the server dir
     * @return the boolean
     */
    public static Boolean exist(String dBName, File serverDir){
        if (serverDir.exists() == false){
            return false;
        }
        for(File file : serverDir.listFiles()) {
            if(file.getName().startsWith(dBName + ".")){
                return true;
            }
        }
        return false;
    }
}
