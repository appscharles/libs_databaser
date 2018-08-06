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
public interface CallableThrowingConsumer<T, R,  E extends Exception> {
    R accept(T t) throws E, DatabaserException;
}