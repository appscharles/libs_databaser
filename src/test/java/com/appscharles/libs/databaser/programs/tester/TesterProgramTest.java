package com.appscharles.libs.databaser.programs.tester;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.jarer.builders.JarCreatorBuilder;
import com.appscharles.libs.jarer.creators.IJarCreator;
import com.appscharles.libs.jarer.exceptions.JarerException;
import com.appscharles.libs.processer.callers.CommanderCaller;
import com.appscharles.libs.processer.callers.CommanderResult;
import com.appscharles.libs.processer.callers.ICommanderCaller;
import com.appscharles.libs.processer.exceptions.ProcesserException;
import com.appscharles.libs.processer.managers.WinKillManager;
import org.h2.Driver;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * The type Tester program test.
 */
public class TesterProgramTest extends TestCase {

    /**
     * Should run program jar.
     *
     * @throws ProcesserException the processer exception
     * @throws IOException        the io exception
     * @throws JarerException     the jarer exception
     */
    @Test
    public void shouldRunProgramJar() throws ProcesserException, IOException, JarerException {
        new WinKillManager().killCommandLineContains("serverDirTesterProgram");
        File fileJar = this.temp.newFile("testerProgram.jar");
        IJarCreator jarCreator = JarCreatorBuilder.create(Tester.NAME, Tester.VERSION, Tester.class, fileJar, Tester.class.getProtectionDomain().getCodeSource().getLocation()).build();
        jarCreator.addPackage("com.appscharles.libs.databaser");
        jarCreator.addPackage("com.appscharles.libs.dialoger");
        jarCreator.addPackage("com.appscharles.libs.processer");
        jarCreator.addPackage("com.appscharles.libs.ioer");
        jarCreator.addPackage("com.appscharles.libs.logger");
        jarCreator.addPackage("com.appscharles.libs.fxer");
        jarCreator.addPackage("com.appscharles.libs.jarer");
        jarCreator.addPackage("com.google.devtools.common.options");
        jarCreator.addPackage("com.google.common");
        jarCreator.addPackage("org.h2", new URL("jar:" + Driver.class.getProtectionDomain().getCodeSource().getLocation() + "!/org.h2"));
        jarCreator.addPackage("org.hamcrest", "org.hamcrest", "hamcrest-core", "1.3");
        jarCreator.addPackage("org.flywaydb.core", "org.flywaydb", "flyway-core", "5.1.4");
        jarCreator.addPackage("com.google", "com.google.guava", "guava", "19.0");
        jarCreator.addPackage("javax.annotation", "com.google.code.findbugs", "jsr305", "3.0.1");
        jarCreator.addPackage("org.apache.logging.log4j", "org.apache.logging.log4j", "log4j-api", "2.11.0");
        jarCreator.addPackage("org.apache.logging.log4j.core", "org.apache.logging.log4j", "log4j-core", "2.11.0");
        jarCreator.addPackage("org.apache.log4j", "log4j", "log4j", "1.2.17");
        jarCreator.addPackage("org.jboss.jandex", "org.jboss", "jandex", "2.0.5.Final");
        jarCreator.addPackage("javax.transaction", "org.jboss.spec.javax.transaction", "jboss-transaction-api_1.2_spec", "1.1.1.Final");
        jarCreator.addPackage("org.jboss.logging", "org.jboss.logging", "jboss-logging", "3.3.2.Final");
        jarCreator.addPackage("javassist", "org.javassist", "javassist", "3.22.0-GA");
        jarCreator.addPackage("META-INF.services", "org.hibernate", "hibernate-core", "5.3.2.Final");
        jarCreator.addPackage("org.hibernate", "org.hibernate", "hibernate-core", "5.3.2.Final");
        jarCreator.addPackage("org.hibernate.annotations.common", "org.hibernate.common", "hibernate-commons-annotations", "5.0.4.Final");
        jarCreator.addPackage("net.bytebuddy", "net.bytebuddy", "byte-buddy", "1.8.12");
        jarCreator.addPackage("javax.persistence", "javax.persistence", "javax.persistence-api", "2.2");
        jarCreator.addPackage("javax.activation", "javax.activation", "javax.activation-api", "1.2.0");
        jarCreator.addPackage("org.dom4j", "dom4j", "dom4j", "1.6.1");
        jarCreator.addPackage("antlr", "antlr", "antlr", "2.7.7");
        jarCreator.addPackage("com.fasterxml.classmate", "com.fasterxml", "classmate", "1.3.4");
        jarCreator.create();
        System.out.println("Generated");
        ICommanderCaller caller = new CommanderCaller();
        CommanderResult result = caller.call("java -jar " + fileJar.getAbsolutePath());
        Assert.assertFalse(result.getOutput(), result.getOutput().contains("ERROR"));
        new WinKillManager().killCommandLineContains("serverDirTesterProgram");
    }

}