package com.appscharles.libs.databaser.managers.server.business.models;

import javafx.beans.property.*;

import java.io.File;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 16.07.2018
 * Time: 12:40
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class AvailableDatabaseItem {

    private final StringProperty databaseName;

    private final ObjectProperty<File> databaseFile;

    private final LongProperty size;

    public AvailableDatabaseItem(String databaseName, File databaseFile, Long size) {
        this.databaseName = new SimpleStringProperty(databaseName);
        this.databaseFile = new SimpleObjectProperty<>(databaseFile);
        this.size = new SimpleLongProperty(size);

    }

    /**
     * Getter for property 'databaseName'.
     *
     * @return Value for property 'databaseName'.
     */
    public String getDatabaseName() {
        return databaseName.get();
    }

    public StringProperty databaseNameProperty() {
        return databaseName;
    }

    /**
     * Setter for property 'databaseName'.
     *
     * @param databaseName Value to set for property 'databaseName'.
     */
    public void setDatabaseName(String databaseName) {
        this.databaseName.set(databaseName);
    }

    /**
     * Getter for property 'databaseFilename'.
     *
     * @return Value for property 'databaseFilename'.
     */
    public File getDatabaseFile() {
        return databaseFile.get();
    }

    public ObjectProperty<File> databaseFileProperty() {
        return databaseFile;
    }

    /**
     * Setter for property 'databaseFile'.
     *
     * @param databaseFile Value to set for property 'databaseFile'.
     */
    public void setDatabaseFile(File databaseFile) {
        this.databaseFile.set(databaseFile);
    }

    /**
     * Getter for property 'size'.
     *
     * @return Value for property 'size'.
     */
    public long getSize() {
        return size.get();
    }

    public LongProperty sizeProperty() {
        return size;
    }

    /**
     * Setter for property 'size'.
     *
     * @param size Value to set for property 'size'.
     */
    public void setSize(long size) {
        this.size.set(size);
    }
}
