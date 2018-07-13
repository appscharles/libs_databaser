package com.appscharles.libs.databaser.extractors;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * The type H 2 jar extractor.
 */
public class H2JarExtractor {

    /**
     * To file.
     *
     * @param resource the resource
     * @param toFile   the to file
     * @throws DatabaserException the databaser exception
     */
    public void toFile(String resource, File toFile) throws DatabaserException {
        if (toFile.exists()){
            if (toFile.delete() == false){
                throw new DatabaserException("Can not delete file: " + toFile.getAbsolutePath());
            }
        }
        InputStream link = (getClass().getResourceAsStream(resource));
        try {
            Files.copy(link, toFile.getAbsoluteFile().toPath());
        } catch (IOException e) {
            throw new DatabaserException(e);
        }
    }
}
