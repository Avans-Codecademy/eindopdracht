package com.example.eindopdracht;

public class Main {
    public static void main(String args[]) {
        Cursus test = new Cursus("testcourse", "Java");
        Student jan = new Student("Jan@gmail.com", "Jan", null, "Male", "3281 BM", "Rotterdam", "Netherlands", null);
        jan.enroll(test);
        System.out.println(jan.toString());
        System.out.println(test.toString());
    }
}
