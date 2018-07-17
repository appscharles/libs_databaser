package com.appscharles.libs.databaser.changers;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.creators.H2FileDatabaseCreator;
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
 * Time: 14:45
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class H2DatabasePasswordChangerTest extends TestCase {

    @Test
    public void shouldChangePasswordOfDatabase() throws IOException, DatabaserException {
        File serverDir = this.temp.newFolder("shouldChangePasswordOfDatabase");
        new H2FileDatabaseCreator("myDB", "root", "secret", serverDir).create();
        try (Connection connection = DriverManager.getConnection("jdbc:h2:file:"+ serverDir.getAbsolutePath() + File.separator + "myDB;IFEXISTS=TRUE","root", "secret")) {
            Assert.assertTrue(connection.isClosed() == false);
        } catch (SQLException e) {
            throw new DatabaserException(e);
        }
        H2DatabasePasswordChanger.change(serverDir, "myDB", "root", "secret", "secret2");
        try (Connection connection = DriverManager.getConnection("jdbc:h2:file:"+ serverDir.getAbsolutePath() + File.separator + "myDB;IFEXISTS=TRUE","root", "secret2")) {
            Assert.assertTrue(connection.isClosed() == false);
        } catch (SQLException e) {
            throw new DatabaserException(e);
        }
    }
}