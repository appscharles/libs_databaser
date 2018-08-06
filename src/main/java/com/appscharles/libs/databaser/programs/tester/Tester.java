package com.appscharles.libs.databaser.programs.tester;

import com.appscharles.libs.databaser.creators.DatabaseCreator;
import com.appscharles.libs.databaser.creators.IDatabaseCreator;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.factories.DBSessionFactory;
import com.appscharles.libs.databaser.factories.ISessionFactory;
import com.appscharles.libs.databaser.migrators.FlyWayMigrator;
import com.appscharles.libs.databaser.runners.IServerRunner;
import com.appscharles.libs.databaser.runners.ServerRunner;
import com.appscharles.libs.databaser.validators.MigrationValidator;
import com.appscharles.libs.logger.configurators.Log4j2ConsoleFileRoller;
import com.appscharles.libs.logger.configurators.Log4jConsole;
import com.appscharles.libs.processer.managers.WinKillManager;
import org.apache.log4j.Level;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.nio.file.Files;

/**
 * The type Tester program.
 */
public class Tester {

    /**
     * The constant NAME.
     */
    public static final String NAME = "tester";

    /**
     * The constant VERSION.
     */
    public static final String VERSION = "1.0.0.0-dev0";

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws DatabaserException the databaser exception
     */
    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
            new Log4jConsole(Level.INFO).config();
            new Log4j2ConsoleFileRoller(org.apache.logging.log4j.Level.DEBUG).setLogsDir(Files.createTempDirectory("test").toFile()).config();
            Integer port = 3325;
            new WinKillManager().killCommandLineContains("serverDirTesterProgram");
            File serverDir = Files.createTempDirectory("serverDirTesterProgram").toFile();
            IServerRunner runner = new ServerRunner(port, "myApp");
            runner.setServerDir(serverDir);
            runner.enableRunForce();
            runner.start();
            IDatabaseCreator creator = new DatabaseCreator("tcp://localhost:"+ port +"/myDB", "root", "secret");
            creator.create();
            FlyWayMigrator migrator = new FlyWayMigrator("tcp://localhost:"+port+"/myDB", "root"  ,"secret",
                    "com/appscharles/libs/databaser/programs/tester/dBMigrations");
            migrator.migrate();
            MigrationValidator validator = new MigrationValidator("tcp://localhost:"+port+"/myDB", "root"  ,"secret",
                    "com/appscharles/libs/databaser/programs/tester/dBMigrations");
            validator.isValid();
            ISessionFactory sessionFactory = new DBSessionFactory("tcp://localhost:" + port + "/myDB", "root", "secret");
            sessionFactory.addAnnotationClass(Customer.class);
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Customer customer = new Customer();
            customer.setName("Example name");
            session.persist(customer);
            transaction.commit();
            session.close();
            sessionFactory.closeSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
