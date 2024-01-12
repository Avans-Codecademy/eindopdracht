package com.example.eindopdracht.database.classes;

import java.time.LocalDate;
import java.util.ArrayList;

import com.example.eindopdracht.database.classes.Enums.Status;

public class Module extends ContentItem{
    private String emailContactPerson;
    private String nameContactPerson;
    private String version;
    private int orderNummer;
    private String course;
    private int percentageDone;
    public static ArrayList<Module> allModules = new ArrayList<>();

    //for interface progress, returns progress
    public int progress() {
        return percentageDone;
    } 

    //method to validate emailformat
    public void name() {
        
    }

    //method to increase percentage done by amount
    public void increasePercentageDone(int add) {
        percentageDone = percentageDone + add;
    }


    //constructor of module, also adds it ot every module list
    public Module(String emailContactPerson, String nameContactPerson, String version, Status status, LocalDate publishDate,int orderNummer, String course) {
        this.emailContactPerson = emailContactPerson;
        this.nameContactPerson = nameContactPerson;
        this.version = version;
        this.status = status;
        this.publishDate = publishDate;
        this.orderNummer = orderNummer;
        this.course = course;
    }

    //adds module to all modules, HAS to be done when making a module, otherwise some thing wont work
    public void addModuleToAll(Module module) {
        allModules.add(module);
    }

    //returns all modules
    static ArrayList<Module> getAllModules() {
        return allModules;
    }


    //getters and setters
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEmailContactPerson() {
        return emailContactPerson;
    }

    public int getPercentageDone() {
        return percentageDone;
    }
    //end getter and setters


    //tostring, need to update
    @Override 
    public String toString() {
        return "ContentItemid: " + contentItemid + ", publishdate:" + publishDate + ", status:" + status + ", title:" + title;
    }    
}
