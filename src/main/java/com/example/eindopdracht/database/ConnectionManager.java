package com.example.eindopdracht.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static String connectionUrl;
    private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String username;
    private static String password;
    private static Connection dbConnection;

    public static Connection getConnection() {
        loadDatabaseCredentials();

        try {
            // Load the drive class
            Class.forName(driverName);
            try {
                // Try to connect to the database using the provided credentials
                dbConnection = DriverManager.getConnection(connectionUrl, username, password);
            } catch (SQLException ex) {
                // Log an exception when connection failed
                System.out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            // Log an exception when database driver was not found
            System.out.println("Driver not found.");
        }

        return dbConnection;
    }

    private static void loadDatabaseCredentials() {
        // Create new properties instance
        Properties props = new Properties();
        FileInputStream input;

        try {
            // Load the properties from the database.properties file
            input = new FileInputStream("database.properties");
            props.load(input);

            // Define the class variables
            connectionUrl = props.getProperty("DB_URL");
            username = props.getProperty("DB_USERNAME");
            password = props.getProperty("DB_PASSWORD");
        } catch (IOException e) {
            System.out.println("Could not load database credentials from database.properties!");
        }
    }

    public static void disconnect() throws SQLException {
        if (dbConnection == null) return;

        // Close the database connection
        dbConnection.close();
    }
}
