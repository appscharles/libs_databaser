package com.appscharles.libs.databaser.factories;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import org.hibernate.Session;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 13.07.2018
 * Time: 15:54
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public interface ISessionOpenable {

    Session openSession() throws DatabaserException;
}
