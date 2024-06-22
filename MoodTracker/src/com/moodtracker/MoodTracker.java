package com.moodtracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MoodTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Mood Tracker!");
        System.out.println("How are you feeling today?");
        System.out.println("1. Happy\n2. Angry\n3. Sad\n4. Lazy");
        System.out.print("Enter your choice (1/2/3/4): ");
        int choice = scanner.nextInt();

        String mood;
        switch (choice) {
            case 1:
                mood = "Happy";
                break;
            case 2:
                mood = "Angry";
                break;
            case 3:
                mood = "Sad";
                break;
            case 4:
                mood = "Lazy";
                break;
            default:
                mood = "Unknown";
                break;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO mood (mood) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, mood);
            statement.executeUpdate();
            System.out.println("Your mood has been recorded successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
