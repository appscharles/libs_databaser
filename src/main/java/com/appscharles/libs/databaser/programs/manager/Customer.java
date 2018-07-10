package com.appscharles.libs.databaser.programs.manager;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 09.07.2018
 * Time: 08:37
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
@Entity
@Table(name="customer")
public class Customer extends Model {

    @Id
    private Long id;

    private String name;

    private Date registered;

    private String comments;

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter for property 'name'.
     *
     * @return Value for property 'name'.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for property 'name'.
     *
     * @param name Value to set for property 'name'.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for property 'registered'.
     *
     * @return Value for property 'registered'.
     */
    public Date getRegistered() {
        return registered;
    }

    /**
     * Setter for property 'registered'.
     *
     * @param registered Value to set for property 'registered'.
     */
    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    /**
     * Getter for property 'comments'.
     *
     * @return Value for property 'comments'.
     */
    public String getComments() {
        return comments;
    }

    /**
     * Setter for property 'comments'.
     *
     * @param comments Value to set for property 'comments'.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
}
