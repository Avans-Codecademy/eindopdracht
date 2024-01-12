package com.example.eindopdracht.database.classes;

import java.time.LocalDate;

import com.example.eindopdracht.database.classes.Enums.Status;

public class Module extends ContentItem{
    private String emailContactPerson;
    private String nameContactPerson;
    private String version;
    private int orderNummer;

    public Module(int contentItemid, LocalDate publishDate, Status status, String title, String description, String emailContactPerson, String nameContactPerson, String version, int orderNummer) {
        this.contentItemid = contentItemid;
        this.publishDate = publishDate;
        this.status = status;
        this.title = title;
        this.description = description;
        this.emailContactPerson = emailContactPerson;
        this.nameContactPerson = nameContactPerson;
        this.version = version;
        this.orderNummer = orderNummer;
    }

    public String getEmailContactPerson() {
        return emailContactPerson;
    }

    public void setEmailContactPerson(String emailContactPerson) {
        this.emailContactPerson = emailContactPerson;
    }

    public String getNameContactPerson() {
        return nameContactPerson;
    }

    public void setNameContactPerson(String nameContactPerson) {
        this.nameContactPerson = nameContactPerson;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getOrderNummer() {
        return orderNummer;
    }

    public void setOrderNummer(int orderNummer) {
        this.orderNummer = orderNummer;
    }

    
}
