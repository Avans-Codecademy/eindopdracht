package com.example.eindopdracht.database.classes;

import java.time.LocalDate;
import java.time.Month;
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
    private String postalcode;
    private String housenumber;
    private ArrayList<Course> courses;

    public Student(String email, String name, int year, Month string, int day, Gender gender, String address, String city,
            String country, String postalcode, String housenumber, ArrayList<Course> courses) {
        LocalDate put = LocalDate.of(year, string, day);
        this.email = email;
        this.name = name;
        this.birthdate = put;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postalcode = postalcode;
        this.housenumber = housenumber;
        this.courses = new ArrayList<Course>();
    }

    //method to check if postalcode format is correct
    public Boolean checkPostal(String postalcode) throws Exception {
        //checks length, dutch postal is 7 with the space
        if (postalcode.length() == 7) {
            String test = postalcode.substring(0, 3);
            try {
                Double.parseDouble(test);
            } catch (NumberFormatException nfe) {
                return false;
            }
            //check if the space is in the correct position and if its a space
            if (postalcode.substring(4,4) == " ") {
                //check if the last 2 chars are letters
                String test2 = postalcode.substring(5,6);
                //check if the last 2 letters are uppercase
                if(Character.isLetter(test2.charAt(0)) && Character.isLetter(test2.charAt(1))){
                    char hold1 = test2.charAt(0);
                    char hold2 = test2.charAt(1);
                    char holdtest1 = Character.toUpperCase(hold1);
                    char holdtest2 = Character.toUpperCase(hold2);
                    if (hold1 == holdtest1 && hold2 == holdtest2) {
                        return true;
                    }
                    else{
                        return false;
                    }
                };
            }
        }
        return null;
    } 
    

    public void enroll(Course course) {
        courses.add(course);
    }

    @Override
    public String toString() {
        return "Student: email" + email + ", name:" + name + ", birthdate:" + birthdate + ", gender:" + gender
                + ", address:" + address + ", city:" + city + ", country:" + country + ", Cursusen:" + courses;
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

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

}
