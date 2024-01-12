package com.example.eindopdracht.controllers;

import com.example.eindopdracht.database.ConnectionManager;
import com.example.eindopdracht.database.classes.Certification;
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

public class CertificationController extends Controller implements Initializable {
    @FXML
    private TableView<Certification> certificationsTableView;

    @FXML
    private TableColumn<Certification, String> certificationNameCol;

    @FXML
    private TextField certificationName;

    @FXML
    private Button createCertificationBtn;
    @FXML
    private Button updateCertificationBtn;

    @FXML
    private TextField searchField;

    private int selectedIndex;
    private String selectedName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }

    public void loadTable() {
        ObservableList<Certification> certificationList = FXCollections.observableArrayList();
        Connection connection = ConnectionManager.getConnection();

        // SQL query
        String query = "SELECT certificationName FROM Certification;";

        try {
            // Execute the SQL query
            Statement statement = connection.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            // Get all the data from the query output
            while (queryOutput.next()) {
                String queryCertificationName = queryOutput.getString("certificationName");

                // Populate the observe list
                certificationList.add(new Certification(queryCertificationName));
            }

            // Link the database columns with the actual table columns
            certificationNameCol.setCellValueFactory(new PropertyValueFactory<>("certificationName"));

            // Create filtered list
            FilteredList<Certification> filteredData = new FilteredList<>(certificationList, b -> true);

            // Search through the certifications
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(certificationModel -> {
                    // Don't search when value is empty
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    // Check if search keyword is in certification name
                    if (certificationModel.getCertificationName().toLowerCase().contains(searchKeyword)) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });

            // Bind the sorted data with the table view
            SortedList<Certification> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(certificationsTableView.comparatorProperty());

            // Load the data into the table
            certificationsTableView.setItems(sortedData);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while retrieving data from database!");
            alert.show();
        }

        // Add table on mouse click event
        certificationsTableView.setRowFactory(tv -> {
            TableRow<Certification> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    // Set selected ID
                    selectedIndex = certificationsTableView.getSelectionModel().getSelectedIndex();
                    selectedName = certificationsTableView.getItems().get(selectedIndex).getCertificationName();
                }
            });

            return myRow;
        });
    }

    @FXML
    void addCertification(ActionEvent event) {
        String certificationName;

        // Get all the information from the form
        certificationName = this.certificationName.getText();

        // Create the certification in the database
        Connection connection = ConnectionManager.getConnection();

        // SQL query
        String query = "INSERT INTO Certification(certificationName) VALUES(?);";

        try {
            // Execute the SQL query
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, certificationName);

            statement.executeUpdate();

            // Send alert to the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Certification successfully created!");

            alert.showAndWait();

            // Reset values of the inputs
            resetValues();

            // Reload table
            loadTable();

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while creating certification in database!");
            alert.show();
        }
    }

    @FXML
    void editCertification(ActionEvent event) {
        if (selectedName == null) {
            return;
        }

        // Hide create button & show edit button
        createCertificationBtn.setVisible(false);
        updateCertificationBtn.setVisible(true);

        // Fill the fields with the selected certification
        certificationName.setText(certificationsTableView.getItems().get(selectedIndex).getCertificationName());
    }

    @FXML
    void updateCertification(ActionEvent event) {
        String certificationName;

        // Get all the information from the form
        certificationName = this.certificationName.getText();

        // Create the Certification in the database
        Connection connection = ConnectionManager.getConnection();

        // SQL query
        String query = "UPDATE Certification SET certificationName=? WHERE certificationName = ?;";

        try {
            // Execute the SQL query
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, certificationName);
            statement.setString(2, selectedName);

            statement.executeUpdate();

            // Send alert to the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Certification successfully updated!");

            alert.showAndWait();

            // Reload table
            loadTable();

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while updating certification in database!");
            alert.show();
        }

        // Hide update button & show create button
        updateCertificationBtn.setVisible(false);
        createCertificationBtn.setVisible(true);

        // Reset values of the inputs
        resetValues();
    }

    private void resetValues() {
        certificationName.setText("");

        selectedName = null;
        selectedIndex = 0;
    }

    @FXML
    void deleteCertification(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure you want to delete this Certification?");
        Optional<ButtonType> result = alert.showAndWait();

        // If user click "OK", delete the Certification
        if (result.get() == ButtonType.OK) {
            Connection connection = ConnectionManager.getConnection();

            // SQL query
            String query = "DELETE FROM Certification WHERE certificationName = ?;";

            try {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, selectedName);
                statement.executeUpdate();

                selectedName = null;
                selectedIndex = 0;

                // Send alert to the user
                Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                alert3.setHeaderText("Certification successfully deleted!");

                alert3.showAndWait();

                // Reload table
                loadTable();
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setHeaderText("Error while deleting certification in database!");
                alert2.show();
            }
        }
    }
}
