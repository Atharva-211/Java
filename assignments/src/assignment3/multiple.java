package assignment3;

import java.util.Scanner;

public class multiple {
    private String guestName;
    private int numberOfGuests;
    private String reservationDate;

    // Constructor to initialize the Reservation object
    public multiple(String guestName, int numberOfGuests, String reservationDate) {
        this.guestName = guestName;
        this.numberOfGuests = numberOfGuests;
        this.reservationDate = reservationDate;
    }

    // Method to input reservation details from the user
    public void inputReservationDetails() {
        Scanner scanner = new Scanner(System.in);

        try {
            // Input guest name
            System.out.print("Enter guest name: ");
            guestName = scanner.nextLine();

            // Check for null guest name
            if (guestName.trim().isEmpty()) {
                throw new IllegalArgumentException("Guest name cannot be empty.");
            }

            // Input number of guests
            System.out.print("Enter number of guests: ");
            numberOfGuests = Integer.parseInt(scanner.nextLine());

            // Check for negative number of guests
            if (numberOfGuests < 0) {
                throw new IllegalArgumentException("Number of guests cannot be negative.");
            }

            // Input reservation date
            System.out.print("Enter reservation date: ");
            reservationDate = scanner.nextLine();

            // Check for null reservation date
            if (reservationDate.trim().isEmpty()) {
                throw new IllegalArgumentException("Reservation date cannot be empty.");
            }

            System.out.println("Reservation details have been successfully entered.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for number of guests. Please enter a valid integer.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
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
        multiple reservation = new multiple("", 0, "");

        // Input reservation details
        reservation.inputReservationDetails();

        // Print reservation details
        reservation.printReservationDetails();
    }
}
