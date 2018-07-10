package com.appscharles.libs.databaser.creators;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.builders.ServerH2Builder;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.servers.IServer;
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
 * Time: 17:14
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class DatabaseCreatorTest extends TestCase {

    @Test
    public void shouldTestCreateDatabaseIfNotExist() throws IOException, DatabaserException, SQLException {
        File dbDir = this.temp.newFolder("db Dir");
        IServer server = ServerH2Builder.create(3452,  dbDir).build();
        server.start();
        IDatabaseCreator creator = new DatabaseCreator("tcp://localhost:3452/myDB", "root", "secret");
        creator.create();
        Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:3452/myDB", "root", "secret");
        connection.close();
    }

    @Test(expected = DatabaserException.class)
    public void shouldThrowException() throws IOException, DatabaserException, SQLException {
        File dbDir = this.temp.newFolder("db Dir");
        IServer server = ServerH2Builder.create(3456, dbDir).build();
        server.start();
        IDatabaseCreator creator = new DatabaseCreator("tcp://localhost:3456/myDB", "root", "secret");
        creator.create();
        creator.create();
    }
}