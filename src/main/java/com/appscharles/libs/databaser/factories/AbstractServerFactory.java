package com.appscharles.libs.databaser.factories;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Abstract server factory.
 */
public abstract class AbstractServerFactory implements IServerFactory, IPortable, IArgumentable {

    /**
     * The Port.
     */
    protected Integer port;

    /**
     * The D b dir.
     */
    protected File dBDir;

    /**
     * The Allow remote.
     */
    protected Boolean allowRemote = true;

    /**
     * The Arguments.
     */
    protected List<String> arguments;

    /**
     * Instantiates a new Abstract server factory.
     */
    protected AbstractServerFactory(){
        this.arguments = new ArrayList<>();
    }

    public AbstractServerFactory disableAllowRemote(){
        this.allowRemote = false;
        return this;
    }

    public Integer getPort() {
        return this.port;
    }

    @Override
    public void addArgument(String argument) {
        this.arguments.add(argument);
    }

    /**
     * Get prepare arguments string [ ].
     *
     * @return the string [ ]
     */
    protected String[] getPrepareArguments(){
       return Arrays.copyOf(this.arguments.toArray(), this.arguments.toArray().length, String[].class);
    }
}
