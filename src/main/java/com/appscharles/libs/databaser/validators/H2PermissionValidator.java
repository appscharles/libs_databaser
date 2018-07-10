package com.appscharles.libs.databaser.validators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 10.07.2018
 * Time: 17:13
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class H2PermissionValidator extends AbstractPermissionValidator {


    /**
     * Instantiates a new Database creator.
     *
     * @param databaseUrl the database url
     * @param username    the username
     * @param password    the password
     */
    public H2PermissionValidator(String databaseUrl, String username, String password) {
        super(databaseUrl, username, password);
    }

    @Override
    public Boolean isAccess() throws DatabaserException {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + this.databaseUrl + ";IFEXISTS=TRUE", this.username, this.password)) {
            return true;
        } catch (SQLException e) {
            if (e.getMessage().contains("[28000-")){
                return false;
            }
           throw new DatabaserException(e);
        }
    }
}
