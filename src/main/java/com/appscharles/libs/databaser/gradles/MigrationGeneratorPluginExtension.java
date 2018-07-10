package com.appscharles.libs.databaser.gradles;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 06.07.2018
 * Time: 20:08
 * Project name: updater
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class MigrationGeneratorPluginExtension {

    private String toDir = "";

    private Boolean dependsOnClean = true;

    private String platform = "H2";

    /**
     * Getter for property 'toDir'.
     *
     * @return Value for property 'toDir'.
     */
    public String getToDir() {
        return toDir;
    }

    /**
     * Setter for property 'toDir'.
     *
     * @param toDir Value to set for property 'toDir'.
     */
    public void setToDir(String toDir) {
        this.toDir = toDir;
    }

    /**
     * Getter for property 'dependsOnClean'.
     *
     * @return Value for property 'dependsOnClean'.
     */
    public Boolean isDependsOnClean() {
        return dependsOnClean;
    }

    /**
     * Setter for property 'dependsOnClean'.
     *
     * @param dependsOnClean Value to set for property 'dependsOnClean'.
     */
    public void setDependsOnClean(Boolean dependsOnClean) {
        this.dependsOnClean = dependsOnClean;
    }

    /**
     * Getter for property 'platform'.
     *
     * @return Value for property 'platform'.
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * Setter for property 'platform'.
     *
     * @param platform Value to set for property 'platform'.
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
