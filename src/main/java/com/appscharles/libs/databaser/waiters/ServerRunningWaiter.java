package com.appscharles.libs.databaser.waiters;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.validators.ServerRunningValidator;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 13.07.2018
 * Time: 11:37
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class ServerRunningWaiter {

    /**
     * Wait for run in localhost.
     *
     * @param tcpPort the tcp port
     * @param timeout the timeout
     * @throws DatabaserException the databaser exception
     */
    public static void waitForRunInLocalhost(Integer tcpPort, Long timeout) throws DatabaserException {
        timeout = System.currentTimeMillis() + timeout;
        while (System.currentTimeMillis() < timeout) {
            if (ServerRunningValidator.isRunning("tcp://localhost:" + tcpPort)){
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new DatabaserException(e);
            }
        }
        throw new DatabaserException("Timeout wait for h2 server running [0007-001]");
    }
}
