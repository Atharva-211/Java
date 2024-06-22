//5.	Write a Java program in which you will declare an abstract class 
//	Vehicle inherits this class from two classes car and truck using the method engine in both displays 
//	“car has good engine” and “truck has bad engine”.

package assignment;

abstract class Vehicle {
    // Abstract method to be implemented by subclasses
    public abstract void engine();
}

class Car extends Vehicle {
    // Implementing the engine method for Car
    @Override
    public void engine() {
        System.out.println("Car has good engine");
    }
}

class Truck extends Vehicle {
    // Implementing the engine method for Truck
    @Override
    public void engine() {
        System.out.println("Truck has bad engine");
    }
}

public class ass1e {
    public static void main(String[] args) {
        // Instantiate objects of Car and Truck
        Car myCar = new Car();
        Truck myTruck = new Truck();

        // Invoke the engine method on Car and Truck objects
        myCar.engine();
        myTruck.engine();
    }
}
