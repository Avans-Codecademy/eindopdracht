package com.example.eindopdracht.controllers;

import com.example.eindopdracht.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    protected Stage stage;
    protected Scene scene;
    protected Parent root;

    public void switchToStudents(ActionEvent event) throws IOException {
        switchScene(event, "students-view.fxml");
    }

    public void switchToCourses(ActionEvent event) throws IOException {
        switchScene(event, "courses-view.fxml");
    }

    public void switchToEntries(ActionEvent event) throws IOException {
        switchScene(event, "entries-view.fxml");
    }

    public void switchToCertifications(ActionEvent event) throws IOException {
        switchScene(event, "certifications-view.fxml");
    }

    public void switchToOverviews(ActionEvent event) throws IOException {
        switchScene(event, "overviews-view.fxml");
    }

    private void switchScene(ActionEvent event, String fxmlPath) throws IOException {
        root = FXMLLoader.load(MainApplication.class.getResource(fxmlPath));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
