package assignment3;

import java.util.Scanner;

public class tryCatch {
    private String guestName;
    private int numberOfGuests;
    private String reservationDate;

    // Constructor to initialize the Reservation object
    public tryCatch(String guestName, int numberOfGuests, String reservationDate) {
        this.guestName = guestName;
        this.numberOfGuests = numberOfGuests;
        this.reservationDate = reservationDate;
    }

    // Method to input reservation details from the user
    public void inputReservationDetails() {
        Scanner scanner = new Scanner(System.in);

        // Input guest name
        System.out.print("Enter guest name: ");
        guestName = scanner.nextLine();

        // Input number of guests
        try {
            System.out.print("Enter number of guests: ");
            numberOfGuests = Integer.parseInt(scanner.nextLine());

            // Check for negative number of guests
            if (numberOfGuests < 0) {
                throw new IllegalArgumentException("Number of guests cannot be negative.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for number of guests. Please enter a valid integer.");
            return;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Input reservation date
        System.out.print("Enter reservation date: ");
        reservationDate = scanner.nextLine();

        // Check for null reservation date
        try {
            if (reservationDate.trim().isEmpty()) {
                throw new IllegalArgumentException("Reservation date cannot be empty.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Reservation details have been successfully entered.");
    }

    // Method to print reservation details
    public void printReservationDetails() {
        System.out.println("Reservation Details:");
        System.out.println("Guest Name: " + guestName);
        System.out.println("Number of Guests: " + numberOfGuests);
        System.out.println("Reservation Date: " + reservationDate);
    }

    public static void main(String[] args) {
        // Create a Reservation object
        tryCatch reservation = new tryCatch("", 0, "");

        // Input reservation details
        reservation.inputReservationDetails();

        // Print reservation details
        reservation.printReservationDetails();
    }
}
