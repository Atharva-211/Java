package assignment2;

abstract class Vehicle {
    // Abstract method to be implemented by subclasses
    abstract void engine();
}

class Car extends Vehicle {
    @Override
    void engine() {
        System.out.println("Car has good engine");
    }
}

class Truck extends Vehicle {
    @Override
    void engine() {
        System.out.println("Truck has bad engine");
    }
}

public class VehicleDemo {
    public static void main(String[] args) {
        // Creating objects of Car and Truck
        Car myCar = new Car();
        Truck myTruck = new Truck();

        // Calling the engine method on both objects
        System.out.println("Details of Car:");
        myCar.engine();

        System.out.println("\nDetails of Truck:");
        myTruck.engine();
    }
}
