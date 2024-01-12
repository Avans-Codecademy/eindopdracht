import com.example.eindopdracht.database.classes.Course;
import com.example.eindopdracht.database.classes.Enums.Level;
import com.example.eindopdracht.database.classes.Enums.Status;
import com.example.eindopdracht.database.classes.Module;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
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


  
}