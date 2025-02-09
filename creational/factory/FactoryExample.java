package creational.factory;

// Step 1: Define Product Interface

interface Vehicle {
    void drive();
}

// Step 2: Create Concrete Products

class Car implements Vehicle {

    @Override
    public void drive() {
       
        System.out.println("driving a car");
    }
}

class Bike implements Vehicle {

    @Override
    public void drive() {
        System.out.println("driving a bike");
    }
}

// Step 3: Create Factory Class

class VehicleFactory {
    public static Vehicle getVehicle(String type) {
        if (type.equalsIgnoreCase("Car")) {
            return new Car();
        } else if (type.equalsIgnoreCase("Bike")) {
            return new Bike();
        }
        throw new IllegalArgumentException("Unknown vehicle type");
    }
}

public class FactoryExample {
    public static void main(String[] args) {
        Vehicle car = VehicleFactory.getVehicle("Car");
        car.drive();

        Vehicle bike = VehicleFactory.getVehicle("Bike");
        bike.drive();  
    }
}
