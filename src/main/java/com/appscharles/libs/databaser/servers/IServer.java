package com.appscharles.libs.databaser.servers;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 07.07.2018
 * Time: 13:10
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public interface IServer {

    void start() throws DatabaserException;

    void stop() throws DatabaserException;
}
