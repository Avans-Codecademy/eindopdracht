package com.example.eindopdracht.database.classes;

import java.time.LocalDate;
import java.util.ArrayList;

import com.example.eindopdracht.database.classes.Enums.Gender;

public class Student {
    private int studentId;
    private String email;
    private String name;
    private LocalDate birthday;
    private Gender gender;
    private String address;
    private String city;
    private String country;
    private String postcode;
    private String houseNumber;
    private ArrayList<Course> courses;

    public Student(Integer studentId, String email, String name, LocalDate birthday, Gender gender, String address, String city, String country, String postcode, String houseNumber) {
        this.studentId = studentId;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postcode = postcode;
        this.houseNumber = houseNumber;
        this.courses = new ArrayList<Course>();
    }

    public void enroll(Course name) {
        courses.add(name);
    }

    @Override
    public String toString() {
        return "Student: email" + email + ", name:" + name + ", birthdate:" + birthday + ", gender:" + gender
                + ", address:" + address + ", city:" + city + ", country:" + country + ", Cursusen:" + courses;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
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

    public ArrayList<Course> getCursusen() {
        return courses;
    }

}
