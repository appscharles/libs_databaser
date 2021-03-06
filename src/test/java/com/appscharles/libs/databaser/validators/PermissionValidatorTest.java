package com.appscharles.libs.databaser.validators;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.creators.DatabaseCreator;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.runners.IServerRunner;
import com.appscharles.libs.databaser.runners.ServerRunner;
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
public class PermissionValidatorTest extends TestCase {

    @Test
    public void shouldAccessToDatabase() throws IOException, DatabaserException {
        File dBDir = this.temp.newFolder("dBDir_shouldAccessToDatabase");
        IServerRunner runner = new ServerRunner(13523, "myApp");
        runner.enableRunForce();
        runner.setServerDir(dBDir);
        runner.start();
        DatabaseCreator creator = new DatabaseCreator("tcp://localhost:"+13523+"/myDB", "root", "secret");
        creator.create();
        PermissionValidator validator = new PermissionValidator("tcp://localhost:"+13523+"/myDB", "root"  ,"secret");
        Assert.assertTrue(validator.isAccess());
        PermissionValidator validator2 = new PermissionValidator("tcp://localhost:"+13523+"/myDB", "root"  ,"secret2");
        Assert.assertFalse(validator2.isAccess());
        runner.stop();
    }
}