package com.appscharles.libs.databaser.generators;

import com.appscharles.libs.databaser.exceptions.DatabaserException;
import com.appscharles.libs.ioer.services.DirDeleter;
import io.ebean.annotation.Platform;
import io.ebean.dbmigration.DbMigration;

import java.io.File;
import java.io.IOException;

/**
 * The type Migration generator.
 */
public class MigrationGenerator implements IMigrationGenerate {

    private Platform platform;

    private DbMigration dBMigration;

    private File toDir;

    public MigrationGenerator(Platform platform) {
        this(platform, null);
    }

    /**
     * Instantiates a new Migration generator.
     *
     * @param platform the platform
     */
    public MigrationGenerator(Platform platform, File toDir) {
        this.platform = platform;
        this.dBMigration = DbMigration.create();
        this.dBMigration.setPlatform(this.platform);
        this.toDir = toDir;

    }

    @Override
    public void generate() throws DatabaserException {
       try {
           if (this.toDir != null){
               this.dBMigration.setPathToResources(this.toDir.getAbsolutePath());
           }
           this.dBMigration.generateMigration();
           if (this.toDir != null){
               DirDeleter.delete(new File(this.toDir.getAbsolutePath(), "dbmigration/model"));
           }

       } catch (IOException e) {
           throw new DatabaserException(e);
       }
    }

    /**
     * Gets b migration.
     *
     * @return the b migration
     */
    public DbMigration getdBMigration() {
        return dBMigration;
    }
}
