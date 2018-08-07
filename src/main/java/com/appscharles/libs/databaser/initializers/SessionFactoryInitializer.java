package com.appscharles.libs.databaser.initializers;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.exceptions.ThrowingConsumer;
import com.appscharles.libs.databaser.factories.DBSessionFactory;
import com.appscharles.libs.databaser.factories.ISessionFactory;
import com.appscharles.libs.databaser.managers.SFManager;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 07.08.2018
 * Time: 08:41
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class SessionFactoryInitializer implements ISessionFactoryInitializer {

    private String databaseURL;

    private String user;

    private String password;

    public SessionFactoryInitializer(String databaseURL, String user, String password, ThrowingConsumer<ISessionFactory, DatabaserException> sessionFactoryConsumer) {
        this.databaseURL = databaseURL;
        this.user = user;
        this.password = password;
        this.sessionFactoryConsumer = sessionFactoryConsumer;
    }

    private ThrowingConsumer<ISessionFactory, DatabaserException> sessionFactoryConsumer;

    @Override
    public void initialize() throws DatabaserException {
        ISessionFactory sessionFactory = new DBSessionFactory(this.databaseURL, this.user, this.password);
        if (this.sessionFactoryConsumer != null){
            this.sessionFactoryConsumer.accept(sessionFactory);
        }
        SFManager.addSessionFactory("default", sessionFactory, true);
    }
}
