package com.example.eindopdracht.database.classes;

import java.time.LocalDate;

import com.example.eindopdracht.database.classes.Enums.Status;

public class Webcast extends ContentItem{
    private int duration;
    private String url;
    private String speakerName;
    private String speakerOrganisation;

    public Webcast(int contentItemid, LocalDate publishDate, Status status, String title, String description,int duration, String url, String speakerName, String speakerOrganisation) {
        this.contentItemid = contentItemid;
        this.publishDate = publishDate;
        this.status = status;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.url = url;
        this.speakerName = speakerName;
        this.speakerOrganisation = speakerOrganisation;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public String getSpeakerOrganisation() {
        return speakerOrganisation;
    }

    public void setSpeakerOrganisation(String speakerOrganisation) {
        this.speakerOrganisation = speakerOrganisation;
    }

    
}
