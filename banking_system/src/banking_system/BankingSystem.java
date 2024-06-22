package banking_system;

import java.sql.*;
import java.util.Scanner;

public class BankingSystem {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/banking_system";
    private static final String USER = "root";
    private static final String PASS = "tentacool";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            createTables(stmt);
            Scanner scanner = new Scanner(System.in);
            int choice;
            do {
                System.out.println("\nBanking System Menu:");
                System.out.println("1. Create Account");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Check Balance");
                System.out.println("5. View Transaction History");
                System.out.println("6. Set Daily Spending Limit");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        createAccount(conn, scanner);
                        break;
                    case 2:
                        deposit(conn, scanner);
                        break;
                    case 3:
                        withdraw(conn, scanner);
                        break;
                    case 4:
                        checkBalance(conn, scanner);
                        break;
                    case 5:
                        viewTransactionHistory(conn, scanner);
                        break;
                    case 6:
                        setDailySpendingLimit(conn, scanner);
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } while (choice != 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTables(Statement stmt) throws SQLException {
        String createAccountTable = "CREATE TABLE IF NOT EXISTS accounts (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "balance DECIMAL(10,2) DEFAULT 0.00," +
                "daily_limit DECIMAL(10,2) DEFAULT 0.00)";
        stmt.executeUpdate(createAccountTable);

        String createTransactionTable = "CREATE TABLE IF NOT EXISTS transactions (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "account_id INT," +
                "amount DECIMAL(10,2)," +
                "type VARCHAR(10)," +
                "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY (account_id) REFERENCES accounts(id))";
        stmt.executeUpdate(createTransactionTable);
    }

    private static void createAccount(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter account holder's name: ");
        String name = scanner.next();
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        System.out.print("Enter daily spending limit: ");
        double dailyLimit = scanner.nextDouble();

        String sql = "INSERT INTO accounts (name, balance, daily_limit) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, balance);
            pstmt.setDouble(3, dailyLimit);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0)
                System.out.println("Account created successfully!");
            else
                System.out.println("Failed to create account.");
        }
    }

    private static void deposit(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter account ID: ");
        int accountId = scanner.nextInt();
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();

        String sql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, amount);
            pstmt.setInt(2, accountId);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                insertTransaction(conn, accountId, amount, "Deposit");
                System.out.println("Deposit successful!");
            } else {
                System.out.println("Failed to deposit. Invalid account ID.");
            }
        }
    }

    private static void withdraw(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter account ID: ");
        int accountId = scanner.nextInt();
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();

        String sql = "SELECT balance, daily_limit FROM accounts WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                double balance = rs.getDouble("balance");
                double dailyLimit = rs.getDouble("daily_limit");
                if (amount <= balance && amount <= dailyLimit) {
                    sql = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(sql)) {
                        updateStmt.setDouble(1, amount);
                        updateStmt.setInt(2, accountId);
                        int affectedRows = updateStmt.executeUpdate();
                        if (affectedRows > 0) {
                            insertTransaction(conn, accountId, amount, "Withdrawal");
                            System.out.println("Withdrawal successful!");
                        }
                    }
                } else {
                    System.out.println("Insufficient funds or exceeding daily spending limit.");
                }
            } else {
                System.out.println("Invalid account ID.");
            }
        }
    }

    private static void insertTransaction(Connection conn, int accountId, double amount, String type) throws SQLException {
        String sql = "INSERT INTO transactions (account_id, amount, type) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            pstmt.setDouble(2, amount);
            pstmt.setString(3, type);
            pstmt.executeUpdate();
        }
    }

    private static void checkBalance(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter account ID: ");
        int accountId = scanner.nextInt();

        String sql = "SELECT balance FROM accounts WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                double balance = rs.getDouble("balance");
                System.out.println("Current balance: $" + balance);
            } else {
                System.out.println("Invalid account ID.");
            }
        }
    }

    private static void viewTransactionHistory(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter account ID: ");
        int accountId = scanner.nextInt();

        String sql = "SELECT * FROM transactions WHERE account_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("Transaction History:");
            while (rs.next()) {
                int id = rs.getInt("id");
                double amount = rs.getDouble("amount");
                String type = rs.getString("type");
                Timestamp timestamp = rs.getTimestamp("timestamp");
                System.out.println("ID: " + id + ", Amount: $" + amount + ", Type: " + type + ", Timestamp: " + timestamp);
            }
        }
    }

    private static void setDailySpendingLimit(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter account ID: ");
        int accountId = scanner.nextInt();
        System.out.print("Enter new daily spending limit: ");
        double newLimit = scanner.nextDouble();

        String sql = "UPDATE accounts SET daily_limit = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newLimit);
            pstmt.setInt(2, accountId);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Daily spending limit updated successfully!");
            } else {
                System.out.println("Failed to update daily spending limit. Invalid account ID.");
            }
        }
    }
}
