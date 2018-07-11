package com.appscharles.libs.databaser.programs.tester;

import com.appscharles.libs.databaser.builders.ServerH2Builder;
import com.appscharles.libs.databaser.creators.DatabaseH2Creator;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.databaser.servers.IServer;
import com.appscharles.libs.logger.configurators.Log4jConsole;
import io.ebean.service.SpiContainerFactory;
import org.apache.log4j.Level;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.ServiceLoader;

/**
 * The type Tester program.
 */
public class TesterProgram {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws DatabaserException the databaser exception
     */
    public static void main(String[] args) throws DatabaserException {
        try {
            //Class codecClass = Class.forName("org.apache.lucene.codecs.Codec")

            ServiceLoader.load(SpiContainerFactory.class);
           new Log4jConsole(Level.INFO).config();
          Integer port = 18574;
            IServer server = ServerH2Builder.create(port, Files.createTempDirectory("customers_").toFile()).build();
            server.start();
            DatabaseH2Creator creator = new DatabaseH2Creator("tcp://localhost:"+port+"/myDB", "root", "secret");

            creator.create();

            Customer customer = new Customer();
            customer.setName("Charles");
            customer.save();
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Add path.
     *
     * @param url the url
     * @throws Exception the exception
     */
    public static void addPath(String url) throws Exception {
        ClassLoader prevCl = Thread.currentThread().getContextClassLoader();

        // Create class loader using given codebase
        // Use prevCl as parent to maintain current visibility
        ClassLoader urlCl = URLClassLoader.newInstance(new URL[]{new URL(url)}, prevCl);

        try {
            // Save class loader so that we can restore later
            Thread.currentThread().setContextClassLoader(urlCl);

            // Expect that environment properties are in
            // application resource file found at "url"
            Context ctx = new InitialContext();

            System.out.println(ctx.lookup("tutorial/report.txt"));

            // Close context when no longer needed
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            // Restore
            Thread.currentThread().setContextClassLoader(prevCl);
        }
    }
}
