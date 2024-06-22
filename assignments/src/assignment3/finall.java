package assignment3;


import java.util.Date;

public class finall {
    private String guestName;
    private int numberOfGuests;
    private Date reservationDate;

    // Constructor
    public finall(String guestName, int numberOfGuests, Date reservationDate) {
        try {
            if (numberOfGuests < 0) {
                throw new IllegalArgumentException("Number of guests cannot be negative.");
            }
            if (guestName == null || guestName.isEmpty()) {
                throw new IllegalArgumentException("Guest name cannot be null or empty.");
            }
            if (reservationDate == null) {
                throw new NullPointerException("Reservation date cannot be null.");
            }

            this.guestName = guestName;
            this.numberOfGuests = numberOfGuests;
            this.reservationDate = reservationDate;
        } catch (IllegalArgumentException e) {
            System.err.println("Error initializing reservation: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Error initializing reservation: " + e.getMessage());
        } finally {
            // Any cleanup operations can be done here
            // In this example, there are no specific cleanup operations to perform
        }
    }

    // Method to print reservation details
    public void printReservationDetails() {
        try {
            System.out.println("Reservation Details:");
            System.out.println("Guest Name: " + guestName);
            System.out.println("Number of Guests: " + numberOfGuests);
            System.out.println("Reservation Date: " + reservationDate);
        } catch (NullPointerException e) {
            System.err.println("Error displaying reservation details: " + e.getMessage());
        } finally {
        	cleanup();
        }
    }

    private void cleanup() {
    	System.out.println("Cleanup operations completed.");
		
	}

	public static void main(String[] args) {
        // Example usage
        Date today = new Date(); // Current date

        // Example 1: Negative number of guests
        finall reservation1 = null;
        try {
            reservation1 = new finall("John", -2, today);
        } finally {
            if (reservation1 != null) {
                reservation1.printReservationDetails();
            }
        }

        // Example 2: Null guest name
        finall reservation2 = null;
        try {
            reservation2 = new finall(null, 3, today);
        } finally {
            if (reservation2 != null) {
                reservation2.printReservationDetails();
            }
        }

        // Example 3: Null reservation date
        finall reservation3 = null;
        try {
            reservation3 = new finall("hello", 2, null);
        } finally {
            if (reservation3 != null) {
                reservation3.printReservationDetails();
            }
        }
    }
}
