package com.example.eindopdracht.controllers;

import com.example.eindopdracht.database.ConnectionManager;
import com.example.eindopdracht.database.classes.Course;
import com.example.eindopdracht.database.classes.Enums.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class CoursesController implements Initializable {
    @FXML
    private TableView<Course> coursesTableView;

    @FXML
    private TableColumn<Course, String> subjectCol;
    @FXML
    private TableColumn<Course, String> courseNameCol;
    @FXML
    private TableColumn<Course, String> introductionTextCol;
    @FXML
    private TableColumn<Course, Level> courseLevelCol;

    @FXML
    private ComboBox<Level> courseLevel;
    @FXML
    private TextField courseName;
    @FXML
    private TextArea introductionText;
    @FXML
    private TextField subject;

    @FXML
    private Button createCourseBtn;
    @FXML
    private Button updateCourseBtn;

    @FXML
    private TextField searchField;

    private int selectedIndex;
    private String selectedName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set items for courseLevel ComboBox
        courseLevel.setItems(FXCollections.observableArrayList(Level.values()));

        loadTable();
    }

    public void loadTable() {
        ObservableList<Course> coursesList = FXCollections.observableArrayList();
        Connection connection = ConnectionManager.getConnection();

        // SQL query
        String query = "SELECT courseName, subject, introductionText, courseLevel FROM Course;";

        try {
            // Execute the SQL query
            Statement statement = connection.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            // Get all the data from the query output
            while (queryOutput.next()) {
                String queryCourseName = queryOutput.getString("courseName");
                String querySubject = queryOutput.getString("subject");
                String queryIntroductionText = queryOutput.getString("introductionText");
                Level queryCourseLevel = Level.valueOf(queryOutput.getString("courseLevel"));

                // Populate the observe list
                coursesList.add(new Course(queryCourseName, querySubject, queryIntroductionText, queryCourseLevel));
            }

            // Link the database columns with the actual table columns
            courseNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            subjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
            introductionTextCol.setCellValueFactory(new PropertyValueFactory<>("introductionText"));
            courseLevelCol.setCellValueFactory(new PropertyValueFactory<>("level"));

            // Create filtered list
            FilteredList<Course> filteredData = new FilteredList<>(coursesList, b -> true);

            // Search through the courses
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(courseModel -> {
                    // Don't search when value is empty
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    // Check if search keyword is in course name
                    if (courseModel.getName().toLowerCase().contains(searchKeyword)) {
                        return true;
                    } else if (courseModel.getSubject().toLowerCase().contains(searchKeyword)) {
                        return true;
                    } else if (courseModel.getIntroductionText().toLowerCase().contains(searchKeyword)) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });

            // Bind the sorted data with the table view
            SortedList<Course> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(coursesTableView.comparatorProperty());

            // Load the data into the table
            coursesTableView.setItems(sortedData);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while retrieving data from database!");
            alert.show();
        }

        // Add table on mouse click event
        coursesTableView.setRowFactory(tv -> {
            TableRow<Course> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    // Set selected ID
                    selectedIndex = coursesTableView.getSelectionModel().getSelectedIndex();
                    selectedName = coursesTableView.getItems().get(selectedIndex).getName();
                }
            });

            return myRow;
        });
    }

    @FXML
    void addCourse(ActionEvent event) {
        String courseName, subject, introductionText;
        Level courseLevel;

        // Get all the information from the form
        courseName = this.courseName.getText();
        subject = this.subject.getText();
        introductionText = this.introductionText.getText();
        courseLevel = this.courseLevel.getValue();

        // Create the course in the database
        Connection connection = ConnectionManager.getConnection();

        // SQL query
        String query = "INSERT INTO Course(courseName, subject, introductionText, courseLevel) VALUES(?, ?, ?, ?);";

        try {
            // Execute the SQL query
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, courseName);
            statement.setString(2, subject);
            statement.setString(3, introductionText);
            statement.setString(4, courseLevel.toString());

            statement.executeUpdate();

            // Send alert to the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Course successfully created!");

            alert.showAndWait();

            // Reset values of the inputs
            resetValues();

            // Reload table
            loadTable();

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while creating course in database!");
            alert.show();
        }
    }

    @FXML
    void editCourse(ActionEvent event) {
        if (selectedName == null) {
            return;
        }

        // Hide create button & show edit button
        createCourseBtn.setVisible(false);
        updateCourseBtn.setVisible(true);

        // Fill the fields with the selected Course
        courseName.setText(coursesTableView.getItems().get(selectedIndex).getName());
        subject.setText(coursesTableView.getItems().get(selectedIndex).getSubject());
        introductionText.setText(coursesTableView.getItems().get(selectedIndex).getIntroductionText());
        courseLevel.setValue(coursesTableView.getItems().get(selectedIndex).getLevel());
    }

    @FXML
    void updateCourse(ActionEvent event) {
        String courseName, subject, introductionText;
        Level courseLevel;

        // Get all the information from the form
        courseName = this.courseName.getText();
        subject = this.subject.getText();
        introductionText = this.introductionText.getText();
        courseLevel = this.courseLevel.getValue();

        // Create the course in the database
        Connection connection = ConnectionManager.getConnection();

        // SQL query
        String query = "UPDATE Course SET courseName=?, subject=?, introductionText=?, courseLevel=? WHERE courseName = ?;";

        try {
            // Execute the SQL query
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, courseName);
            statement.setString(2, subject);
            statement.setString(3, introductionText);
            statement.setString(4, courseLevel.toString());
            statement.setString(5, selectedName);

            statement.executeUpdate();

            // Send alert to the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Course successfully updated!");

            alert.showAndWait();

            // Reload table
            loadTable();

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while updating course in database!");
            alert.show();
        }

        // Hide update button & show create button
        updateCourseBtn.setVisible(false);
        createCourseBtn.setVisible(true);

        // Reset values of the inputs
        resetValues();
    }

    private void resetValues() {
        courseName.setText("");
        subject.setText("");
        introductionText.setText("");
        courseLevel.setValue(null);

        selectedName = null;
        selectedIndex = 0;
    }

    @FXML
    void deleteCourse(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure you want to delete this Course?");
        Optional<ButtonType> result = alert.showAndWait();

        // If user click "OK", delete the Course
        if (result.get() == ButtonType.OK) {
            Connection connection = ConnectionManager.getConnection();

            // SQL query
            String query = "DELETE FROM Course WHERE CourseName = ?;";

            try {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, selectedName);
                statement.executeUpdate();

                selectedName = null;
                selectedIndex = 0;

                // Send alert to the user
                Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                alert3.setHeaderText("Course successfully deleted!");

                alert3.showAndWait();

                // Reload table
                loadTable();
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setHeaderText("Error while deleting course in database!");
                alert2.show();
            }
        }
    }
}
