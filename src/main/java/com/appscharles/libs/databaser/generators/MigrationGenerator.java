package com.appscharles.libs.databaser.generators;

import com.appscharles.libs.databaser.configurators.FlyWayDBMigratorConfigurator;
import com.appscharles.libs.databaser.exceptions.DatabaserException;
import io.ebean.annotation.Platform;
import io.ebean.dbmigration.DbMigration;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Migration generator.
 */
public class MigrationGenerator implements IMigrationGenerate {


    String version;

    private Platform platform;

    private DbMigration dBMigration;

    private File toDir;

    /**
     * Instantiates a new Migration generator.
     *
     * @param platform the platform
     */
    public MigrationGenerator(String version, Platform platform, File toDir) {
        this.version = version;
        this.platform = platform;
        System.setProperty("ddl.migration.name", version + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
        this.dBMigration = DbMigration.create();
        this.dBMigration.setServerConfig(new FlyWayDBMigratorConfigurator().config());
        this.dBMigration.setPlatform(this.platform);
        this.toDir = toDir;

    }

    @Override
    public void generate() throws DatabaserException {
        try {
            if (this.toDir != null) {
                this.dBMigration.setPathToResources(this.toDir.getAbsolutePath());
            }
            this.dBMigration.generateMigration();
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
