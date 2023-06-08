package com.retroBoard.retroBoard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Retro {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private Date createdAt;
    private int creatorId;
    private Date modifiedDate;
//    private Comment topic;

    public Retro(String name, int creatorId) {
        this.name = name;
        this.createdAt = new Date();
        this.creatorId = creatorId;
        this.modifiedDate = createdAt;
    }

    public Retro() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

}
