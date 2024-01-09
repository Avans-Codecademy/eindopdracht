package com.example.eindopdracht;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    public static void main(String[] args) {
//        Cursus test = new Cursus("testcourse", "Java");
//        Student jan = new Student("Jan@gmail.com", "Jan", null, "Male", "3281 BM", "Rotterdam", "Netherlands", null);
//        jan.enroll(test);
//        System.out.println(jan.toString());
//        System.out.println(test.toString());
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Launch from fxml
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
