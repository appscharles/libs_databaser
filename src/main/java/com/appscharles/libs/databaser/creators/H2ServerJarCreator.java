package com.appscharles.libs.databaser.creators;

import com.appscharles.libs.databaser.builders.H2ServerJarBuilder;
import com.appscharles.libs.databaser.configurators.H2ServerConfigurator;
import com.appscharles.libs.databaser.exceptions.DatabaserException;

import java.io.File;

/**
 * The type H 2 server jar creator.
 */
public class H2ServerJarCreator extends AbstractServerJarCreator {

    /**
     * Instantiates a new H 2 server jar creator.
     */
    public H2ServerJarCreator() {
        super(H2ServerConfigurator.getH2ServerDir());
    }

    @Override
    public void create() throws DatabaserException {
        File jarFile = new File(this.serverDir, H2ServerConfigurator.getH2ServerFileName());
        H2ServerJarBuilder.create(jarFile).build();
    }

}
