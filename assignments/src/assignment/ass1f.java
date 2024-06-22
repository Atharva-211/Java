//
//6.	Develop an Employee Management System and demonstrate the concept of encapsulation. 
//	Create a class named Employee with private attributes for employeeId, firstName, lastName, dateOfBirth, 
//	and salary. Implement accessor methods for retrieving attribute values and mutator methods for modifying 
//	attributes, ensuring proper validation. Additionally, include a method calculateYearlyBonus() within the 
//	Employee class that utilizes encapsulated data to calculate a yearly bonus based on a predetermined formula.


package assignment;

import java.time.LocalDate;

class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private double salary;

    // Constructor
    public Employee(int employeeId, String firstName, String lastName, LocalDate dateOfBirth, double salary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
    }

    // Accessor methods
    public int getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public double getSalary() {
        return salary;
    }

    // Mutator methods with validation
    public void setFirstName(String firstName) {
        if (firstName != null && !firstName.trim().isEmpty()) {
            this.firstName = firstName;
        } else {
            System.out.println("Invalid first name");
        }
    }

    public void setLastName(String lastName) {
        if (lastName != null && !lastName.trim().isEmpty()) {
            this.lastName = lastName;
        } else {
            System.out.println("Invalid last name");
        }
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth != null) {
            this.dateOfBirth = dateOfBirth;
        } else {
            System.out.println("Invalid date of birth");
        }
    }

    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        } else {
            System.out.println("Invalid salary");
        }
    }

    // Method using encapsulated data to calculate yearly bonus
    public double calculateYearlyBonus() {
        // Example bonus calculation formula (you can modify it based on your requirements)
        return 0.1 * salary;
    }
}

public class ass1f {
    public static void main(String[] args) {
        // Creating an employee
        Employee employee = new Employee(1, "John", "Doe", LocalDate.of(1990, 5, 15), 50000);

        // Accessing employee details
        System.out.println("Employee Details:");
        System.out.println("ID: " + employee.getEmployeeId());
        System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
        System.out.println("Date of Birth: " + employee.getDateOfBirth());
        System.out.println("Salary: $" + employee.getSalary());

        // Modifying employee details
        employee.setFirstName("Jane");
        employee.setSalary(60000);

        // Accessing updated employee details
        System.out.println("\nUpdated Employee Details:");
        System.out.println("ID: " + employee.getEmployeeId());
        System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
        System.out.println("Date of Birth: " + employee.getDateOfBirth());
        System.out.println("Salary: $" + employee.getSalary());

        // Calculating and displaying yearly bonus
        double yearlyBonus = employee.calculateYearlyBonus();
        System.out.println("\nYearly Bonus: $" + yearlyBonus);
    }
}
