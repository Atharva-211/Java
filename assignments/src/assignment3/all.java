package assignment3;


import java.util.Scanner;

//Custom exception for invalid guest number
class InvalidGuestNumberException extends Exception {
 public InvalidGuestNumberException(String message) {
     super(message);
 }
}

//Custom exception for invalid room number
class InvalidRoomNumberException extends Exception {
 public InvalidRoomNumberException(String message) {
     super(message);
 }
}

//Custom exception for null or empty guest name
class InvalidGuestNameException extends Exception {
 public InvalidGuestNameException(String message) {
     super(message);
 }
}

//Custom exception for null reservation date
class InvalidReservationDateException extends Exception {
 public InvalidReservationDateException(String message) {
     super(message);
 }
}

class Reservation {
 private String guestName;
 private int roomNumber;
 private int numberOfGuests;
 private String reservationDate;

 // Constructor to initialize Reservation
 public Reservation(String guestName, int roomNumber, int numberOfGuests, String reservationDate) throws InvalidGuestNameException, InvalidRoomNumberException, InvalidGuestNumberException, InvalidReservationDateException {
     if (guestName == null || guestName.isEmpty()) {
         throw new InvalidGuestNameException("Guest name cannot be null or empty.");
     }
     if (roomNumber <= 0) {
         throw new InvalidRoomNumberException("Room number must be positive.");
     }
     if (numberOfGuests <= 0) {
         throw new InvalidGuestNumberException("Number of guests must be positive.");
     }
     if (reservationDate == null) {
         throw new InvalidReservationDateException("Reservation date cannot be null.");
     }

     this.guestName = guestName;
     this.roomNumber = roomNumber;
     this.numberOfGuests = numberOfGuests;
     this.reservationDate = reservationDate;
 }

 // Method to display reservation details
 public void displayReservationDetails() {
     System.out.println("Reservation Details:");
     System.out.println("Guest Name: " + guestName);
     System.out.println("Room Number: " + roomNumber);
     System.out.println("Number of Guests: " + numberOfGuests);
     System.out.println("Reservation Date: " + reservationDate);
 }

public class all {

	 public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        Reservation reservation = null;

	        try {
	            // Input reservation details
	            System.out.print("Enter guest name: ");
	            String guestName = scanner.nextLine();
	            System.out.print("Enter room number: ");
	            int roomNumber = Integer.parseInt(scanner.nextLine());
	            System.out.print("Enter number of guests: ");
	            int numberOfGuests = Integer.parseInt(scanner.nextLine());
	            System.out.print("Enter reservation date (YYYY-MM-DD): ");
	            String reservationDate = scanner.nextLine();

	            // Create reservation instance
	            reservation = new Reservation(guestName, roomNumber, numberOfGuests, reservationDate);
	            reservation.displayReservationDetails();

	        } catch (InvalidGuestNameException e) {
	            System.out.println("Error: " + e.getMessage());
	        } catch (InvalidRoomNumberException e) {
	            System.out.println("Error: " + e.getMessage());
	        } catch (InvalidGuestNumberException e) {
	            System.out.println("Error: " + e.getMessage());
	        } catch (InvalidReservationDateException e) {
	            System.out.println("Error: " + e.getMessage());
	        } catch (Exception e) {
	            System.out.println("An unexpected error occurred: " + e.getMessage());
	        } finally {
	            // Cleanup operations
	            System.out.println("Cleaning up resources...");
	            scanner.close();
	            if (reservation != null) {
	                System.out.println("Reservation processing completed.");
	            } else {
	                System.out.println("No reservation was created.");
	            }
	        }
	    }
	}

}