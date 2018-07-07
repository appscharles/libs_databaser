package com.appscharles.libs.databaser.factories;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.validators.DatabaseExistValidator;
import org.h2.tools.Server;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 07.07.2018
 * Time: 16:03
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class ServerFactoryTest extends TestCase {

    @Test
    public void shouldRunH2Server() throws IOException, DatabaserException, SQLException {
        File dbDir = this.temp.newFolder("db Dir");
        Server server = ServerFactory.create(8645, dbDir)
                .disableAllowRemote()
                .build();
        server.start();
        Assert.assertEquals(server.getPort(), 8645);
        Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:8645/db", "2sa", "sa");

        connection.close();
        Assert.assertFalse(DatabaseExistValidator.exist("tcp://localhost:8645/db4"));
        server.stop();
    }
}