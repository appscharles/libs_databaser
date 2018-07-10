package com.appscharles.libs.databaser.migrators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 10.07.2018
 * Time: 15:58
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public interface IFlyWayMigrator {

    void migrate() throws DatabaserException;
}
