package com.appscharles.libs.databaser.validators;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 07.07.2018
 * Time: 16:54
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class DatabaseExistValidator {

    public static Boolean exist(String databaseUrl) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + databaseUrl + ";IFEXISTS=TRUE")) {
            return true;
        } catch (SQLException e) {
            if (e.getMessage().contains("90013-")){
                return false;
            }
            return true;
        }
    }
}
