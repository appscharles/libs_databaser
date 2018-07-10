package com.appscharles.libs.databaser.programs.manager;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.jarer.exceptions.JarerException;
import com.appscharles.libs.processer.exceptions.ProcesserException;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 09.07.2018
 * Time: 08:38
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class CustomerTest extends TestCase {

    @Test
    public void shouldSaveUserToDatabase() throws IOException, DatabaserException {
//        IServer server = createDefaultEbean(2345, this.temp.newFolder("shouldSaveUserToDatabase"));
//        Customer customer = new Customer();
//        customer.setName("Charles");
//        customer.save();
//        server.stop();
    }

    @Test
    public void shouldExceptionNotExistTableCustomer() throws IOException, JarerException, ProcesserException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        File jarFile = new File(this.temp.newFolder(), "file.jar");
//        Manifest manifest = ManifestBuilder.create("myApp", "1.0.0.0-dev2", ManagerProgram.class).build();
//        JarCreator jarCreator = new JarCreator(jarFile, manifest, ManagerProgram.class.getProtectionDomain().getCodeSource().getLocation());
//        jarCreator.addPackage("org.h2", new URL("jar:" + Driver.class.getProtectionDomain().getCodeSource().getLocation().toString()+ "!/org.h2"));
//
//        jarCreator.addPackage("com.appscharles.libs.databaser");
//        jarCreator.addPackage("com.appscharles.libs.logger");
//        jarCreator.addPackage("META-INF.services", "io.ebean", "ebean", "11.18.4");
//
//        jarCreator.addPackage("io", "io.ebean", "ebean", "11.18.4");
//        jarCreator.addPackage("io.ebean.annotation", "io.ebean", "ebean-annotation", "4.1");
//        jarCreator.addPackage("io.ebean.migration", "io.ebean", "ebean-migration", "11.7.1");
//        jarCreator.addPackage("javax.persistence", "io.ebean", "persistence-api", "2.2.1");
//        jarCreator.addPackage("org.avaje.classpath.scanner", "org.avaje", "avaje-classpath-scanner", "3.1.1");
//        jarCreator.addPackage("META-INF.services", "org.avaje", "avaje-classpath-scanner", "3.1.1");
//
//        jarCreator.addPackage("org.avaje.classpath.scanner", "org.avaje", "avaje-classpath-scanner-api", "2.2");
//
//        jarCreator.addPackage("org.avaje.datasource", "org.avaje", "avaje-datasource", "3.2.2");
//        jarCreator.addPackage("META-INF.services", "org.avaje", "avaje-datasource", "3.2.2");
//        jarCreator.addPackage("org.antlr.v4.runtime", "org.antlr", "antlr4-runtime", "4.7.1");
//
//        jarCreator.addPackage("org.avaje.datasource", "org.avaje", "avaje-datasource-api", "3.2");
//        jarCreator.addPackage("org.yaml.snakeyaml", "org.yaml", "snakeyaml", "1.21");
//        jarCreator.addPackage("org.slf4j", "org.slf4j", "slf4j-api", "1.8.0-beta2");
//
//        jarCreator.addPackage("org.apache.log4j", "log4j", "log4j", "1.2.17");
//
//        jarCreator.create();
//        ICommanderCaller commanderCaller = new CommanderCaller();
//        CommanderResult result = commanderCaller.call("java -jar " + jarFile.getPath());
//        Assert.assertTrue("FROM: " + result.getOutput(), result.isError());
//        Assert.assertTrue(result.getOutput(), result.getOutput().contains("[42102-"));
    }

//    @Test
//    public void shouldFindPackageH2() throws JarerException, IOException, ClassNotFoundException {
//        Class.forName("org.h2.Driver");
//       // Driver.load();
//        //Driver.class.get
//       // ClassLoader loader = Driver.class.getClass().getClassLoader();
////        Reflections reflections = new Reflections("my.package");
////        Set<Class<? extends Object>> classes = reflections.getSubTypesOf(Object.class);
//        URL loader = ;
//        System.out.println(loader.toString());
//        //loader.loadClass("org.h2.Driver");
//        List<URL> urls = Collections.list(getClass().getClassLoader().getResources("org.h2"));
//        Assert.assertTrue(urls.toString(), urls.size() > 0);
//    }

}