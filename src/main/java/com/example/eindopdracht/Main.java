package com.example.eindopdracht;

import com.example.eindopdracht.database.classes.Course;
import com.example.eindopdracht.database.classes.Module;
import com.example.eindopdracht.database.classes.Student;
import com.example.eindopdracht.database.classes.Enums.Gender;
import com.example.eindopdracht.database.classes.Enums.Level;

public class Main {
    public static void main(String[] args) {
        Module t = new Module("w@gmail.com", null, null, null, null, 0, null);
        Course test = new Course("testcourse", "Java", "This is just a test", Level.BEGINNER, null, null);
        Student jan = new Student("Jan@gmail.com", "Jan", 0, null, 0, Gender.MALE, "3281 BM", "Rotterdam", "Netherlands", "3281SH", "9b", null);
        jan.enroll(test);
        System.out.println(jan.toString());
        System.out.println(test.toString());
        System.out.println(t.toString());
    }
}
