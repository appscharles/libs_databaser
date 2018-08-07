package com.appscharles.libs.databaser.initializers;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.exceptions.ThrowingConsumer;
import com.appscharles.libs.databaser.factories.ISessionFactory;
import com.appscharles.libs.databaser.managers.client.ClientManager;
import com.appscharles.libs.databaser.managers.client.business.configurations.ClientManagerConfiguration;
import com.appscharles.libs.databaser.validators.ConnectionValidator;
import com.appscharles.libs.fxer.exceptions.FxerException;
import com.appscharles.libs.proper.containers.CryptedProperties;
import com.appscharles.libs.proper.exceptions.ProperException;

import java.io.File;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 06.08.2018
 * Time: 16:47
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class DatabaseConfigurationInitializer extends AbstractDatabaseConfigurationInitializer {

    private String saltPassword;

    private File fileCryptedProperties;

    private Integer serverPort;

    private File serverDir;

    private String appName;

    private String resourceMigrationPath;

    private ThrowingConsumer<ISessionFactory, DatabaserException> sessionFactoryConsumer;

    public DatabaseConfigurationInitializer(String saltPassword, File fileCryptedProperties, Integer serverPort, File serverDir, String appName, String resourceMigrationPath, ThrowingConsumer<ISessionFactory, DatabaserException> sessionFactoryConsumer) {
        super(false);
        this.saltPassword = saltPassword;
        this.fileCryptedProperties = fileCryptedProperties;
        this.serverPort = serverPort;
        this.serverDir = serverDir;
        this.appName = appName;
        this.resourceMigrationPath = resourceMigrationPath;
        this.sessionFactoryConsumer = sessionFactoryConsumer;
    }

    @Override
    public void initialize() throws DatabaserException {
        try {
            CryptedProperties properties = new CryptedProperties();
            properties.load(this.fileCryptedProperties, this.saltPassword);
            String rememberServerAddress = properties.getProperty("libs.databaser.remember_server_address", "localhost");
            String rememberDatabaseName = properties.getProperty("libs.databaser.remember_database_name", "");
            String rememberUser = properties.getProperty("libs.databaser.remember_user", "");
            String rememberPassword = properties.getProperty("libs.databaser.remember_password", "");
            String databaseURL = "tcp://" + rememberServerAddress + ":" + this.serverPort.toString() + "/" + rememberDatabaseName;
            if (ConnectionValidator.isValid(databaseURL, rememberUser, rememberPassword, this.resourceMigrationPath) == false) {
                ClientManagerConfiguration clientManagerConfiguration = new ClientManagerConfiguration(this.serverPort, this.appName, this.serverDir, rememberServerAddress, rememberDatabaseName, rememberUser, rememberPassword, this.resourceMigrationPath, this.fileCryptedProperties, this.saltPassword, this.sessionFactoryConsumer);
                clientManagerConfiguration.setTest(this.test);
                ClientManager.launch(clientManagerConfiguration);
            } else {
                ISessionFactoryInitializer initializer = new SessionFactoryInitializer(databaseURL, rememberUser, rememberPassword, this.sessionFactoryConsumer);
                initializer.initialize();
            }
        } catch (ProperException | FxerException e) {
            throw new DatabaserException(e);
        }
    }


}
