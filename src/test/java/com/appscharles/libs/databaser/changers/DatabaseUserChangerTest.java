package com.appscharles.libs.databaser.changers;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.creators.DatabaseCreator;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
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
 * Date: 17.07.2018
 * Time: 13:14
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class DatabaseUserChangerTest extends TestCase {

    @Test
    public void shouldChangeUserDatabase() throws IOException, DatabaserException, InterruptedException {
        File serverDir = this.temp.newFolder("shouldChangeUserDatabase");
        new DatabaseCreator("file:"+serverDir.getAbsolutePath() + File.separator+"myDB", "root", "secret").create();
        try (Connection connection = DriverManager.getConnection("jdbc:h2:file:"+ serverDir.getAbsolutePath() + File.separator + "myDB;IFEXISTS=TRUE","root", "secret")) {
            Assert.assertTrue(connection.isClosed() == false);
        } catch (SQLException e) {
            throw new DatabaserException(e);
        }
        DatabaseUserChanger.change(serverDir, "myDB", "root", "secret", "root2");
        try (Connection connection = DriverManager.getConnection("jdbc:h2:file:"+ serverDir.getAbsolutePath() + File.separator + "myDB;IFEXISTS=TRUE","root2", "secret")) {
            Assert.assertTrue(connection.isClosed() == false);
        } catch (SQLException e) {
            throw new DatabaserException(e);
        }
    }
}