package com.appscharles.libs.databaser.extractors;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * The type Url server extractor.
 */
public class URLServerExtractor {

    /**
     * Extract string.
     *
     * @param databaseURL the database url
     * @return the string
     * @throws DatabaserException the databaser exception
     */
    public static String extract(String databaseURL) throws DatabaserException {
        try {
            URI uRI = new URI(databaseURL);
            return uRI.getScheme() + "://" + uRI.getAuthority();
        } catch (URISyntaxException e) {
        throw new DatabaserException(e);
        }
    }
}
