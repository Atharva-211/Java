package assignment4;

class AttendanceThread extends Thread {
	private String divisionName;
	private String[] students;

	public AttendanceThread(String divisionName, String... students) {
		this.divisionName = divisionName;
		this.students = students;
	}

	@Override
	public void run() {
		System.out.println("Attendance for " + divisionName + ":");
		for (String student : students) {
			System.out.println("- " + student);
		}
	}
}

class LateStudent implements Runnable {
	private String studentName;

	public LateStudent(String studentName) {
		this.studentName = studentName;
	}

	@Override
	public void run() {
		System.out.println(studentName + " is late.");
	}
}

public class Multithreading {
    public static void main(String[] args) {
        AttendanceThread class1Thread = new AttendanceThread("Class 1", "Student 1", "Student 2");
        class1Thread.start();

        AttendanceThread class2Thread = new AttendanceThread("Class 2", "Student 3", "Student 4");
        class2Thread.start();

        // Separate instances of LateStudent for each class
        for (String student : new String[]{"Student 3", "Student 2"}) {
            new Thread(new LateStudent(student)).start();
        }
    }
}