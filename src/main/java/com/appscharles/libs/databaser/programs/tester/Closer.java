package com.appscharles.libs.databaser.programs.tester;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 14.07.2018
 * Time: 15:04
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class Closer {

    public static void main(String[] args) throws SQLException {
       Connection connection = DriverManager.getConnection("jdbc:h2:mem:jpa2ddl", "sa", "");
        connection.createStatement().execute("SHUTDOWN");
    }
}
