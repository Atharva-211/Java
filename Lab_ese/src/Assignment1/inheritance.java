package Assignment1;


class Person{
	String name;
	int age;
	
	public Person(String name, int age) {
		this.name=name;
		this.age=age;
	}
	
	public void displayinfo() {
		System.out.println("name : "+ name);
		System.out.println("age : "+age);
	}
}

class Student extends Person{
	int student_id;
	String major;
	
	public Student(String name, int age, int student_id, String major) {
		super(name,age);
		this.student_id=student_id;
		this.major=major;
	}
	
	public void enroll_course(String course)  {
		System.out.println("course : "+ course);
	}
}

class GraduateStudent extends Student{
	String research_topic;
	
	public GraduateStudent(String name, int age, int student_id, String major,String research_topic) {
		super(name,age,student_id,major);
		this.research_topic=research_topic;
	}
	
	public void conduct_research()  {
		System.out.println("research : "+ research_topic);
	}
}

class Professor extends Person {
	int employee_id;
	String department;
	String teaching_subject;
	
	public Professor(String name, int age, int employee_id, String department, String teaching_subject) {
		super(name,age);
		this.employee_id=employee_id;
		this.department=department;
		this.teaching_subject=teaching_subject;
	}
	
	public void conduct_lecture()  {
		System.out.println("conduct_lecture : "+ teaching_subject);
	}
}

public class inheritance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Student student = new Student("atharva", 20 ,73, "cs");
		student.displayinfo();
		student.enroll_course("Java");
		
		GraduateStudent graduateStudent = new GraduateStudent("gaikwad", 30 ,23, "aa", "python");
		graduateStudent.displayinfo();
		graduateStudent.conduct_research();
		
		Professor professor = new Professor("hii", 380 ,283, "uuu", "matha");
		professor.displayinfo();
		professor.conduct_lecture();

	}

}
