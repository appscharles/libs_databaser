package com.appscharles.libs.databaser.validators;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.builders.ServerH2Builder;
import com.appscharles.libs.databaser.creators.DatabaseH2Creator;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.migrators.H2FlyWayMigrator;
import com.appscharles.libs.databaser.servers.IServer;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 10.07.2018
 * Time: 16:22
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class H2MigrationValidatorTest extends TestCase {

    @Test
    public void shouldValidDatabase() throws IOException, DatabaserException {
        File dBDir = this.temp.newFolder("dBDir_shouldIsNotValidDatabase");
        IServer server = ServerH2Builder.create(12423, dBDir).build();
        server.start();
        DatabaseH2Creator creator = new DatabaseH2Creator("tcp://localhost:"+12423+"/myDB", "root", "secret");
        creator.create();
        H2FlyWayMigrator migrator = new H2FlyWayMigrator("tcp://localhost:"+12423+"/myDB", "root"  ,"secret",
                "com/appscharles/libs/databaser/programs/manager/dbmigration");
        migrator.migrate();
        H2MigrationValidator validator = new H2MigrationValidator("tcp://localhost:"+12423+"/myDB", "root"  ,"secret",
                "com/appscharles/libs/databaser/programs/manager/dbmigration");
        Assert.assertTrue(validator.isValid());
        server.stop();
    }

    @Test
    public void shouldIsNotValidDatabase() throws IOException, DatabaserException {
        File dBDir = this.temp.newFolder("dBDir_shouldIsNotValidDatabase");
        IServer server = ServerH2Builder.create(12523, dBDir).build();
        server.start();
        DatabaseH2Creator creator = new DatabaseH2Creator("tcp://localhost:"+12523+"/myDB", "root", "secret");
        creator.create();
        H2MigrationValidator validator = new H2MigrationValidator("tcp://localhost:"+12523+"/myDB", "root"  ,"secret",
                "com/appscharles/libs/databaser/programs/manager/dbmigration");
        Assert.assertFalse("Not found migrations? Maybe run `generateMigration` gradle task.", validator.isValid());
        server.stop();
    }
}