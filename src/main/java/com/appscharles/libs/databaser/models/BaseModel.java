package com.appscharles.libs.databaser.models;

import javax.persistence.*;
import java.util.Calendar;

/**
 * The type Base model.
 */
@MappedSuperclass
public abstract class BaseModel implements IBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar updatedAt;

    /**
     * On create.
     */
    @PrePersist
    protected void onCreate() {
        updatedAt = createdAt = Calendar.getInstance();
    }

    /**
     * On update.
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = Calendar.getInstance();
    }

    public Long getId() {
        return id;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public Calendar getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Calendar updatedAt) {
        this.updatedAt = updatedAt;
    }
}
