package com.appscharles.libs.databaser.validators;

import java.io.File;

/**
 * The type H 2 file database exist validator.
 */
public class H2FileDatabaseExistValidator {

    /**
     * Exist boolean.
     *
     * @param dBName the d b name
     * @param dBDir  the d b dir
     * @return the boolean
     */
    public static Boolean exist(String dBName, File dBDir){
        if (dBDir.exists() == false){
            return false;
        }
        for(File file : dBDir.listFiles()) {
            if(file.getName().startsWith(dBName + ".")){
                return true;
            }
        }
        return false;
    }
}
