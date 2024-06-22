package com.moodtracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Update these values with your actual database information
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mood_tracker";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "tentacool";

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Error connecting to database: " + e.getMessage());
        }
    }

    public static String getJdbcUrl() {
        return JDBC_URL;
    }
}
