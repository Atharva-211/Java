package Assignment3;
import java.time.LocalDate;
import java.util.Scanner;

public class Reservation {
    // Private attributes
    private String reservationId;
    private String guestName;
    private int numberOfGuests;
    private LocalDate reservationDate;
    private int roomNumber;

    // Constructor
    public Reservation(String reservationId, String guestName, int numberOfGuests, LocalDate reservationDate, int roomNumber) {
        try {
            setReservationId(reservationId);
            setGuestName(guestName);
            setNumberOfGuests(numberOfGuests);
            setReservationDate(reservationDate);
            setRoomNumber(roomNumber);
        } catch (IllegalArgumentException e) {
            System.out.println("Error during reservation initialization: " + e.getMessage());
        } finally {
            cleanup();
        }
    }

    // Accessor methods (Getters)
    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    // Mutator methods (Setters) with validation
    public void setReservationId(String reservationId) {
        if (reservationId != null && !reservationId.trim().isEmpty()) {
            this.reservationId = reservationId;
        } else {
            throw new IllegalArgumentException("Reservation ID cannot be null or empty.");
        }
    }

    public void setGuestName(String guestName) {
        if (guestName != null && !guestName.trim().isEmpty()) {
            this.guestName = guestName;
        } else {
            throw new IllegalArgumentException("Guest name cannot be null or empty.");
        }
    }

    public void setNumberOfGuests(int numberOfGuests) {
        if (numberOfGuests > 0) {
            this.numberOfGuests = numberOfGuests;
        } else {
            throw new IllegalArgumentException("Number of guests must be positive.");
        }
    }

    public void setReservationDate(LocalDate reservationDate) {
        if (reservationDate != null) {
            this.reservationDate = reservationDate;
        } else {
            throw new IllegalArgumentException("Reservation date cannot be null.");
        }
    }

    public void setRoomNumber(int roomNumber) {
        if (roomNumber > 0) {
            this.roomNumber = roomNumber;
        } else {
            throw new IllegalArgumentException("Room number must be positive.");
        }
    }

    // Method to display reservation details
    public void displayReservationDetails() {
        try {
            System.out.println("Reservation ID: " + getReservationId());
            System.out.println("Guest Name: " + getGuestName());
            System.out.println("Number of Guests: " + getNumberOfGuests());
            System.out.println("Reservation Date: " + getReservationDate());
            System.out.println("Room Number: " + getRoomNumber());
        } catch (Exception e) {
            System.out.println("Error displaying reservation details: " + e.getMessage());
        } finally {
            cleanup();
        }
    }

    // Method to cleanup resources
    private void cleanup() {
        System.out.println("Cleanup operations completed.");
    }

    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Example of valid reservation
        try {
            System.out.println("Enter reservation ID: ");
            String reservationId = scanner.nextLine();

            System.out.println("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.println("Enter number of guests: ");
            int numberOfGuests = scanner.nextInt();

            System.out.println("Enter room number: ");
            int roomNumber = scanner.nextInt();

            System.out.println("Enter reservation date (YYYY-MM-DD): ");
            String dateInput = scanner.next();
            LocalDate reservationDate = LocalDate.parse(dateInput);

            Reservation reservation = new Reservation(reservationId, guestName, numberOfGuests, reservationDate, roomNumber);
            reservation.displayReservationDetails();
        } catch (Exception e) {
            System.out.println("Error during reservation initialization: " + e.getMessage());
        } finally {
            System.out.println("Initialization attempt completed.");
        }

        // Example of invalid reservation
        try {
            Reservation invalidReservation = new Reservation("R002", "", -1, null, -1);
            invalidReservation.displayReservationDetails();
        } catch (Exception e) {
            System.out.println("Error during reservation initialization: " + e.getMessage());
        } finally {
            System.out.println("Initialization attempt completed.");
        }

        // Close the scanner
        scanner.close();
    }
}
