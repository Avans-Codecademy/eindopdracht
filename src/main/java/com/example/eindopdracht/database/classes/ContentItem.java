package com.example.eindopdracht.database.classes;

import java.time.LocalDate;

import com.example.eindopdracht.database.classes.Enums.Status;

public abstract class  ContentItem {
    protected int contentItemid;
    protected LocalDate publishDate;
    protected Status status;
    protected String title;
    protected String description;

    public int getContentItemid() {
        return contentItemid;
    }
    public void setContentItemid(int contentItemid) {
        this.contentItemid = contentItemid;
    }
    public LocalDate getPublishDate() {
        return publishDate;
    }
    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    
}