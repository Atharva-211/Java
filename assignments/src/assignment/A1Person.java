//2.	Create a base class Person with attributes name and age. Include a method 
//	display_info() to print basic information about the person.
//	
//a.	Single Inheritance:
//		Derive a class Student from the Person class with additional attributes student_id and major. 
//		Include a method enroll_course() to simulate a student enrolling in a course.
//	
//b.	Multilevel Inheritance:
//		Extend the Student class to create a new class GraduateStudent with an additional attribute research_topic. 
//		Implement a method conduct_research() to simulate a graduate student conducting research.
//	
//c.	Hierarchical Inheritance:
//		Derive a class Professor from the Person class with attributes like employee_id, department, and teaching_subject. 
//		Implement a method conduct_lecture() to simulate a professor conducting a lecture.
//	
//d.	Hybrid Inheritance:
//		Create a class TeachingAssistant that inherits from both GraduateStudent and Professor. 
//		Include an attribute ta_id and a method assist_professor() to simulate a teaching assistant assisting a professor.


package assignment;

// Base class Person
class Person {
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

// Single Inheritance: Student class
class Student extends Person {
    private int studentId;
    private String major;

    public Student(String name, int age, int studentId, String major) {
        super(name, age);
        this.studentId = studentId;
        this.major = major;
    }

    public void enrollCourse() {
        System.out.println(name + " is enrolling in a course.");
    }
}

// Multilevel Inheritance: GraduateStudent class
class GraduateStudent extends Student {
    private String researchTopic;

    public GraduateStudent(String name, int age, int studentId, String major, String researchTopic) {
        super(name, age, studentId, major);
        this.researchTopic = researchTopic;
    }

    public void conductResearch() {
        System.out.println(name + " is conducting research on " + researchTopic);
    }
}

// Hierarchical Inheritance: Professor class
class Professor extends Person {
    private int employeeId;
    private String department;
    private String teachingSubject;

    public Professor(String name, int age, int employeeId, String department, String teachingSubject) {
        super(name, age);
        this.employeeId = employeeId;
        this.department = department;
        this.teachingSubject = teachingSubject;
    }

    public void conductLecture() {
        System.out.println(name + " is conducting a lecture on " + teachingSubject);
    }
}

// Hybrid Inheritance: TeachingAssistant class
class TeachingAssistant extends GraduateStudent {
    private int taId;

    public TeachingAssistant(String name, int age, int studentId, String major, String researchTopic, int taId) {
        super(name, age, studentId, major, researchTopic);
        this.taId = taId;
    }

    public void assistProfessor() {
        System.out.println(name + " is assisting the professor.");
    }
}

public class A1Person {
    public static void main(String[] args) {
        // Single Inheritance
        Student student = new Student("John", 20, 123, "Computer Science");
        student.displayInfo();
        student.enrollCourse();

        System.out.println("---------------------------");

        // Multilevel Inheritance
        GraduateStudent gradStudent = new GraduateStudent("Alice", 25, 456, "Physics", "Quantum Mechanics");
        gradStudent.displayInfo();
        gradStudent.enrollCourse();
        gradStudent.conductResearch();

        System.out.println("---------------------------");

        // Hierarchical Inheritance
        Professor professor = new Professor("Dr. Smith", 40, 789, "Mathematics", "Algebra");
        professor.displayInfo();
        professor.conductLecture();

        System.out.println("---------------------------");

        // Hybrid Inheritance
        TeachingAssistant ta = new TeachingAssistant("Bob", 22, 789, "Physics", "Optics", 567);
        ta.displayInfo();
        ta.enrollCourse();
        ta.conductResearch();
        ta.assistProfessor();
    }
}
