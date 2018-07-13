package com.appscharles.libs.databaser.validators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 13.07.2018
 * Time: 10:17
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class PortUsingValidatorTest {

    @Test
    public void shouldPortIsNotUsing() throws DatabaserException, IOException {
        Assert.assertFalse(PortUsingValidator.isUsing("127.0.0.1", 8021));
        ServerSocket serverSocket = new ServerSocket(8021);
        Assert.assertTrue(PortUsingValidator.isUsing("127.0.0.1", 8021));
        serverSocket.close();
    }
}