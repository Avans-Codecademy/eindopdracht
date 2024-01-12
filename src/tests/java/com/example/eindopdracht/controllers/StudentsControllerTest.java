package com.example.eindopdracht.controllers;

import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StudentsControllerTest {
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
    void isEmailValidIsNotValid() {
        //Arrange
        String email = "Joep.Snels@.kpnmail.nl";
        StudentsController studentsController = new StudentsController();


        //Act
        studentsController.isEmailValid(email);

        //Assert
        assertFalse(studentsController.isEmailValid(email));

    }
    @Test
    void isEmailValidValid(){
        //Arrange
        String email = "Joep.Snels@kpnmail.nl";
        StudentsController studentsController = new StudentsController();


        //Act
        studentsController.isEmailValid(email);

        //Assert
        assertTrue(studentsController.isEmailValid(email));

    }
    @Test
    void isEmailValidEmpty(){
        //Arrange
        String email = "";
        StudentsController studentsController = new StudentsController();


        //Act
        studentsController.isEmailValid(email);

        //Assert
        assertFalse(studentsController.isEmailValid(email));

    }
    @Test
    void isEmailValidNull(){
        //Arrange
        StudentsController studentsController = new StudentsController();


        //Act
        studentsController.isEmailValid(null);

        //Assert
        assertFalse(studentsController.isEmailValid(null));

    }
    @Test
    void isEmailValidNoDomain(){
        //Arrange
        String email = "Joep.Snels@nl";
        StudentsController studentsController = new StudentsController();


        //Act
        studentsController.isEmailValid(email);

        //Assert
        assertFalse(studentsController.isEmailValid(email));

    }
    @Test
    void IsEmailValidNoAtSybol(){
        //Arrange
        String email = "Joep.Snelskpnmail.nl";
        StudentsController studentsController = new StudentsController();


        //Act
        studentsController.isEmailValid(email);

        //Assert
        assertFalse(studentsController.isEmailValid(email));

    }
    @Test
    void isPostcodeValidWithoutSpace(){
        //Arrange
        String postcode = "6758RZ";
        StudentsController studentsController = new StudentsController();


        //Act
        studentsController.isValidPostcode(postcode);

        //Assert
        assertFalse(studentsController.isValidPostcode(postcode));
    }
    @Test
    void isPostcodeValidWithASpaceInTheNumbers(){
        //Arrange
        String postcode = "67 58RZ";
        StudentsController studentsController = new StudentsController();


        //Act
        studentsController.isValidPostcode(postcode);

        //Assert
        assertFalse(studentsController.isValidPostcode(postcode));
    }
    @Test
    void isPostcodeValidNoUppercase(){
        String postcode = "6758 rz";
        StudentsController studentsController = new StudentsController();


        //Act
        studentsController.isValidPostcode(postcode);

        //Assert
        assertFalse(studentsController.isValidPostcode(postcode));
    }
    @Test
    void isPostcodeValidValid(){
        //Arrange
        String postcode = "6758 RZ";
        StudentsController studentsController = new StudentsController();


        //Act
        studentsController.isValidPostcode(postcode);

        //Assert
        assertTrue(studentsController.isValidPostcode(postcode));
    }
    @Test
    void isPostcodeValidNull(){

        StudentsController studentsController = new StudentsController();


        //Act
        studentsController.isValidPostcode(null);

        //Assert
        assertFalse(studentsController.isValidPostcode(null));
    }
    @Test
    void isBirthdayValidNull(){
        //Arange
        StudentsController studentsController = new StudentsController();


        //Act
        studentsController.isBirthdayValid(null);

        //Assert
        assertFalse(studentsController.isBirthdayValid(null));
    }
    @Test
    void isBirthdayValidBefore1900(){
        //Arrange
        LocalDate birthday = LocalDate.of(1899,12,31);
        StudentsController studentsController = new StudentsController();


        //Act
        studentsController.isBirthdayValid(birthday);

        //Assert
        assertFalse(studentsController.isBirthdayValid(birthday));
    }
    @Test
    void isBirthdayValidAfterToday(){
        //Arrange
        LocalDate birthday = LocalDate.of(3000,12,31);
        StudentsController studentsController = new StudentsController();


        //Act
        studentsController.isBirthdayValid(birthday);

        //Assert
        assertFalse(studentsController.isBirthdayValid(birthday));
    }
    @Test
    void isBirthdayValidValid(){
        //Arrange
        LocalDate birthday = LocalDate.of(2006,9,13);
        StudentsController studentsController = new StudentsController();


        //Act
        studentsController.isBirthdayValid(birthday);

        //Assert
        assertTrue(studentsController.isBirthdayValid(birthday));
    }


}