package com.example.eindopdracht;

import com.example.eindopdracht.database.classes.Cursus;
import com.example.eindopdracht.database.classes.Student;
import com.example.eindopdracht.gui.mainGui;

import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        Cursus test = new Cursus("testcourse", "Java");
        Student jan = new Student("100000StudentId", "Jan@gmail.com", "Jan", null, "Male", "3281 BM", "Rotterdam",
                "Netherlands", null);
        jan.enroll(test);
        System.out.println(jan.toString());
        System.out.println(test.toString());

        Application.launch(mainGui.class);
    }
}
