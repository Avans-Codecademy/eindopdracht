package com.example.eindopdracht;

import com.example.eindopdracht.database.classes.Course;
import com.example.eindopdracht.database.classes.Student;
import com.example.eindopdracht.database.classes.Enums.Gender;
import com.example.eindopdracht.database.classes.Enums.Level;

public class Main {
    public static void main(String[] args) {
        Course test = new Course("testcourse", "Java", "This is just a test", Level.BEGINNER, null, null);
        Student jan = new Student("Jan@gmail.com", "Jan", null, Gender.MALE, "3281 BM", "Rotterdam", "Netherlands", null);
        jan.enroll(test);
        System.out.println(jan.toString());
        System.out.println(test.toString());
    }
}
