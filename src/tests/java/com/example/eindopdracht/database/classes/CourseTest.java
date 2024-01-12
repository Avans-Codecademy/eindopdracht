package com.example.eindopdracht.database.classes;

import com.example.eindopdracht.database.classes.Enums.Level;
import com.example.eindopdracht.database.classes.Enums.Status;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class CourseTest {
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
    void addModuleWithTwoModules(){
        //Arrange
        Course test = new Course("testcourse", "Java", "I like coding", Level.BEGINNER);
        //Act
        for (int i = 0; i < 2; i++){
            test.addModule(new Module(1, LocalDate.now(), Status.ACTIVE, "Python4Life", "Python Arraylists", "Joep.Snels@kpnmail.nl", "Joep", 1.4, 1));
        }
        //Assert
        assertEquals(2, test.getModules().size());
    }
    @Test
    void addModuleWith100Modules(){
        Course test = new Course("testcourse", "Java", "I like coding", Level.BEGINNER);
        //Act
        for (int i = 0; i < 100; i++){
            test.addModule(new Module(1, LocalDate.now(), Status.ACTIVE, "Python4Life", "Python Arraylists", "Joep.Snels@kpnmail.nl", "Joep", 1.4, 1));
        }
        //Assert
        assertEquals(100, test.getModules().size());
    }
    @Test
    void addIntrestingCoursesWith2Courses(){
        Course test = new Course("testcourse", "Java", "I like coding", Level.BEGINNER);
        //Act
        for (int i = 0; i < 2; i++){
            test.addInterestingCourses(new Course("testcourse", "Java", "I like coding", Level.BEGINNER));
        }
        //Assert
        assertEquals(2, test.getInterestingCourses().size());
    }
    @Test
    void addIntrestingCoursesWith100Courses(){
        Course test = new Course("testcourse", "Java", "I like coding", Level.BEGINNER);
        //Act
        for (int i = 0; i < 100; i++){
            test.addInterestingCourses(new Course("testcourse", "Java", "I like coding", Level.BEGINNER));
        }
        //Assert
        assertEquals(100, test.getInterestingCourses().size());
    }
}