package com.example.eindopdracht.database.classes;

import com.example.eindopdracht.database.classes.Enums.Gender;
import com.example.eindopdracht.database.classes.Enums.Level;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class StudentTest {
    @BeforeAll
    static void startingScript(){
        System.out.println("Loading tests");
    }
    @BeforeEach
    void startingNewTest(){
        System.out.println("Starting test");
    }
    @AfterEach
    void doneTesting(){
        System.out.println("Test completed");
    }
    @AfterAll
    static void allTestsDone(){
        System.out.println("Job is done");
    }
    @Test
    void enrollWith2Courses(){
        //Arrange
        Course test = new Course("testcourse", "Java", "hello people", Level.BEGINNER);
        Course anotherOne = new Course("testcourse", "Java", "hello people", Level.BEGINNER);
        Student jan = new Student(1, "Jan@gmail.com", "Jan", LocalDate.now(),  Gender.MALE, "Molenstraat", "Breda", "The Netherlands", "4706 KR", "361a");
        //Act
        jan.enroll(test);
        jan.enroll(anotherOne);
        //Assert
        assertEquals(2, jan.getCursusen().size());
    }
    @Test
    void enrollWith100Courses(){
        //Arrange
        Course test = new Course("testcourse", "Java", "hello people", Level.BEGINNER);
        Student jan = new Student(1, "Jan@gmail.com", "Jan", LocalDate.now(),  Gender.MALE, "Molenstraat", "Breda", "The Netherlands", "4706 KR", "361a");
        //Act
        for (int i = 0; i < 100; i++) {
            jan.enroll(test);
        }
        //Assert
        assertEquals(100, jan.getCursusen().size());
    }
}