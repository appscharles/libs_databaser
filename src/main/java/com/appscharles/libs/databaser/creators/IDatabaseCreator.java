package com.appscharles.libs.databaser.creators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 07.07.2018
 * Time: 17:34
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public interface IDatabaseCreator {

    void create() throws DatabaserException;
}
