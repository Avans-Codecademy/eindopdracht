package com.example.eindopdracht.controllers;

import com.example.eindopdracht.database.ConnectionManager;
import com.example.eindopdracht.database.classes.Enums.Gender;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OverviewsController extends Controller implements Initializable {
    @FXML
    private Text averageProgressCourse;

    @FXML
    private Text averageProgressStudent;

    @FXML
    private ComboBox<String> courseCompleted;

    @FXML
    private ComboBox<String> courseForModule;

    @FXML
    private ComboBox<String> courseForProgress;

    @FXML
    private ComboBox<String> courseForRecommended;

    @FXML
    private ComboBox<Gender> gender;

    @FXML
    private Text mostIssued1;

    @FXML
    private Text mostIssued2;

    @FXML
    private Text mostIssued3;

    @FXML
    private Text mostWatched1;

    @FXML
    private Text mostWatched2;

    @FXML
    private Text mostWatched3;

    @FXML
    private Text numberCompletedCourses;

    @FXML
    private Text obtainedCertificates;

    @FXML
    private Text percentageSignedUp;

    @FXML
    private Text recommendedCourses;

    @FXML
    private ComboBox<String> studentCertificates;

    @FXML
    private ComboBox<String> studentForModule;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gender.setItems(FXCollections.observableArrayList(Gender.values()));

        loadCourses();

        loadStudents();

        getTop3MostWatchedWebcasts();

        getTop3MostIssuesCertificates();
    }

    public void getTop3MostWatchedWebcasts() {
        // SQL query
        Connection connection = ConnectionManager.getConnection();
        String query = "SELECT TOP 3 Title, COUNT(Title) AS amount FROM ContentItem JOIN ViewContentItem ON ViewContentItem.ContentItem = ContentItem.ContentItemId WHERE ContentItem.WebcastId IS NOT NULL GROUP BY Title ORDER BY amount DESC";

        try {
            Statement statement = connection.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            int positionCount = 1;

            while (queryOutput.next()) {
                if (positionCount == 1) {
                    mostWatched1.setText("1. " +
                            queryOutput.getString("Title") + " - Amount: " + queryOutput.getString("amount"));
                } else if (positionCount == 2) {
                    mostWatched2.setText("2. " +
                            queryOutput.getString("Title") + " - Amount: " + queryOutput.getString("amount"));
                } else if (positionCount == 3) {
                    mostWatched3.setText("3. " +
                            queryOutput.getString("Title") + " - Amount: " + queryOutput.getString("amount"));
                }
                positionCount++;
            }

        } catch (SQLException exception) {

            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while retrieving data from database!");
            alert.show();
        }

    }

    public void getTop3MostIssuesCertificates() {

        Connection connection = ConnectionManager.getConnection();
        String query = "SELECT TOP 3 CourseName, COUNT(CourseName) AS amount FROM Entry JOIN ObtainedCertification ON Entry.EntryId = ObtainedCertification.EntryId GROUP BY CourseName ORDER BY amount DESC";

        try {

            Statement statement = connection.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            int positionCount = 1;

            while (queryOutput.next()) {
                if (positionCount == 1) {
                    mostIssued1.setText(
                            queryOutput.getString("CourseName") + " - Amount: " + queryOutput.getString("amount"));
                } else if (positionCount == 2) {
                    mostIssued2.setText(
                            queryOutput.getString("CourseName") + " - Amount: " + queryOutput.getString("amount"));
                } else if (positionCount == 3) {
                    mostIssued3.setText(
                            queryOutput.getString("CourseName") + " - Amount: " + queryOutput.getString("amount"));
                }
                positionCount++;
            }

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

            // Load them into the ComboBoxes
            courseForProgress.setItems(FXCollections.observableArrayList(courseNames));
            courseForModule.setItems(FXCollections.observableArrayList(courseNames));
            courseForRecommended.setItems(FXCollections.observableArrayList(courseNames));
            courseCompleted.setItems(FXCollections.observableArrayList(courseNames));
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while retrieving data from database!");
            alert.show();
        }
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
            studentForModule.setItems(FXCollections.observableArrayList(studentEmails));
            studentCertificates.setItems(FXCollections.observableArrayList(studentEmails));
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while retrieving data from database!");
            alert.show();
        }
    }

    @FXML
    void selectGender(ActionEvent event) {
        Gender selectedGender = gender.getValue();

        // Create the certification in the database
        Connection connection = ConnectionManager.getConnection();

        // Get the total amount of signed courses
        String query1 = "SELECT COUNT(StudentID) AS countBehaald FROM Student WHERE Student.Gender = ?";

        // Get the total amount of signed courses for the selected gender
        String query2 = "SELECT COUNT(StudentID) AS countBehaald FROM Student WHERE Student.Gender = ? AND Student.StudentEmail IN (SELECT Entry.StudentEmail FROM Entry);";

        try {
            // Execute the SQL query
            PreparedStatement statement1 = connection.prepareStatement(query1);
            statement1.setString(1, selectedGender.toString());
            ResultSet queryOutput1 = statement1.executeQuery();

            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.setString(1, selectedGender.toString());
            ResultSet queryOutput2 = statement2.executeQuery();

            // Get the total amount of signed courses
            int totalSignedIn = 0;
            if (queryOutput1.next()) {
                totalSignedIn = queryOutput1.getInt(1);
            }

            // Get the total amount of signed courses for the selected gender
            int totalSignPerGender = 0;
            if (queryOutput2.next()) {
                totalSignPerGender = queryOutput2.getInt(1);
            }

            // Formater to make it a bit neater
            DecimalFormat df = new DecimalFormat("0.00");

            // The percentage can be calculated by dividing the part by the whole and doing
            // that times a 100.
            double percentage = 0.00;
            percentage = ((totalSignPerGender * 1.0) / totalSignedIn) * 100;
            percentageSignedUp.setText(df.format(percentage) + "%");

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while retrieving data in database!");
            alert.show();
        }
    }

    @FXML
    void selectCourseForProgress(ActionEvent event) {
        String selectedCourseName = courseForProgress.getValue();

        // Create the certification in the database
        Connection connection = ConnectionManager.getConnection();

        // Get the average progress per module of the selected course
        String query = "SELECT AVG(PercentageDone) gemiddeldePercentage FROM ViewContentItem WHERE ViewContentItem.ContentItem IN (SELECT ContentItemId FROM ContentItem WHERE Coursename = ?);";

        try {
            // Execute the SQL query
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, selectedCourseName);
            ResultSet queryOutput = statement.executeQuery();

            // Get the average progress per module of the selected course
            if (queryOutput.next()) {
                double percentage = queryOutput.getDouble(1);

                // Set result in text
                averageProgressCourse.setText(percentage + "%");
            }

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while retrieving data in database!");
            alert.show();
        }
    }

    @FXML
    void selectCourseForModule(ActionEvent event) {
        String selectedStudentEmail = studentForModule.getValue();
        String selectedCourseName = courseForModule.getValue();

        // Create the certification in the database
        Connection connection = ConnectionManager.getConnection();

        // Get the average progress per module of the selected student and course
        String query = "SELECT PercentageDone, ContentItem FROM ViewContentItem WHERE ViewContentItem.StudentEmail = ? AND ViewContentItem.ContentItem IN (SELECT ContentItemId FROM ContentItem WHERE ContentItem.Coursename = ? );";

        try {
            // Execute the SQL query
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, selectedStudentEmail);
            statement.setString(2, selectedCourseName);
            ResultSet queryOutput = statement.executeQuery();

            // Get the obtained certificates of the selected user
            StringBuilder modulesStringBuilder = new StringBuilder();

            if (!queryOutput.next()) {
                modulesStringBuilder.append("Student has not opend any modules connected to this course!");
            }

            while (queryOutput.next()) {
                modulesStringBuilder.append("ContentItemId: ").append(queryOutput.getString("ContentItem"))
                        .append(" = ")
                        .append(queryOutput.getString("PercentageDone")).append("%\n");
            }

            // Set result in text
            averageProgressStudent.setText(modulesStringBuilder.toString());

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while retrieving data in database!");
            alert.show();
        }
    }

    @FXML
    void selectStudentCertificates(ActionEvent event) {
        String selectedStudentEmail = studentCertificates.getValue();

        // Create the certification in the database
        Connection connection = ConnectionManager.getConnection();

        // Get the obtained certificates of the selected user
        String query = "SELECT CertificationName FROM ObtainedCertification WHERE ObtainedCertification.EntryId = ( SELECT Entry.EntryId FROM Entry WHERE Entry.StudentEmail = ?);";

        try {
            // Execute the SQL query
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, selectedStudentEmail);
            ResultSet queryOutput = statement.executeQuery();

            // Get the obtained certificates of the selected user
            StringBuilder certifications = new StringBuilder();

            while (queryOutput.next()) {
                certifications.append(queryOutput.getString("CertificationName")).append(", ");
            }

            // Set result in text
            obtainedCertificates.setText(certifications.toString());

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while retrieving data in database!");
            alert.show();
        }
    }

    @FXML
    void selectCourseForRecommended(ActionEvent event) {
        String selectedCourse = courseForRecommended.getValue();

        Connection connection = ConnectionManager.getConnection();

        // Get the recommended courses from the selected course
        String query = "SELECT * FROM Course WHERE Course.CourseName IN ( SELECT RecommendedCourse FROM Recommendation WHERE Recommendation.Recommender = ?);";

        try {
            // Execute the SQL query
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, selectedCourse);
            ResultSet queryOutput = statement.executeQuery();

            // Get the recommended courses from the selected course
            StringBuilder courses = new StringBuilder();

            while (queryOutput.next()) {
                courses.append(queryOutput.getString("CourseName")).append(", ");
            }

            // Set result in text
            recommendedCourses.setText(courses.toString());

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while retrieving data in database!");
            alert.show();
        }
    }

    @FXML
    void selectCourseCompleted(ActionEvent event) {
        String selectedCourse = courseCompleted.getValue();

        Connection connection = ConnectionManager.getConnection();

        // Get number of students who completed the selected course
        String query = "SELECT COUNT(StudentEmail) as studentenBehaald FROM Entry WHERE Entry.EntryId IN (SELECT EntryId FROM ObtainedCertification) AND Entry.CourseName = ? ;";

        try {
            // Execute the SQL query
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, selectedCourse);
            ResultSet queryOutput = statement.executeQuery();

            // Get number of students who completed the selected course
            if (queryOutput.next()) {
                int count = queryOutput.getInt(1);

                // Set result in text
                numberCompletedCourses.setText(count + " have compleated this course!");
            }

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error while retrieving data in database!");
            alert.show();
        }
    }
}
