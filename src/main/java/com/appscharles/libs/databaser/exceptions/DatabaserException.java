package com.appscharles.libs.databaser.exceptions;

/**
 * The type Databaser exception.
 */
public class DatabaserException extends Exception {

    // Database not created, because it is exist. [0001-000]
    // Can not connection to database. [0002-000]
    // Database is not exists [0003-001]

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

