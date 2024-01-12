package com.example.eindopdracht.database.classes;

import java.time.LocalDate;

import com.example.eindopdracht.database.classes.Enums.Status;

public class Module extends ContentItem{
    private String emailContactPerson;
    private String nameContactPerson;
    private String version;
    private int orderNummer;
    private String course;
    private int percentageDone;
    public int progress() {
        return percentageDone;
    } 

    //method to increase percentage done by amount
    public void increasePercentageDone(int add) {
        percentageDone = percentageDone + add;
    }

    public Module(String emailContactPerson, String nameContactPerson, String version, Status status, LocalDate publishDate,int orderNummer, String course) {
        if (emailContactPerson.contains("@") && emailContactPerson.contains(".")) {
        this.emailContactPerson = emailContactPerson;
        this.nameContactPerson = nameContactPerson;
        this.version = version;
        this.status = status;
        this.publishDate = publishDate;
        this.orderNummer = orderNummer;
        this.course = course;}
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

    @Override 
    public String toString() {
        return "ContentItemid: " + contentItemid + ", publishdate:" + publishDate + ", status:" + status + ", title:" + title;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }



    public String getEmailContactPerson() {
        return emailContactPerson;
    }



    
}
