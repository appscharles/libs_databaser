package com.appscharles.libs.databaser.exceptions;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 14.07.2018
 * Time: 11:38
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {
    void accept(T t) throws E, DatabaserException;
}
