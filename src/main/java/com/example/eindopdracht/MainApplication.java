package com.example.eindopdracht;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Launch from fxml
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("overviews-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Codecademy | Jacob Timmerman (2218563) - Jacky Chen (2204610) - Joep Snels (2215029) - Renze Westerink (2217105)");
        stage.setScene(scene);
        stage.show();
    }
}
