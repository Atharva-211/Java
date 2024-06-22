package dbms;

import java.sql.*;
import java.util.Scanner;

public class BookExchangeSystem {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/BookExchangeSystem";
    private static final String USER = "root";
    private static final String PASS = "tentacool";

    public static void main(String[] args) {
        createTables(); // Ensure tables are created when the program starts

        Scanner scanner = new Scanner(System.in);

     // Display menu
        System.out.println("Welcome to the Book Exchange System!");


        int choice;
        do {
            System.out.println("\n1. Add Member");
            System.out.println("2. Add Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Add Review");
            System.out.println("6. Check Availability");
            System.out.println("7. Lend Book");
            System.out.println("0. Exit");
            System.out.print("\nEnter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addMember(scanner);
                    break;
                case 2:
                    addBook(scanner);
                    break;
                case 3:
                    borrowBook(scanner);
                    break;
                case 4:
                    returnBook(scanner);
                    break;
                case 5:
                    addReview(scanner);
                    break;
                case 6:
                    checkAvailability(scanner);
                    break;
                case 7:
                    lendBook(scanner);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void createTables() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            // Create Members table
            stmt.execute("CREATE TABLE IF NOT EXISTS Members (" +
                    "MemberID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "Name VARCHAR(255) NOT NULL, " +
                    "Email VARCHAR(255) NOT NULL UNIQUE, " +
                    "Address VARCHAR(255), " +
                    "Phone VARCHAR(20))");

            // Create Books table
            stmt.execute("CREATE TABLE IF NOT EXISTS Books (" +
                    "BookID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "Title VARCHAR(255) NOT NULL, " +
                    "Author VARCHAR(255) NOT NULL, " +
                    "Genre VARCHAR(50), " +
                    "Description TEXT, " +
                    "Status VARCHAR(20) DEFAULT 'Available')");

            // Create Borrowing History table
            stmt.execute("CREATE TABLE IF NOT EXISTS BorrowingHistory (" +
                    "BorrowID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "MemberID INT, " +
                    "BookID INT, " +
                    "BorrowDate DATE, " +
                    "ReturnDate DATE, " +
                    "Status VARCHAR(20) DEFAULT 'Borrowed', " +
                    "FOREIGN KEY(MemberID) REFERENCES Members(MemberID), " +
                    "FOREIGN KEY(BookID) REFERENCES Books(BookID))");

            // Create Reviews table
            stmt.execute("CREATE TABLE IF NOT EXISTS Reviews (" +
                    "ReviewID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "MemberID INT, " +
                    "BookID INT, " +
                    "Rating INT, " +
                    "ReviewText TEXT, " +
                    "ReviewDate DATE, " +
                    "FOREIGN KEY(MemberID) REFERENCES Members(MemberID), " +
                    "FOREIGN KEY(BookID) REFERENCES Books(BookID))");
            
            // Create Lending table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Lending (" +
                    "LendingID INT AUTO_INCREMENT PRIMARY KEY," +
                    "BookID INT NOT NULL," +
                    "BorrowerName VARCHAR(255) NOT NULL," +
                    "DueDate DATE NOT NULL," +
                    "FOREIGN KEY (BookID) REFERENCES Books(BookID)" +
                    ")");
        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
        }
    }

    public static void addMember(Scanner scanner) {
        System.out.println("\nAdding Member:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Members (Name, Email, Address, Phone) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, address);
            stmt.setString(4, phone);
            stmt.executeUpdate();
            System.out.println("Member added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding member: " + e.getMessage());
        }
    }

    public static void addBook(Scanner scanner) {
        System.out.println("\nAdding Book:");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Books (Title, Author, Genre, Description) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, genre);
            stmt.setString(4, description);
            stmt.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding book: " + e.getMessage());
        }
    }

    public static void borrowBook(Scanner scanner) {
        System.out.println("\nBorrowing Book:");
        System.out.print("Enter Member ID: ");
        int memberID = scanner.nextInt();
        System.out.print("Enter Book ID: ");
        int bookID = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Check if the book is available
        if (!isBookAvailable(bookID)) {
            System.out.println("The book is not available for borrowing.");
            return;
        }

        System.out.print("Enter Borrow Date (YYYY-MM-DD): ");
        String borrowDate = scanner.nextLine();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO BorrowingHistory (MemberID, BookID, BorrowDate) VALUES (?, ?, ?)")) {
            stmt.setInt(1, memberID);
            stmt.setInt(2, bookID);
            stmt.setString(3, borrowDate);
            stmt.executeUpdate();

            // Update book status to 'Borrowed'
            try (PreparedStatement updateStmt = conn.prepareStatement("UPDATE Books SET Status = 'Borrowed' WHERE BookID = ?")) {
                updateStmt.setInt(1, bookID);
                updateStmt.executeUpdate();
            }
            System.out.println("Book borrowed successfully.");
        } catch (SQLException e) {
            System.err.println("Error borrowing book: " + e.getMessage());
        }
    }
    
    public static void lendBook(Scanner scanner) {
        System.out.println("\nLending Book:");
        System.out.print("Enter Book ID: ");
        int bookID = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Check if the book is available
        if (isBookAvailable(bookID)) {
            System.out.println("The book is currently available for lending.");
            return;
        }

        System.out.print("Enter Borrower's Name: ");
        String borrowerName = scanner.nextLine();
        System.out.print("Enter Due Date (YYYY-MM-DD): ");
        String dueDate = scanner.nextLine();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Lending (BookID, BorrowerName, DueDate) VALUES (?, ?, ?)")) {
            stmt.setInt(1, bookID);
            stmt.setString(2, borrowerName);
            stmt.setString(3, dueDate);
            stmt.executeUpdate();

            // Update book status to 'Lent'
            try (PreparedStatement updateStmt = conn.prepareStatement("UPDATE Books SET Status = 'Lent' WHERE BookID = ?")) {
                updateStmt.setInt(1, bookID);
                updateStmt.executeUpdate();
            }
            System.out.println("Book lent successfully.");
        } catch (SQLException e) {
            System.err.println("Error lending book: " + e.getMessage());
        }
    }
    
    public static void checkAvailability(Scanner scanner) {
        System.out.println("\nChecking Availability:");
        System.out.print("Enter Book ID: ");
        int bookID = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (isBookAvailable(bookID)) {
            System.out.println("The book is available.");
        } else {
            System.out.println("The book is not available.");
        }
    }

    public static boolean isBookAvailable(int bookID) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT Status FROM Books WHERE BookID = ?")) {
            stmt.setInt(1, bookID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String status = rs.getString("Status");
                return status.equals("Available");
            }
        } catch (SQLException e) {
            System.err.println("Error checking book availability: " + e.getMessage());
        }
        return false; // Default to not available
    }



    public static void returnBook(Scanner scanner) {
        System.out.println("\nReturning Book:");
        System.out.print("Enter Borrow ID: ");
        int borrowID = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter Return Date (YYYY-MM-DD): ");
        String returnDate = scanner.nextLine();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE BorrowingHistory SET ReturnDate = ?, Status = 'Returned' WHERE BorrowID = ?")) {
            stmt.setString(1, returnDate);
            stmt.setInt(2, borrowID);
            stmt.executeUpdate();

            // Update book status to 'Available'
            try (PreparedStatement updateStmt = conn.prepareStatement("UPDATE Books SET Status = 'Available' WHERE BookID = (SELECT BookID FROM BorrowingHistory WHERE BorrowID = ?)")) {
                updateStmt.setInt(1, borrowID);
                updateStmt.executeUpdate();
            }
            System.out.println("Book returned successfully.");
        } catch (SQLException e) {
            System.err.println("Error returning book: " + e.getMessage());
        }
    }

    public static void addReview(Scanner scanner) {
        System.out.println("\nAdding Review:");
        System.out.print("Enter Member ID: ");
        int memberID = scanner.nextInt();
        System.out.print("Enter Book ID: ");
        int bookID = scanner.nextInt();
        System.out.print("Enter Rating (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter Review Text: ");
        String reviewText = scanner.nextLine();
        System.out.print("Enter Review Date (YYYY-MM-DD): ");
        String reviewDate = scanner.nextLine();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Reviews (MemberID, BookID, Rating, ReviewText, ReviewDate) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setInt(1, memberID);
            stmt.setInt(2, bookID);
            stmt.setInt(3, rating);
            stmt.setString(4, reviewText);
            stmt.setString(5, reviewDate);
            stmt.executeUpdate();
            System.out.println("Review added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding review: " + e.getMessage());
        }
    }
    
    

}

