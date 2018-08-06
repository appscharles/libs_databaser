package com.appscharles.libs.databaser.extractors;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * The type Resource extractor.
 */
public class ResourceExtractor {

    /**
     * To file.
     *
     * @param resource     the resource
     * @param file         the file
     * @param forceExtract the force extract
     * @throws DatabaserException the databaser exception
     */
    public void toFile(String resource, File file, Boolean forceExtract) throws DatabaserException {
        if (file.exists() && forceExtract){
            if (file.delete() == false){
                throw new DatabaserException("Can not delete file: " + file.getAbsolutePath());
            }
        }
        InputStream link = (getClass().getResourceAsStream(resource));
        try {
            Files.copy(link, file.getAbsoluteFile().toPath());
        } catch (IOException e) {
            throw new DatabaserException(e);
        }
    }

    /**
     * To file.
     *
     * @param resource the resource
     * @param file     the file
     * @throws DatabaserException the databaser exception
     */
    public void toFile(String resource, File file) throws DatabaserException {
        toFile(resource, file, false);
    }
}
