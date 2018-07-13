package com.appscharles.libs.databaser.programs.tester;

import com.appscharles.libs.databaser.TestCase;
import com.appscharles.libs.dialoger.factories.ExceptionDialogFactory;
import com.appscharles.libs.fxer.stages.FXStage;
import com.appscharles.libs.ioer.services.DirReader;
import com.appscharles.libs.jarer.builders.JarCreatorBuilder;
import com.appscharles.libs.jarer.creators.IJarCreator;
import com.appscharles.libs.jarer.creators.JarCreator;
import com.appscharles.libs.jarer.exceptions.JarerException;
import com.appscharles.libs.logger.configurators.Log4j2Console;
import com.appscharles.libs.processer.callers.CommanderCaller;
import com.appscharles.libs.processer.callers.CommanderResult;
import com.appscharles.libs.processer.callers.ICommanderCaller;
import com.appscharles.libs.processer.exceptions.ProcesserException;
import com.appscharles.libs.processer.managers.WinKillManager;
import com.google.common.reflect.AbstractInvocationHandler;
import com.google.common.xml.XmlEscapers;
import com.google.devtools.common.options.Converter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Core;
import org.flywaydb.core.Flyway;
import org.h2.Driver;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

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
    public void shouldRunProgramJar() throws ProcesserException, IOException, JarerException, ClassNotFoundException {
        new WinKillManager().killCommandLineContains("serverDirTesterProgram");
        File fileJar = this.temp.newFile("testerProgram.jar");
        IJarCreator jarCreator = JarCreatorBuilder.create(Tester.NAME, Tester.VERSION, Tester.class, fileJar).build();
        jarCreator.addDependency("com/appscharles/libs/databaser", Tester.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("com/appscharles/libs/dialoger", ExceptionDialogFactory.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("com/appscharles/libs/processer", com.appscharles.libs.processer.callers.ICommanderCaller.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("com/appscharles/libs/ioer", DirReader.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("com/appscharles/libs/logger", Log4j2Console.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("com/appscharles/libs/fxer", FXStage.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("com/appscharles/libs/jarer", JarCreator.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("com/google/devtools/common/options", Converter.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("com/google/common", XmlEscapers.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("org/h2", Driver.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("org/hamcrest", CoreMatchers.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("org/flywaydb/core", Flyway.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("com/google", AbstractInvocationHandler.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("javax/annotation", javax.annotation.Detainted.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("org/apache/logging/log4j", Level.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("org/apache/logging/log4j/core", Core.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("org/apache/log4j", org.apache.log4j.Level.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("org/jboss/jandex", org.jboss.jandex.AnnotationInstance.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("javax/transaction", javax.transaction.HeuristicCommitException.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("org/jboss/logging", org.jboss.logging.BasicLogger.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("javassist", javassist.ClassMap.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("META-INF/services", org.hibernate.Session.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("org/hibernate", org.hibernate.Session.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("org/hibernate/annotations/common", org.hibernate.annotations.common.AssertionFailure.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("net/bytebuddy", net.bytebuddy.ClassFileVersion.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("javax/persistence", javax.persistence.AssociationOverride.class.getProtectionDomain().getCodeSource().getLocation());
      jarCreator.addDependency("org/dom4j", org.dom4j.Branch.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("antlr", antlr.ActionTransInfo.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.addDependency("com/fasterxml/classmate", com.fasterxml.classmate.TypeResolver.class.getProtectionDomain().getCodeSource().getLocation());
        jarCreator.create();
        System.out.println("Generated");
        ICommanderCaller caller = new CommanderCaller();
        CommanderResult result = caller.call("java -jar " + fileJar.getAbsolutePath());
        Assert.assertFalse(result.getOutput(), result.getOutput().contains("ERROR"));
        new WinKillManager().killCommandLineContains("serverDirTesterProgram");
    }

}