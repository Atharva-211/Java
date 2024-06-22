package assignment6;

import java.util.ArrayList;
import java.util.Scanner;

class Course {
    private String courseId;
    private String courseName;
    private ArrayList<String> studentNames;
    private ArrayList<String> studentIds;

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.studentNames = new ArrayList<>();
        this.studentIds = new ArrayList<>();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ArrayList<String> getStudentNames() {
        return studentNames;
    }

    public ArrayList<String> getStudentIds() {
        return studentIds;
    }
}

public class Collections {
    private ArrayList<Course> courses;

    public Collections() {
        this.courses = new ArrayList<>();
    }

    public void addCourseEntry(String courseId, String courseName, String studentId, String studentName) {
        Course course = new Course(courseId, courseName);
        course.getStudentIds().add(studentId);
        course.getStudentNames().add(studentName);
        courses.add(course);
        System.out.println("Entry added successfully.");
    }

    public void displayCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses enrolled yet.");
        } else {
            System.out.println("Courses:");
            for (Course course : courses) {
                System.out.println("Course ID: " + course.getCourseId());
                System.out.println("Course Name: " + course.getCourseName());
                System.out.println("Students Enrolled: " + course.getStudentNames().size());
                System.out.println("Student Names: " + course.getStudentNames());
                System.out.println("Student IDs: " + course.getStudentIds());
                System.out.println();
            }
        }
    }

    public boolean containsCourse(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                return true;
            }
        }
        return false;
    }

    public boolean removeCourse(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                courses.remove(course);
                return true;
            }
        }
        return false;
    }

    public boolean clearCourses() {
        if (!courses.isEmpty()) {
            courses.clear();
            return true;
        }
        return false;
    }

    public int getCoursesCount() {
        return courses.size();
    }

    public static void main(String[] args) {
        Collections manager = new Collections();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Course Entry");
            System.out.println("2. Display Courses");
            System.out.println("3. Check if course exists");
            System.out.println("4. Remove Course");
            System.out.println("5. Clear All Courses");
            System.out.println("6. Get number of courses");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter course details:");
                    System.out.print("Course ID: ");
                    String courseId = scanner.nextLine();
                    if (manager.containsCourse(courseId)) {
                        System.out.println("Course already exists.");
                        break;
                    }
                    System.out.print("Course Name: ");
                    String courseName = scanner.nextLine();
                    System.out.println("Enter student details:");
                    System.out.print("Student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Student Name: ");
                    String studentName = scanner.nextLine();
                    manager.addCourseEntry(courseId, courseName, studentId, studentName);
                    break;
                case 2:
                    manager.displayCourses();
                    break;
                case 3:
                    System.out.print("Enter course ID to check: ");
                    String checkCourseId = scanner.nextLine();
                    if (manager.containsCourse(checkCourseId)) {
                        System.out.println("Course exists.");
                    } else {
                        System.out.println("Course does not exist.");
                    }
                    break;
                case 4:
                    System.out.print("Enter course ID to remove: ");
                    String removeCourseId = scanner.nextLine();
                    if (manager.removeCourse(removeCourseId)) {
                        System.out.println("Course removed successfully.");
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 5:
                    if (manager.clearCourses()) {
                        System.out.println("All courses cleared successfully.");
                    } else {
                        System.out.println("No courses to clear.");
                    }
                    break;
                case 6:
                    System.out.println("Number of courses: " + manager.getCoursesCount());
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        }
    }
}
