package com.example.eindopdracht.database.classes;

import java.time.LocalDate;
import java.util.ArrayList;

import com.example.eindopdracht.database.classes.Enums.Gender;

public class Student {
    private String email;
    private String name;
    private LocalDate birthdate;
    private Gender gender;
    private String address;
    private String city;
    private String country;
    private ArrayList<Course> courses;

    public Student(String email, String name, LocalDate birthdate, Gender gender, String address, String city,
            String country, ArrayList<Course> courses) {
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.country = country;
        this.courses = new ArrayList<Course>();
    }

    public void enroll(Course name) {
        courses.add(name);
    }

    @Override
    public String toString() {
        return "Student: email" + email + ", name:" + name + ", birthdate:" + birthdate + ", gender:" + gender
                + ", address:" + address + ", city:" + city + ", country:" + country + ", Courses:" + courses;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
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
