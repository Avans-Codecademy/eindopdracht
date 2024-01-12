package com.example.eindopdracht.controllers;

import com.example.eindopdracht.database.ConnectionManager;
import com.example.eindopdracht.database.classes.Entry;
import com.example.eindopdracht.database.classes.Enums.Level;
import com.example.eindopdracht.database.models.StudentModel;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class EntriesController implements Initializable {
    @FXML
    private TableView<Entry> entriesTableView;

    @FXML
    private TableColumn<Entry, Integer> entryIdCol;
    @FXML
    private TableColumn<Entry, LocalDate> entryDateCol;
    @FXML
    private TableColumn<Entry, String> studentEmailCol;
    @FXML
    private TableColumn<Entry, String> courseNameCol;

    @FXML
    private DatePicker entryDate;
    @FXML
    private ComboBox<String> studentEmail;
    @FXML
    private ComboBox<String> courseName;

    @FXML
    private Button createEntryBtn;
    @FXML
    private Button updateEntryBtn;

    @FXML
    private TextField searchField;

    private int selectedIndex;
    private int selectedId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set items for studentEmail ComboBox
        loadStudents();
        // Set items for courseName ComboBox
        loadCourses();

        loadTable();
    }

    public void loadStudents() {
        ArrayList<String> studentEmails = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();

        // SQL query
        String query = "SELECT studentEmail FROM Student";

        try {
            // Execute the SQL query
            Statement statement = connection.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            // Add the emails to the ArrayList
            while (queryOutput.next()) {
                studentEmails.add(queryOutput.getString("studentEmail"));
            }

            // Load them into the ComboBox
            studentEmail.setItems(FXCollections.observableArrayList(studentEmails));
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while retrieving data from database!");
            alert.show();
        }
    }

    public void loadCourses() {
        ArrayList<String> courseNames = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();

        // SQL query
        String query = "SELECT courseName FROM Course";

        try {
            // Execute the SQL query
            Statement statement = connection.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            // Add the names to the ArrayList
            while (queryOutput.next()) {
                courseNames.add(queryOutput.getString("courseName"));
            }

            // Load them into the ComboBox
            courseName.setItems(FXCollections.observableArrayList(courseNames));
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while retrieving data from database!");
            alert.show();
        }
    }

    public void loadTable() {
        ObservableList<Entry> entriesList = FXCollections.observableArrayList();
        Connection connection = ConnectionManager.getConnection();

        // SQL query
        String query = "SELECT entryId, entryDate, studentEmail, courseName FROM Entry;";

        try {
            // Execute the SQL query
            Statement statement = connection.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            // Get all the data from the query output
            while (queryOutput.next()) {
                int queryEntryId = Integer.parseInt(queryOutput.getString("entryId"));
                LocalDate queryEntryDate = queryOutput.getDate("entryDate").toLocalDate();
                String queryStudentEmail = queryOutput.getString("studentEmail");
                String queryCourseName = queryOutput.getString("courseName");

                // Populate the observe list
                entriesList.add(new Entry(queryEntryId, queryEntryDate, queryStudentEmail, queryCourseName));
            }

            // Link the database columns with the actual table columns
            entryIdCol.setCellValueFactory(new PropertyValueFactory<>("entryId"));
            entryDateCol.setCellValueFactory(new PropertyValueFactory<>("entryDate"));
            studentEmailCol.setCellValueFactory(new PropertyValueFactory<>("studentEmail"));
            courseNameCol.setCellValueFactory(new PropertyValueFactory<>("courseName"));

            // Create filtered list
            FilteredList<Entry> filteredData = new FilteredList<>(entriesList, b -> true);

            // Search through the courses
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(entryModel -> {
                    // Don't search when value is empty
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    // Check if search keyword is in entry name
                    if (entryModel.getStudentEmail().toLowerCase().contains(searchKeyword)) {
                        return true;
                    } else if (entryModel.getCourseName().toLowerCase().contains(searchKeyword)) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });

            // Bind the sorted data with the table view
            SortedList<Entry> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(entriesTableView.comparatorProperty());

            // Load the data into the table
            entriesTableView.setItems(sortedData);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while retrieving data from database!");
            alert.show();
        }

        // Add table on mouse click event
        entriesTableView.setRowFactory(tv -> {
            TableRow<Entry> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    // Set selected ID
                    selectedIndex = entriesTableView.getSelectionModel().getSelectedIndex();
                    selectedId = entriesTableView.getItems().get(selectedIndex).getEntryId();
                }
            });

            return myRow;
        });
    }

    @FXML
    void addEntry(ActionEvent event) {
        LocalDate entryDate;
        String studentEmail, courseName;

        // Get all the information from the form
        entryDate = this.entryDate.getValue();
        studentEmail = this.studentEmail.getValue();
        courseName = this.courseName.getValue();

        // Create the entry in the database
        Connection connection = ConnectionManager.getConnection();

        // SQL query
        String query = "INSERT INTO Entry(entryDate, studentEmail, courseName) VALUES(?, ?, ?);";

        try {
            // Execute the SQL query
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, Date.valueOf(entryDate));
            statement.setString(2, studentEmail);
            statement.setString(3, courseName);

            statement.executeUpdate();

            // Send alert to the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Entry successfully created!");

            alert.showAndWait();

            // Reset values of the inputs
            resetValues();

            // Reload table
            loadTable();

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while creating entry in database!");
            alert.show();
        }
    }

    @FXML
    void editEntry(ActionEvent event) {
        if (selectedId == 0) {
            return;
        }

        // Hide create button & show edit button
        createEntryBtn.setVisible(false);
        updateEntryBtn.setVisible(true);

        // Fill the fields with the selected Course
        entryDate.setValue(entriesTableView.getItems().get(selectedIndex).getEntryDate());
        studentEmail.setValue(entriesTableView.getItems().get(selectedIndex).getStudentEmail());
        courseName.setValue(entriesTableView.getItems().get(selectedIndex).getCourseName());
    }

    @FXML
    void updateEntry(ActionEvent event) {
        LocalDate entryDate;
        String studentEmail, courseName;

        // Get all the information from the form
        entryDate = this.entryDate.getValue();
        studentEmail = this.studentEmail.getValue();
        courseName = this.courseName.getValue();

        // Create the course in the database
        Connection connection = ConnectionManager.getConnection();

        // SQL query
        String query = "UPDATE Entry SET entryDate=?, studentEmail=?, courseName=? WHERE entryId = ?;";

        try {
            // Execute the SQL query
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, Date.valueOf(entryDate));
            statement.setString(2, studentEmail);
            statement.setString(3, courseName);
            statement.setInt(4, selectedId);

            statement.executeUpdate();

            // Send alert to the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Entry successfully updated!");

            alert.showAndWait();

            // Reload table
            loadTable();

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while updating entry in database!");
            alert.show();
        }

        // Hide update button & show create button
        updateEntryBtn.setVisible(false);
        createEntryBtn.setVisible(true);

        // Reset values of the inputs
        resetValues();
    }

    private void resetValues() {
        entryDate.setValue(null);
        studentEmail.setValue(null);
        courseName.setValue(null);

        selectedId = 0;
        selectedIndex = 0;
    }

    @FXML
    void deleteEntry(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure you want to delete this Entry?");
        Optional<ButtonType> result = alert.showAndWait();

        // If user click "OK", delete the Entry
        if (result.get() == ButtonType.OK) {
            Connection connection = ConnectionManager.getConnection();

            // SQL query
            String query = "DELETE FROM Entry WHERE entryId = ?;";

            try {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, selectedId);
                statement.executeUpdate();

                selectedId = 0;
                selectedIndex = 0;

                // Send alert to the entry
                Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                alert3.setHeaderText("Entry successfully deleted!");

                alert3.showAndWait();

                // Reload table
                loadTable();
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setHeaderText("Error while deleting entry in database!");
                alert2.show();
            }
        }
    }
}
