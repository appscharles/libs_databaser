package com.appscharles.libs.databaser.exceptions;

/**
 * The type Databaser exception.
 */
public class DatabaserException extends Exception {

    // Database not created, because it is exist. [0001-000]
    // Can not connection to database. [0002-000]
    // Database is not exists [0003-001]
    // H2 server is not launched [0004-001]
    // H2 Server is not running, because in port ... is running server [0005-001]
    // Port ... is using [0006-001]
    // Timeout wait for h2 server running [0007-001]
    // H2 server not launched with command ... [0008-001]
    // File h2 database not created, because it is exist. [0009-000]
    // Could not change user, because database is not exist. [0010-000]
    // Username can only contain letters. [0011-000]

    /**
     * The Serial version uid.
     */
    static final long serialVersionUID = 7821375828146530432L;

    /**
     * Instantiates a new Databaser exception.
     */
    public DatabaserException() {
        super();
    }

    /**
     * Instantiates a new Databaser exception.
     *
     * @param message the message
     */
    public DatabaserException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Databaser exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public DatabaserException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Databaser exception.
     *
     * @param cause the cause
     */
    public DatabaserException(Throwable cause) {
        super(cause);
    }

}

