package com.appscharles.libs.databaser.builders;

import com.appscharles.libs.databaser.creators.DatabaseCreator;
import com.appscharles.libs.databaser.creators.IDatabaseCreator;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.factories.DBSessionFactory;
import com.appscharles.libs.databaser.factories.ISessionFactory;
import com.appscharles.libs.databaser.migrators.FlyWayMigrator;
import com.appscharles.libs.databaser.runners.IServerRunner;
import com.appscharles.libs.databaser.runners.ServerRunner;
import com.appscharles.libs.databaser.validators.MigrationValidator;
import com.appscharles.libs.processer.exceptions.ProcesserException;
import com.appscharles.libs.processer.managers.WinKillManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 14.07.2018
 * Time: 11:05
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class TempSessionFactoryBuilder {

    private Integer port;

    private String serverDirectoryName;

    private String serverURL;

    private String user;

    private String password;

    private String dBName;

    private String resourceMigrationPath;

    private List<String> packagesToScan;

    private List<Class> annotationClasses;

    private TempSessionFactoryBuilder() {
        this.packagesToScan = new ArrayList<>();
        this.annotationClasses = new ArrayList<>();
    }

    public static TempSessionFactoryBuilder create(Integer port, String resourceMigrationPath, Class annotationClass) {
        return create(port, resourceMigrationPath, annotationClass, null);
    }

    public static TempSessionFactoryBuilder create(Integer port, String resourceMigrationPath, String packageToScan) {
        return create(port, resourceMigrationPath, null, packageToScan);
    }

    public static TempSessionFactoryBuilder create(Integer port, String resourceMigrationPath, Class annotationClass, String packageToScan) {
        TempSessionFactoryBuilder instance = new TempSessionFactoryBuilder();
        instance.port = port;
        instance.serverDirectoryName = "serverDir_testH2SessionFactory";
        instance.serverURL = "tcp://localhost";
        instance.user = "root";
        instance.password = "secret";
        instance.dBName = "H2testDB";
        instance.resourceMigrationPath = resourceMigrationPath;
        if (annotationClass != null){
            instance.annotationClasses.add(annotationClass);
        }
        if (packageToScan != null){
            instance.packagesToScan.add(packageToScan);
        }
        return instance;
    }

    public ISessionFactory build() throws DatabaserException {
        try {
            File serverDir = Files.createTempDirectory(this.serverDirectoryName).toFile();
            new WinKillManager().killCommandLineContains(this.serverDirectoryName);
            IServerRunner runner = new ServerRunner(this.port, "myApp");
            runner.setServerDir(serverDir);
            runner.enableRunForce();
            runner.start();
            String databaseURL = this.serverURL + ((this.port != null) ? ":" + this.port : "") + "/" + this.dBName;
            IDatabaseCreator creator = new DatabaseCreator(databaseURL, this.user, this.password);
            creator.create();
            FlyWayMigrator migrator = new FlyWayMigrator(databaseURL, this.user, this.password, this.resourceMigrationPath);
            migrator.migrate();
            MigrationValidator validator = new MigrationValidator(databaseURL, this.user, this.password, this.resourceMigrationPath);
            if (validator.isValid() == false) {
                throw new DatabaserException("Migrations is not valid");
            }
            ISessionFactory sessionFactory = new DBSessionFactory(databaseURL, this.user, this.password);
            this.annotationClasses.forEach((c)->sessionFactory.addAnnotationClass(c));
            this.packagesToScan.forEach((p)->sessionFactory.addPackageToScan(p));
            return sessionFactory;
        } catch (IOException | DatabaserException | ProcesserException e) {
            throw new DatabaserException(e);
        }
    }

    /**
     * Setter for property 'serverDirectoryName'.
     *
     * @param serverDirectoryName Value to set for property 'serverDirectoryName'.
     */
    public TempSessionFactoryBuilder setServerDirectoryName(String serverDirectoryName) {
        this.serverDirectoryName = serverDirectoryName;
        return this;
    }

    /**
     * Setter for property 'serverURL'.
     *
     * @param serverURL Value to set for property 'serverURL'.
     */
    public TempSessionFactoryBuilder setServerURL(String serverURL) {
        this.serverURL = serverURL;
        return this;
    }

    /**
     * Setter for property 'user'.
     *
     * @param user Value to set for property 'user'.
     */
    public TempSessionFactoryBuilder setUser(String user) {
        this.user = user;
        return this;
    }

    /**
     * Setter for property 'password'.
     *
     * @param password Value to set for property 'password'.
     */
    public TempSessionFactoryBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Setter for property 'dBName'.
     *
     * @param dBName Value to set for property 'dBName'.
     */
    public TempSessionFactoryBuilder setdBName(String dBName) {
        this.dBName = dBName;
        return this;
    }

    /**
     * Setter for property 'resourceMigrationPath'.
     *
     * @param resourceMigrationPath Value to set for property 'resourceMigrationPath'.
     */
    public TempSessionFactoryBuilder setResourceMigrationPath(String resourceMigrationPath) {
        this.resourceMigrationPath = resourceMigrationPath;
        return this;
    }

    public List<String> getPackagesToScan() {
        return packagesToScan;
    }

    public List<Class> getAnnotationClasses() {
        return annotationClasses;
    }
}
