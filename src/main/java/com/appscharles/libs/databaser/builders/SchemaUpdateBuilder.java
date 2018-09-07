package com.appscharles.libs.databaser.builders;

import org.hibernate.tool.hbm2ddl.SchemaUpdate;

import java.io.File;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 07.09.2018
 * Time: 14:38
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class SchemaUpdateBuilder {

    private File outputFile;

    private SchemaUpdateBuilder(){

    }

    public static SchemaUpdateBuilder create(File outputFile){
        SchemaUpdateBuilder instance = new SchemaUpdateBuilder();
        instance.outputFile = outputFile;
        return instance;
    }

    public SchemaUpdate build(){
        SchemaUpdate schemaUpdate = new SchemaUpdate ();
        schemaUpdate.setDelimiter(";");
        schemaUpdate.setOutputFile(this.outputFile.getAbsolutePath());
        schemaUpdate.setFormat(true);
     return schemaUpdate;
    }
}
