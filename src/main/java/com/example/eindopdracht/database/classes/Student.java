package com.example.eindopdracht.database.classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Student {
    private String studentId;
    private String email;
    private String name;
    private LocalDate birthdate;
    private String gender;
    private String address;
    private String city;
    private String country;
    private ArrayList<Cursus> cursusen;

    public Student(String studentID, String email, String name, LocalDate birthdate, String gender, String address,
            String city,
            String country, ArrayList<Cursus> cursusen) {
        this.studentId = studentID; // dit moet geautomatiseerd worden
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.country = country;
        this.cursusen = new ArrayList<Cursus>();
    }

    public void enroll(Cursus name) {
        cursusen.add(name);
    }

    @Override
    public String toString() {
        return "Student: studentID: " + studentId + ", email:" + email + ", name:" + name + ", birthdate:" + birthdate
                + ", gender:" + gender
                + ", address:" + address + ", city:" + city + ", country:" + country + ", Cursusen:" + cursusen;
    }

    public String studentId() {
        return studentId;
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

    public ArrayList<Cursus> getCursusen() {
        return cursusen;
    }

}
