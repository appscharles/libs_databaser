package com.appscharles.libs.databaser.builders;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.programs.server.Server;
import com.appscharles.libs.jarer.builders.JarCreatorBuilder;
import com.appscharles.libs.jarer.creators.IJarCreator;
import com.appscharles.libs.jarer.exceptions.JarerException;
import org.h2.Driver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The type H 2 server jar builder.
 */
public class H2ServerJarBuilder {

    private File jarFile;

    private H2ServerJarBuilder() {

    }

    /**
     * Create h 2 server jar builder.
     *
     * @param jarFile the jar file
     * @return the h 2 server jar builder
     */
    public static H2ServerJarBuilder create(File jarFile) {
        H2ServerJarBuilder instance = new H2ServerJarBuilder();
        instance.jarFile = jarFile;
        return instance;
    }

    /**
     * Build.
     *
     * @throws DatabaserException the databaser exception
     */
    public void build() throws DatabaserException {
        try {
            IJarCreator jarCreator = JarCreatorBuilder.create(Server.NAME, Server.VERSION, Server.class, this.jarFile, H2ServerJarBuilder.class.getProtectionDomain().getCodeSource().getLocation()).build();
            jarCreator.addPackage("com.appscharles.libs.databaser");
//            jarCreator.addPackage("com.appscharles.libs.processer");
//            jarCreator.addPackage("com.appscharles.libs.ioer");
            jarCreator.addPackage("com.google.devtools.common.options");
            jarCreator.addPackage("com.google.common");
            jarCreator.addPackage("com.appscharles.libs.logger");
           // Class.forName("org.h2.Driver");
            jarCreator.addPackage("org.h2", new URL("jar:" + Driver.class.getProtectionDomain().getCodeSource().getLocation() + "!/org.h2"));
            jarCreator.addPackage("com.appscharles.libs.fxer");
//            jarCreator.addPackage("com.appscharles.libs.dialoger");
            jarCreator.addPackage("org.apache.logging.log4j", "org.apache.logging.log4j", "log4j-api", "2.11.0");
            jarCreator.addPackage("org.apache.logging.log4j.core", "org.apache.logging.log4j", "log4j-core", "2.11.0");
            jarCreator.create();
        } catch (JarerException | MalformedURLException e) {
            throw new DatabaserException(e);
        }
    }
}
