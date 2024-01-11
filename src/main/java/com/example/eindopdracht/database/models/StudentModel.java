package com.example.eindopdracht.database.models;

import java.util.Date;

public class StudentModel {
    Integer studentId;
    String studentEmail;
    String name;
    Date birthday;
    String gender;
    String address;
    String city;
    String country;
    String postcode;
    Integer houseNumber;

    public StudentModel(Integer studentId, String studentEmail, String name, Date birthday, String gender, String address, String city, String country, String postcode, Integer houseNumber) {
        this.studentId = studentId;
        this.studentEmail = studentEmail;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postcode = postcode;
        this.houseNumber = houseNumber;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }
}
