package com.appscharles.libs.databaser.validators;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.builders.ServerH2Builder;
import com.appscharles.libs.databaser.creators.DatabaseH2Creator;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.servers.IServer;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 10.07.2018
 * Time: 17:16
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class H2PermissionValidatorTest extends TestCase {

    @Test
    public void shouldAccessToDatabase() throws IOException, DatabaserException {
        File dBDir = this.temp.newFolder("dBDir_shouldAccessToDatabase");
        IServer server = ServerH2Builder.create(13523, dBDir).build();
        server.start();
        DatabaseH2Creator creator = new DatabaseH2Creator("tcp://localhost:"+13523+"/myDB", "root", "secret");
        creator.create();
        H2PermissionValidator validator = new H2PermissionValidator("tcp://localhost:"+13523+"/myDB", "root"  ,"secret");
        Assert.assertTrue(validator.isAccess());
        H2PermissionValidator validator2 = new H2PermissionValidator("tcp://localhost:"+13523+"/myDB", "root"  ,"secret2");
        Assert.assertFalse(validator2.isAccess());
        server.stop();
    }
}