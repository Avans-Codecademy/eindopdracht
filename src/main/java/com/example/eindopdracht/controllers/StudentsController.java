package com.example.eindopdracht.controllers;

import com.example.eindopdracht.database.ConnectionManager;
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

import java.util.regex.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;
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
    private TableColumn<StudentModel, String> postcodeCol;
    @FXML
    private TableColumn<StudentModel, String> houseNumberCol;
    @FXML
    private TextField searchField;
    @FXML
    private Button createStudentBtn;
    @FXML
    private Button updateStudentBtn;

    @FXML
    private TextField studentAddress;
    @FXML
    private DatePicker studentBirthday;
    @FXML
    private TextField studentCity;
    @FXML
    private TextField studentCountry;
    @FXML
    private TextField studentEmail;
    @FXML
    private ComboBox<String> studentGender;
    @FXML
    private TextField studentName;
    @FXML
    private TextField studentPostcode;
    @FXML
    private TextField studentHouseNumber;

    private int selectedIndex;
    private int selectedId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set items for gender ComboBox
        studentGender.setItems(FXCollections.observableArrayList("MALE", "FEMALE", "OTHER"));

        loadTable();
    }

    public void loadTable() {
        ObservableList<StudentModel> studentsList = FXCollections.observableArrayList();
        Connection connection = ConnectionManager.getConnection();

        // SQL query
        String query = "SELECT studentId, studentEmail, name, birthday, gender, address, city, country, postcode, houseNumber FROM Student";

        try {
            // Execute the SQL query
            Statement statement = connection.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            // Get all the data from the query output
            while (queryOutput.next()) {
                Integer queryStudentId = queryOutput.getInt("studentId");
                String queryStudentEmail = queryOutput.getString("studentEmail");
                String queryStudentName = queryOutput.getString("name");
                LocalDate queryStudentBirthday = LocalDate.parse(queryOutput.getString("birthday"));
                String queryStudentGender = queryOutput.getString("gender");
                String queryStudentAddress = queryOutput.getString("address");
                String queryStudentCity = queryOutput.getString("city");
                String queryStudentCountry = queryOutput.getString("country");
                String queryStudentPostcode = queryOutput.getString("postcode");
                Integer queryStudentHouseNumber = queryOutput.getInt("houseNumber");

                // Populate the student observe list
                studentsList.add(new StudentModel(queryStudentId, queryStudentEmail, queryStudentName, queryStudentBirthday, queryStudentGender, queryStudentAddress, queryStudentCity, queryStudentCountry, queryStudentPostcode, queryStudentHouseNumber));
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
            postcodeCol.setCellValueFactory(new PropertyValueFactory<>("postcode"));
            houseNumberCol.setCellValueFactory(new PropertyValueFactory<>("houseNumber"));

            // Create filtered list
            FilteredList<StudentModel> filteredData = new FilteredList<>(studentsList, b -> true);

            // Search through the students
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(studentModel -> {
                    // Don't search when value is empty
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    // Check if search keyword is in student name
                    if (studentModel.getName().toLowerCase().contains(searchKeyword)) {
                        return true;
                    } else if (studentModel.getStudentEmail().toLowerCase().contains(searchKeyword)) {
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
            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while retrieving data from database!");
            alert.show();
        }

        // Add table on mouse click event
        studentsTableView.setRowFactory(tv -> {
            TableRow<StudentModel> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    // Set selected ID
                    selectedIndex = studentsTableView.getSelectionModel().getSelectedIndex();
                    selectedId = Integer.parseInt(String.valueOf(studentsTableView.getItems().get(selectedIndex).getStudentId()));
                }
            });

            return myRow;
        });
    }

    @FXML
    void addStudent(ActionEvent event) {
        String email, name, gender, address, city, country, postcode;
        int houseNumber;
        LocalDate birthday;

        // Get all the information from the form
        email = studentEmail.getText();

        if (!isEmailValid(email)) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Wrong email!");
            alert.show();
            return;
        }
        postcode = studentPostcode.getText();
        if (!isValidPostcode(postcode)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Postcode is wrong!");
            alert.show();
            return;
        }
        birthday = studentBirthday.getValue();
        if (!isBirthdayValid(birthday)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Not a valid birthday!");
            alert.show();
            return;
        }


        name = studentName.getText();
        gender = studentGender.getValue().toUpperCase();
        address = studentAddress.getText();
        city = studentCity.getText();
        country = studentCountry.getText();

        houseNumber = Integer.parseInt(studentHouseNumber.getText());


            // Create the student in the database
        Connection connection = ConnectionManager.getConnection();

        // SQL query
        String query = "INSERT INTO Student(studentEmail,name,birthday,gender,address,city,country,postcode,houseNumber) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            // Execute the SQL query
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, name);
            statement.setDate(3, Date.valueOf(birthday));
            statement.setString(4, gender);
            statement.setString(5, address);
            statement.setString(6, city);
            statement.setString(7, country);
            statement.setString(8, postcode);
            statement.setInt(9, houseNumber);

            statement.executeUpdate();

            // Send alert to the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Student successfully created!");

            alert.showAndWait();

            // Reset values of the inputs
            resetValues();

            // Reload table
            loadTable();

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while creating student in database!");
            alert.show();
        }

    }
    public boolean isEmailValid(String email){
        //Regular Expression
        if (email == null){
            return false;
        }
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        //Compile regular expression to get the pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean isValidPostcode(String postcodeToCheck) {
        if (postcodeToCheck == null){
            return false;
        }
        return postcodeToCheck.matches("[1-9]{1}[0-9]{3}\s[a-zA-Z]{2}");
    }
    public boolean isBirthdayValid(LocalDate birthday){
        return birthday != null && !birthday.isAfter(LocalDate.now()) && !birthday.isBefore(LocalDate.of(1900, 1, 1));
    }


    @FXML
    void editStudent(ActionEvent event) {
        if (selectedId == 0) {
            return;
        }

        // Hide create button & show edit button
        createStudentBtn.setVisible(false);
        updateStudentBtn.setVisible(true);

        // Fill the fields with the selected Student
        studentEmail.setText(studentsTableView.getItems().get(selectedIndex).getStudentEmail());
        studentName.setText(studentsTableView.getItems().get(selectedIndex).getName());
        studentBirthday.setValue(studentsTableView.getItems().get(selectedIndex).getBirthday());
        studentGender.setValue(studentsTableView.getItems().get(selectedIndex).getGender());
        studentAddress.setText(studentsTableView.getItems().get(selectedIndex).getAddress());
        studentCity.setText(studentsTableView.getItems().get(selectedIndex).getCity());
        studentCountry.setText(studentsTableView.getItems().get(selectedIndex).getCountry());
        studentPostcode.setText(studentsTableView.getItems().get(selectedIndex).getPostcode());
        studentHouseNumber.setText(String.valueOf(studentsTableView.getItems().get(selectedIndex).getHouseNumber()));
    }

    @FXML
    void updateStudent(ActionEvent event) {
        String email, name, gender, address, city, country, postcode;
        int houseNumber;
        LocalDate birthday;

        // Get all the information from the form
        email = studentEmail.getText();
        name = studentName.getText();
        birthday = studentBirthday.getValue();
        gender = studentGender.getValue().toUpperCase();
        address = studentAddress.getText();
        city = studentCity.getText();
        country = studentCountry.getText();
        postcode = studentPostcode.getText();
        houseNumber = Integer.parseInt(studentHouseNumber.getText());

        // Create the student in the database
        Connection connection = ConnectionManager.getConnection();

        // SQL query
        String query = "UPDATE Student SET studentEmail=?, name=?, birthday=?, gender=?, address=?, city=?, country=?, postcode=?, houseNumber=? WHERE studentId = ?;";

        try {
            // Execute the SQL query
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, name);
            statement.setDate(3, Date.valueOf(birthday));
            statement.setString(4, gender);
            statement.setString(5, address);
            statement.setString(6, city);
            statement.setString(7, country);
            statement.setString(8, postcode);
            statement.setInt(9, houseNumber);
            statement.setInt(10, selectedId);

            statement.executeUpdate();

            // Send alert to the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Student successfully updated!");

            alert.showAndWait();

            // Reload table
            loadTable();

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while updating student in database!");
            alert.show();
        }

        // Hide update button & show create button
        updateStudentBtn.setVisible(false);
        createStudentBtn.setVisible(true);

        // Reset values of the inputs
        resetValues();
    }

    private void resetValues() {
        studentEmail.setText("");
        studentName.setText("");
        studentBirthday.setValue(null);
        studentGender.setValue(null);
        studentAddress.setText("");
        studentCity.setText("");
        studentCountry.setText("");
        studentPostcode.setText("");
        studentHouseNumber.setText("");

        selectedId = 0;
        selectedIndex = 0;
    }

    @FXML
    void deleteStudent(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure you want to delete this Student?");
        Optional<ButtonType> result = alert.showAndWait();

        // If user click "OK", delete the Student
        if (result.get() == ButtonType.OK) {
            Connection connection = ConnectionManager.getConnection();

            // SQL query
            String query = "DELETE FROM Student WHERE StudentId = ?;";

            try {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, selectedId);
                statement.executeUpdate();

                selectedId = 0;
                selectedIndex = 0;

                // Send alert to the user
                Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                alert3.setHeaderText("Student successfully deleted!");

                alert3.showAndWait();

                // Reload table
                loadTable();
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setHeaderText("Error while deleting student in database!");
                alert2.show();
            }
        }

    }

}
