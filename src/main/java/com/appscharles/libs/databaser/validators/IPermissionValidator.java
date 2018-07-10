package com.appscharles.libs.databaser.validators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 10.07.2018
 * Time: 16:24
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public interface IPermissionValidator {

    Boolean isAccess() throws DatabaserException;
}
