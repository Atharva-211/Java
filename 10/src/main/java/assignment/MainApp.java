package assignment;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainApp {
    public static void main(String[] args) {
        // Create configuration instance and configure Hibernate
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        // Create session factory
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Open session
        try (Session session = sessionFactory.openSession()) {
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.println("Choose operation:");
                System.out.println("1. Create");
                System.out.println("2. Read");
                System.out.println("3. Update");
                System.out.println("4. Delete");
                System.out.println("5. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1: // Create
                        createStudent(session, scanner);
                        break;
                    case 2: // Read
                        readStudent(session, scanner);
                        break;
                    case 3: // Update
                        updateStudent(session, scanner);
                        break;
                    case 4: // Delete
                        deleteStudent(session, scanner);
                        break;
                    case 5: // Exit
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close session and session factory
            sessionFactory.close();
        }
    }

    private static void createStudent(Session session, Scanner scanner) {
        // Prompt user for student details
        System.out.println("Enter student details:");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Email: ");
        String email = scanner.nextLine();

        // Create a new student object
        Student student = new Student(firstName, lastName, age, email);

        // Begin transaction
        Transaction transaction = session.beginTransaction();

        // Save the student object to the database
        session.save(student);

        // Commit transaction
        transaction.commit();

        System.out.println("Student saved successfully!");
    }

    private static void readStudent(Session session, Scanner scanner) {
        System.out.print("Enter student ID to read: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Retrieve the student from the database
        Student student = session.get(Student.class, studentId);
        if (student != null) {
            System.out.println("Student details:");
            System.out.println(student);
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void updateStudent(Session session, Scanner scanner) {
        System.out.print("Enter student ID to update: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Retrieve the student from the database
        Student student = session.get(Student.class, studentId);
        if (student != null) {
            // Prompt user for updated details
            System.out.println("Enter updated details:");
            System.out.print("First Name: ");
            student.setFirstName(scanner.nextLine());
            System.out.print("Last Name: ");
            student.setLastName(scanner.nextLine());
            System.out.print("Age: ");
            student.setAge(scanner.nextInt());
            scanner.nextLine(); // Consume newline
            System.out.print("Email: ");
            student.setEmail(scanner.nextLine());

            // Begin transaction
            Transaction transaction = session.beginTransaction();

            // Update the student in the database
            session.update(student);

            // Commit transaction
            transaction.commit();

            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void deleteStudent(Session session, Scanner scanner) {
        System.out.print("Enter student ID to delete: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Retrieve the student from the database
        Student student = session.get(Student.class, studentId);
        if (student != null) {
            // Begin transaction
            Transaction transaction = session.beginTransaction();

            // Delete the student from the database
            session.delete(student);

            // Commit transaction
            transaction.commit();

            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }
}
