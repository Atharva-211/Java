//3.	Multiple inheritance using Interface:
//	Define two interfaces: CardioWorkout and StrengthTraining.
//
//	a.	 In the CardioWorkout interface, declare methods such as getWorkoutName(), 
//			getDurationMinutes(), and displayWorkoutDetails().
//
//	b.	In the StrengthTraining interface, declare methods like getEquipmentUsed(), getInstructorName(), 
//		and displayStrengthTrainingDetails().
//
//Derived Class:
//Create a class named FullBodyWorkout that implements both Cardio_Workout and StrengthTraining. 
//This class represents a workout program that combines cardio exercises with strength training for 
//a comprehensive full-body workout.


package assignment;

interface CardioWorkout {
    String getWorkoutName();
    int getDurationMinutes();
    void displayWorkoutDetails();
}

interface StrengthTraining {
    String getEquipmentUsed();
    String getInstructorName();
    void displayStrengthTrainingDetails();
}

class FullBodyWorkout implements CardioWorkout, StrengthTraining {
    private String workoutName;
    private int durationMinutes;
    private String equipmentUsed;
    private String instructorName;

    public FullBodyWorkout(String workoutName, int durationMinutes, String equipmentUsed, String instructorName) {
        this.workoutName = workoutName;
        this.durationMinutes = durationMinutes;
        this.equipmentUsed = equipmentUsed;
        this.instructorName = instructorName;
    }

    @Override
    public String getWorkoutName() {
        return workoutName;
    }

    @Override
    public int getDurationMinutes() {
        return durationMinutes;
    }

    @Override
    public void displayWorkoutDetails() {
        System.out.println("Cardio Workout Details:");
        System.out.println("Workout Name: " + getWorkoutName());
        System.out.println("Duration: " + getDurationMinutes() + " minutes");
    }

    @Override
    public String getEquipmentUsed() {
        return equipmentUsed;
    }

    @Override
    public String getInstructorName() {
        return instructorName;
    }

    @Override
    public void displayStrengthTrainingDetails() {
        System.out.println("Strength Training Details:");
        System.out.println("Equipment Used: " + getEquipmentUsed());
        System.out.println("Instructor: " + getInstructorName());
    }

    public void displayFullBodyWorkoutDetails() {
        System.out.println("Full Body Workout Details:");
        displayWorkoutDetails();
        displayStrengthTrainingDetails();
    }
}

public class ass1c {
    public static void main(String[] args) {
        // Example usage
        FullBodyWorkout fullBodyWorkout = new FullBodyWorkout("Cardio & Strength", 60, "Dumbbells", "John Doe");

        fullBodyWorkout.displayFullBodyWorkoutDetails();
    }
}
