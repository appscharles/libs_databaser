package com.appscharles.libs.databaser.factories;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

/**
 * The interface Server factory.
 */
public interface IServerFactory extends IAllowRemoteDisableable {

    /**
     * Build t.
     *
     * @param <T> the type parameter
     * @return the t
     * @throws DatabaserException the databaser exception
     */
    <T> T build() throws DatabaserException;
}
