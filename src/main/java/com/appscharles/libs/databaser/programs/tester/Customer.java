package com.appscharles.libs.databaser.programs.tester;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * The type Customer.
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
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets registered.
     *
     * @return the registered
     */
    public Date getRegistered() {
        return registered;
    }

    /**
     * Sets registered.
     *
     * @param registered the registered
     */
    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    /**
     * Gets comments.
     *
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets comments.
     *
     * @param comments the comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
}
