package assignment2;

import java.time.LocalDate;

class Employee {
    // Private attributes
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
    public void setEmployeeId(int employeeId) {
        if (employeeId > 0) {
            this.employeeId = employeeId;
        } else {
            System.out.println("Invalid employee ID");
        }
    }

    public void setFirstName(String firstName) {
        // Add validation as needed
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        // Add validation as needed
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        // Add validation as needed
        this.dateOfBirth = dateOfBirth;
    }

    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        } else {
            System.out.println("Invalid salary");
        }
    }

    // Method to calculate yearly bonus
    public double calculateYearlyBonus() {
        // Example formula for bonus calculation
        return 0.1 * salary;
    }
}

public class EmployeeManagement {
    public static void main(String[] args) {
        // Create an employee
        Employee employee = new Employee(101, "John", "Doe", LocalDate.of(1990, 5, 15), 50000.0);

        // Display employee details
        System.out.println("Employee Details:");
        System.out.println("ID: " + employee.getEmployeeId());
        System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
        System.out.println("Date of Birth: " + employee.getDateOfBirth());
        System.out.println("Salary: $" + employee.getSalary());

        // Modify employee details using mutator methods
        employee.setSalary(55000.0);
        employee.setDateOfBirth(LocalDate.of(1991, 7, 20));

        // Display updated employee details
        System.out.println("\nUpdated Employee Details:");
        System.out.println("ID: " + employee.getEmployeeId());
        System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
        System.out.println("Date of Birth: " + employee.getDateOfBirth());
        System.out.println("Salary: $" + employee.getSalary());

        // Calculate and display yearly bonus
        System.out.println("\nYearly Bonus: $" + employee.calculateYearlyBonus());
    }
}
