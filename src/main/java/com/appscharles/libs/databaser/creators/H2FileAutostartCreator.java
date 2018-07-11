package com.appscharles.libs.databaser.creators;

import com.appscharles.libs.databaser.configurators.H2ServerConfigurator;
import com.appscharles.libs.ioer.services.FileWriter;

import java.io.File;
import java.io.IOException;

/**
 * The type H 2 file autostart creator.
 */
public class H2FileAutostartCreator {

    /**
     * Create.
     *
     * @param serverDir the server dir
     * @throws IOException the io exception
     */
    public static void create(File serverDir) throws IOException {
        File fileBat = new File(serverDir, H2ServerConfigurator.getH2ServerAutostartFileName());
        FileWriter.write(fileBat, "java -jar " + fileBat.getAbsolutePath());
    }
}
