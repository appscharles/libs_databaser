package com.appscharles.libs.databaser.factories;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 07.07.2018
 * Time: 11:11
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public interface IServerFactory extends IAllowRemoteDisableable {

    <T> T build() throws DatabaserException;
}
