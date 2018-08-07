package com.appscharles.libs.databaser.initializers;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 06.08.2018
 * Time: 16:48
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public interface ISessionFactoryInitializer {

    void initialize() throws DatabaserException;
}
