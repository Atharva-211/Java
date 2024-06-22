package assignment5;

import java.util.ArrayList;
import java.util.Scanner;

class CourseEnrollment {
    private String courseId;
    private String courseName;
    private ArrayList<String> studentsEnrolled;

    public CourseEnrollment(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.studentsEnrolled = new ArrayList<>();
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public ArrayList<String> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public void addStudent(String studentId, String studentName) {
        String entry = "Student ID: " + studentId + ", Student Name: " + studentName;
        studentsEnrolled.add(entry);
    }
}

public class CourseManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an ArrayList to store course details
        ArrayList<CourseEnrollment> courses = new ArrayList<>();

        // Take input for course details
        System.out.println("Enter Course Details:");
        System.out.print("Course ID: ");
        String courseId = scanner.nextLine();
        System.out.print("Course Name: ");
        String courseName = scanner.nextLine();

        // Create a new course object
        CourseEnrollment course = new CourseEnrollment(courseId, courseName);

        // Take input for student details
        System.out.println("Enter Student Details:");
        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Student Name: ");
        String studentName = scanner.nextLine();

        // Add the student to the course
        course.addStudent(studentId, studentName);

        // Add the course to the list of courses
        courses.add(course);

        // Display success message
        System.out.println("Entry added successfully!");

        // Optional: Display the enrolled students for each course
        for (CourseEnrollment enrolledCourse : courses) {
            System.out.println("\nCourse ID: " + enrolledCourse.getCourseId());
            System.out.println("Course Name: " + enrolledCourse.getCourseName());
            System.out.println("Students Enrolled:");
            for (String student : enrolledCourse.getStudentsEnrolled()) {
                System.out.println(student);
            }
        }

        // Close the scanner
        scanner.close();
    }
}
