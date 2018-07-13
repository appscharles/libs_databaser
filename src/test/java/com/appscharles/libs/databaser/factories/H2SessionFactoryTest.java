package com.appscharles.libs.databaser.factories;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.creators.H2DatabaseCreator;
import com.appscharles.libs.databaser.creators.IDatabaseCreator;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.migrators.H2FlyWayMigrator;
import com.appscharles.libs.databaser.programs.tester.Customer;
import com.appscharles.libs.databaser.runners.IServerRunner;
import com.appscharles.libs.databaser.runners.ServerRunner;
import com.appscharles.libs.databaser.validators.H2MigrationValidator;
import com.appscharles.libs.processer.exceptions.ProcesserException;
import com.appscharles.libs.processer.managers.WinKillManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 13.07.2018
 * Time: 15:55
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class H2SessionFactoryTest extends TestCase {

    @Test
    public void shouldAddCustomerToDatabase() throws ProcesserException, IOException, DatabaserException {
        File serverDir = this.temp.newFolder("serverDir123");
        new WinKillManager().killCommandLineContains("serverDir123");
        Integer port = 4531;
        IServerRunner runner = new ServerRunner(port);
        runner.setServerDir(serverDir);
        runner.enableRunForce();
        runner.start();
        IDatabaseCreator creator = new H2DatabaseCreator("tcp://localhost:"+ port +"/myDB", "root", "secret");
        creator.create();
        H2FlyWayMigrator migrator = new H2FlyWayMigrator("tcp://localhost:"+port+"/myDB", "root"  ,"secret",
                "com/appscharles/libs/databaser/programs/tester/dBMigrations");
        migrator.migrate();
        H2MigrationValidator validator = new H2MigrationValidator("tcp://localhost:"+port+"/myDB", "root"  ,"secret",
                "com/appscharles/libs/databaser/programs/tester/dBMigrations");
        Assert.assertTrue(validator.isValid());
        ISessionFactory sessionFactory = new H2SessionFactory("tcp://localhost:" + port + "/myDB", "root", "secret");
        sessionFactory.addAnnotationClass(Customer.class);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = new Customer();
        customer.setName("Example name");
        session.persist(customer);
        transaction.commit();
        session.close();
        sessionFactory.closeSessionFactory();
    }
}