package com.example.eindopdracht.database.classes;

import java.time.LocalDate;

import com.example.eindopdracht.database.classes.Enums.Status;

public class Module extends ContentItem{
    private String emailContactPerson;
    private String nameContactPerson;
    private double version;
    private int orderNumber;

    public Module(int contentItemid, LocalDate publishDate, Status status, String title, String description, String emailContactPerson, String nameContactPerson, double version, int orderNummer) {
        this.contentItemid = contentItemid;
        this.publishDate = publishDate;
        this.status = status;
        this.title = title;
        this.description = description;
        this.emailContactPerson = emailContactPerson;
        this.nameContactPerson = nameContactPerson;
        this.version = version;
        this.orderNumber = orderNummer;
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

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    
}
