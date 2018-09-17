package com.appscharles.libs.databaser.generators;

import com.appscharles.libs.databaser.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 17.09.2018
 * Time: 12:59
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class NextVersionMigrationGeneratorTest extends TestCase {

    @Test
    public void shouldGetNextMigrationVersion() throws IOException {
        File dir = this.temp.newFolder();
        NextVersionMigrationGenerator generator = new NextVersionMigrationGenerator(dir);
        Assert.assertTrue(generator.generate().equals(1));
        File file1 = this.temp.newFile("v1__wffwe");
        file1.renameTo(new File(dir, file1.getName()));
        Assert.assertTrue(generator.generate().equals(2));
        File file2 = this.temp.newFile("v3__wffwe");
        file2.renameTo(new File(dir, file2.getName()));
        Assert.assertTrue(generator.generate().equals(4));
    }
}