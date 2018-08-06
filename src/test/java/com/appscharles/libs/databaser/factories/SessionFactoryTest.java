package com.appscharles.libs.databaser.factories;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.builders.TempSessionFactoryBuilder;
import com.appscharles.libs.databaser.creators.DatabaseCreator;
import com.appscharles.libs.databaser.creators.IDatabaseCreator;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.operators.DBOperator;
import com.appscharles.libs.databaser.managers.SFManager;
import com.appscharles.libs.databaser.migrators.FlyWayMigrator;
import com.appscharles.libs.databaser.programs.tester.Customer;
import com.appscharles.libs.databaser.runners.IServerRunner;
import com.appscharles.libs.databaser.runners.ServerRunner;
import com.appscharles.libs.databaser.validators.MigrationValidator;
import com.appscharles.libs.processer.exceptions.ProcesserException;
import com.appscharles.libs.processer.managers.WinKillManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 13.07.2018
 * Time: 15:55
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class SessionFactoryTest extends TestCase {

    @Test
    public void shouldAddCustomerToDatabase() throws ProcesserException, IOException, DatabaserException {
        File serverDir = this.temp.newFolder("serverDir123");
        new WinKillManager().killCommandLineContains("serverDir123");
        Integer port = 4531;
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
        Assert.assertTrue(validator.isValid());
        ISessionFactory sessionFactory = new DBSessionFactory("tcp://localhost:" + port + "/myDB", "root", "secret");
        sessionFactory.addPackageToScan("com.appscharles.libs.databaser");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = new Customer();
        customer.setName("Example name");
        session.persist(customer);
        transaction.commit();
        session.close();
        sessionFactory.closeSessionFactory();
    }

    @Test
    public void shouldAddCustomerToDatabase2() throws DatabaserException {
        ISessionFactory sessionFactory = TempSessionFactoryBuilder.create(5478, "com/appscharles/libs/databaser/programs/tester/dBMigrations", Customer.class).build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = new Customer();
        customer.setName("Example name");
        session.persist(customer);
        transaction.commit();
        session.close();
        sessionFactory.closeSessionFactory();
    }

    @Test
    public void shouldAddCustomerToDatabase3() throws DatabaserException, ClassNotFoundException, InterruptedException {
        ISessionFactory sessionFactory = TempSessionFactoryBuilder.create(5478, "com/appscharles/libs/databaser/programs/tester/dBMigrations", Customer.class).build();
        SFManager.addSessionFactory("databaser", sessionFactory, true);
        Customer customer = new Customer();
        customer.setName("Example name");
        DBOperator.save(customer);
        Assert.assertNotNull(customer.getCreatedAt());
        Assert.assertNotNull(customer.getUpdatedAt());
        Assert.assertEquals(customer.getUpdatedAt(), customer.getCreatedAt());
        Thread.sleep(1000);
        customer.setName("Example name2");
        DBOperator.save(customer);
        Assert.assertNotEquals(customer.getUpdatedAt(), customer.getCreatedAt());
        Customer c = DBOperator.get(Customer.class, customer.getId());
        Assert.assertEquals(c.getId(), customer.getId());
        Customer c2 = DBOperator.session((session -> {
            return session.get(Customer.class, c.getId());
        }));
        Customer customer2 = new Customer();
        customer2.setName("Example name");
        DBOperator.save(customer2);

        List<Customer> customers = DBOperator.session((session -> {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root);
            Query<Customer> q = session.createQuery(criteriaQuery);
            return q.getResultList();
        }));
        Assert.assertEquals(customers.size(), 2);
        Assert.assertEquals(c2.getId(), c.getId());
        DBOperator.delete(c);
        SFManager.closeDefaultSessionFactory();
    }
}