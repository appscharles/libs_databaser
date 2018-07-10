package com.appscharles.libs.databaser.migrators;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.builders.ServerH2Builder;
import com.appscharles.libs.databaser.creators.DatabaseH2Creator;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.servers.IServer;
import org.flywaydb.core.internal.exception.FlywaySqlException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 10.07.2018
 * Time: 15:53
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class FlyWayMigratorTest extends TestCase {

    @Test
    public void shouldMigrate() throws IOException, DatabaserException {
        File dBDir = this.temp.newFolder("dBDir_shouldMigrate");
        IServer server = ServerH2Builder.create(12323, dBDir).build();
        server.start();
        DatabaseH2Creator creator = new DatabaseH2Creator("tcp://localhost:"+12323+"/myDB", "root", "secret");
        creator.create();
        H2FlyWayMigrator migrator = new H2FlyWayMigrator("tcp://localhost:"+12323+"/myDB", "root"  ,"secret",
                "com/appscharles/libs/databaser/programs/manager/dbmigration");
        migrator.migrate();
    }

    @Test(expected = FlywaySqlException.class)
    public void shouldThrowExceptionNotExistDatabase() throws IOException, DatabaserException {
        File dBDir = this.temp.newFolder("dBDir_shouldMigrate");
        IServer server = ServerH2Builder.create(12333, dBDir).build();
        server.start();
        H2FlyWayMigrator migrator = new H2FlyWayMigrator("tcp://localhost:"+12333+"/myDB", "root"  ,"secret",
                "com/appscharles/libs/databaser/programs/manager/dbmigration");
        migrator.migrate();
    }
}