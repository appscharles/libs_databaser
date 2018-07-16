package com.appscharles.libs.databaser.operators;

import com.appscharles.libs.databaser.builders.TestH2SessionFactoryBuilder;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.factories.ISessionFactory;
import com.appscharles.libs.databaser.managers.SFManager;
import com.appscharles.libs.databaser.programs.tester.Customer;
import org.junit.Assert;
import org.junit.Test;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 16.07.2018
 * Time: 11:05
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class DBOperatorTest {
    @Test
    public void shouldGetCustomers() throws DatabaserException {
        ISessionFactory sessionFactory = TestH2SessionFactoryBuilder.create(5478, "com/appscharles/libs/databaser/programs/tester/dBMigrations", Customer.class).build();
        SFManager.addSessionFactory("databaser5", sessionFactory, true);
        Customer customer = new Customer();
        customer.setName("John");
        DBOperator.save(customer);
        Assert.assertNotNull(customer.getId());
        Customer customer2 = new Customer();
        customer2.setName("John 2");
        DBOperator.save(customer2);
        Assert.assertEquals(DBOperator.getAll(Customer.class).size(), 2);
        SFManager.closeDefaultSessionFactory();
    }
}