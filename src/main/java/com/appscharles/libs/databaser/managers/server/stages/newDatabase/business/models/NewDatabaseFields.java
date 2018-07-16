package com.appscharles.libs.databaser.managers.server.stages.newDatabase.business.models;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 16.07.2018
 * Time: 16:55
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class NewDatabaseFields {

    private String dBName;

    private String user;

    private String password;

    public NewDatabaseFields(String dBName, String user, String password) {
        this.dBName = dBName;
        this.user = user;
        this.password = password;
    }

    /**
     * Getter for property 'dBName'.
     *
     * @return Value for property 'dBName'.
     */
    public String getdBName() {
        return dBName;
    }

    /**
     * Setter for property 'dBName'.
     *
     * @param dBName Value to set for property 'dBName'.
     */
    public void setdBName(String dBName) {
        this.dBName = dBName;
    }

    /**
     * Getter for property 'user'.
     *
     * @return Value for property 'user'.
     */
    public String getUser() {
        return user;
    }

    /**
     * Setter for property 'user'.
     *
     * @param user Value to set for property 'user'.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Getter for property 'password'.
     *
     * @return Value for property 'password'.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for property 'password'.
     *
     * @param password Value to set for property 'password'.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
