package Assignment1;


interface CardioWorkout {
	String getWorkoutName();
	int getDurationMinutes();
	void displayWorkoutDetails();
}

interface StrengthTraining {
	int getEquipmentUsed();
	String getInstructorName();
	void displayStrengthTrainingDetails();
}

class FullBodyWorkout implements CardioWorkout, StrengthTraining{
	private String workoutName;
	private int workoutDuration;
	private int equipmentUsed;
	private String instructorName;
	
	public FullBodyWorkout(String workoutName, int workoutDuration, int equipmentUsed, String instructorName) {
		this.workoutName=workoutName;
		this.workoutDuration=workoutDuration;
		this.equipmentUsed=equipmentUsed;
		this.instructorName=instructorName;
	}
	
	@Override
	public String getWorkoutName() {
		return workoutName;
	}
	
	@Override
	public int getDurationMinutes() {
		return workoutDuration;
	}
	
	@Override
	public void displayWorkoutDetails() {
		System.out.println("Workout Details");
		System.out.println("workoutName : "+workoutName);
		System.out.println("workoutDuration : "+workoutDuration);
	}
	
	
	@Override
	public int getEquipmentUsed() {
		return equipmentUsed;
	}
	
	@Override
	public String getInstructorName() {
		return instructorName;
	}
	
	@Override
	public void displayStrengthTrainingDetails() {
		System.out.println("Strength Details");
		System.out.println("equipmentUsed : "+equipmentUsed);
		System.out.println("instructorName : "+instructorName);
	}
	
	

	public void DisplayFullbodyDetails() {
		displayWorkoutDetails();
		displayStrengthTrainingDetails();
	}
}

public class interfac {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FullBodyWorkout fullBodyWorkout = new FullBodyWorkout("mussle" , 60, 3, "atharva");
		fullBodyWorkout.DisplayFullbodyDetails();
		

	}

}
