package com.appscharles.libs.databaser.models;

import java.util.Calendar;

/**
 * The interface Timestampable.
 */
public interface IModelTimestampable {

    /**
     * Gets created at.
     *
     * @return the created at
     */
    Calendar getCreatedAt();

    /**
     * Sets created at.
     *
     * @param createdAt the created at
     */
    void setCreatedAt(Calendar createdAt);

    /**
     * Gets updated at.
     *
     * @return the updated at
     */
    Calendar getUpdatedAt();

    /**
     * Setter for property 'updatedAt'.
     *
     * @param updatedAt Value to set for property 'updatedAt'.
     */
    ;

    /**
     * Sets updated at.
     *
     * @param updatedAt the updated at
     */
    void setUpdatedAt(Calendar updatedAt);
}
