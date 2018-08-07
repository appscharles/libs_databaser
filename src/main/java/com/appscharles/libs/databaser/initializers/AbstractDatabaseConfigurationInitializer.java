package com.appscharles.libs.databaser.initializers;

import com.appscharles.libs.databaser.managers.configurations.ITestable;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 06.08.2018
 * Time: 16:48
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public abstract class AbstractDatabaseConfigurationInitializer implements IDatabaseConfigurationInitializer, ITestable {

    protected Boolean test;

    public AbstractDatabaseConfigurationInitializer(Boolean test) {
        this.test = test;
    }

    @Override
    public Boolean getTest() {
        return test;
    }

    @Override
    public void setTest(Boolean test) {
        this.test = test;
    }
}
