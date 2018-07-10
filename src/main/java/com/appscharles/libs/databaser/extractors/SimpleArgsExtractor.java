package com.appscharles.libs.databaser.extractors;

/**
 * The type Simple args extractor.
 */
public class SimpleArgsExtractor {

    /**
     * Get arg string.
     *
     * @param arg  the arg
     * @param args the args
     * @return the string
     */
    public static String getArg(String arg, String[] args){
        for (String a : args) {
            if (a.startsWith("--" + arg + "=")){
                return a.split("=")[1];
            }
        }
        throw new IllegalArgumentException("Not found argument: " + arg);
    }
}
