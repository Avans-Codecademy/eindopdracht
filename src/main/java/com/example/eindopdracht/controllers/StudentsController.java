package com.example.eindopdracht.controllers;

import com.example.eindopdracht.database.ConnectionManager;
import com.example.eindopdracht.database.models.StudentModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class StudentsController implements Initializable {

    @FXML
    private TableView<StudentModel> studentsTableView;
    @FXML
    private TableColumn<StudentModel, Integer> studentIdCol;
    @FXML
    private TableColumn<StudentModel, String> studentEmailCol;
    @FXML
    private TableColumn<StudentModel, String> nameCol;
    @FXML
    private TableColumn<StudentModel, String> birthdayCol;
    @FXML
    private TableColumn<StudentModel, String> genderCol;
    @FXML
    private TableColumn<StudentModel, String> addressCol;
    @FXML
    private TableColumn<StudentModel, String> cityCol;
    @FXML
    private TableColumn<StudentModel, String> countryCol;
    @FXML
    private TextField searchField;

    ObservableList<StudentModel> studentModelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection connection = ConnectionManager.getConnection();

        // SQL query
        String query = "SELECT studentId, studentEmail, name, birthday, gender, address, city, country FROM Student";

        try {
            // Execute the SQL query
            Statement statement = connection.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            // Get all the data from the query output
            while (queryOutput.next()) {
                Integer queryStudentId = queryOutput.getInt("studentId");
                String queryStudentEmail = queryOutput.getString("studentEmail");
                String queryStudentName = queryOutput.getString("name");
                String queryStudentBirthday = queryOutput.getString("birthday");
                String queryStudentGender = queryOutput.getString("gender");
                String queryStudentAddress = queryOutput.getString("address");
                String queryStudentCity = queryOutput.getString("city");
                String queryStudentCountry = queryOutput.getString("country");

                // Populate the student observe list
                studentModelObservableList.add(new StudentModel(queryStudentId, queryStudentEmail, queryStudentName, queryStudentBirthday, queryStudentGender, queryStudentAddress, queryStudentCity, queryStudentCountry));
            }

            // Link the database columns with the actual table columns
            studentIdCol.setCellValueFactory(new PropertyValueFactory<>("studentId"));
            studentEmailCol.setCellValueFactory(new PropertyValueFactory<>("studentEmail"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            birthdayCol.setCellValueFactory(new PropertyValueFactory<>("birthday"));
            genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
            countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));

            // Create filtered list
            FilteredList<StudentModel> filteredData = new FilteredList<>(studentModelObservableList, b -> true);

            // Search through the students
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(studentModel -> {
                    // Don't search when value is empty
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    // Check if search keyword is in student name
                    if (studentModel.getName().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });

            // Bind the sorted data with the table view
            SortedList<StudentModel> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(studentsTableView.comparatorProperty());

            // Load the data into the table
            studentsTableView.setItems(sortedData);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
