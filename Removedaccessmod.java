import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface RentalStructure {
    void displayVehicles();
    void bookVehicle();
    void addVehicle();
}

abstract class Vehicle {
    String company;
    String model;
    int year;
    int costPHour;
    boolean available;

    Vehicle(String company, String model, int year, int costPHour) {
        this.company = company;
        this.model = model;
        this.year = year;
        this.costPHour = costPHour;
        this.available = true;
    }

    String getCompany() {
        return company;
    }

    String getModel() {
        return model;
    }

    int getYear() {
        return year;
    }

    int getCostPHour() {
        return costPHour;
    }

    boolean isAvailable() {
        return available;
    }

    void setAvailable(boolean available) {
        this.available = available;
    }

    abstract void displayDetails();
}

class TwoWheeler extends Vehicle {
    TwoWheeler(String company, String model, int year, int costPHour) {
        super(company, model, year, costPHour);
    }

    void displayDetails() {
        System.out.println("Two Wheeler: " + getCompany() + " " + getModel() + " (" + getYear() + ")");
        System.out.println("Cost per Hour: Rs." + getCostPHour());
    }
}

class FourWheeler extends Vehicle {
    FourWheeler(String company, String model, int year, int costPHour) {
        super(company, model, year, costPHour);
    }

    void displayDetails() {
        System.out.println("Four Wheeler: " + getCompany() + " " + getModel() + " (" + getYear() + ")");
        System.out.println("Cost per Hour: Rs." + getCostPHour());
    }
}

class RentalSystem implements RentalStructure {
    List<Vehicle> availableVehicles;

    RentalSystem() {
        availableVehicles = new ArrayList<>();
        availableVehicles.add(new TwoWheeler("Harley", "Davidson", 2021, 500));
        availableVehicles.add(new TwoWheeler("RoyalEnfield", "Hunter", 2020, 150));
        availableVehicles.add(new FourWheeler("Maruti", "Swift", 2019, 200));
        availableVehicles.add(new FourWheeler("Hyundai", "i20", 2020, 250));
    }

    public void displayVehicles() {
        System.out.println("Available Vehicles:");
        System.out.println("---------------------------------");
        for (int i = 0; i < availableVehicles.size(); i++) {
            System.out.print((i + 1) + ".");
            availableVehicles.get(i).displayDetails();
            System.out.println("---------------------------------");
        }
    }

    public void bookVehicle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Available Vehicles:");
        displayVehicles();
        System.out.println("Enter the vehicle number to book: ");
        int vehicleNumber = scanner.nextInt();
        if (vehicleNumber < 1 || vehicleNumber > availableVehicles.size()) {
            System.out.println("Invalid vehicle number!");
            return;
        }
        Vehicle selectedVehicle = availableVehicles.get(vehicleNumber - 1);
        if (!selectedVehicle.isAvailable()) {
            System.out.println("This vehicle is not available for booking!");
            return;
        }
        System.out.println("Enter the number of hours for rent: ");
        int hours = scanner.nextInt();
        System.out.println("Enter your name: ");
        String name = scanner.next();
        System.out.println("Enter your mobile number: ");
        String mobile = scanner.next();
        System.out.println("Enter the pickup point: ");
        String pickupPoint = scanner.next();
        System.out.println("Enter the return point: ");
        String returnPoint = scanner.next();
        int totalCost = selectedVehicle.getCostPHour() * hours;
        System.out.println("---------------------------------\nVehicle Booked\n---------------------------------");
        System.out.println("Bill Details:\n---------------------------------");
        System.out.println("Name: " + name);
        System.out.println("Mobile: " + mobile);
        System.out.println("Pickup Point: " + pickupPoint);
        System.out.println("Return Point: " + returnPoint);
        System.out.println("Vehicle: " + selectedVehicle.getCompany() + " " + selectedVehicle.getModel() + " (" + selectedVehicle.getYear() + ")");
        System.out.println("Cost per Hour: Rs." + selectedVehicle.getCostPHour());
        System.out.println("Total Cost: Rs." + totalCost);
        System.out.println("---------------------------------");

        selectedVehicle.setAvailable(false);
    }

    public void addVehicle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------------------------\nAdding a new vehicle...\n---------------------------------");
        System.out.println("Enter vehicle type (1 for Two Wheeler, 2 for Four Wheeler): ");
        int vehicleType = scanner.nextInt();
        System.out.println("Enter company: ");
        String company = scanner.next();
        System.out.println("Enter model: ");
        String model = scanner.next();
        System.out.println("Enter year: ");
        int year = scanner.nextInt();
        System.out.println("Enter cost per hour: ");
        int costPHour = scanner.nextInt();
        if (vehicleType == 1) {
            availableVehicles.add(new TwoWheeler(company, model, year, costPHour));
        } else if (vehicleType == 2) {
            availableVehicles.add(new FourWheeler(company, model, year, costPHour));
        } else {
            System.out.println("Invalid vehicle type!");
        }
        System.out.println("---------------------------------\nNew vehicle added successfully!\n---------------------------------");

    }
}

public class Main {
    public static void main(String[] args) {
    RentalSystem obj = new RentalSystem();
    Scanner scanner = new Scanner(System.in);
    int choice;
    do {
        System.out.println("---------------------------------");
        System.out.println("---------------------------------");
        System.out.println("BandiLo-The Vehicle RentalSystem");
        System.out.println("---------------------------------");
        System.out.println("1. Display available vehicles");
        System.out.println("2. Book vehicle");
        System.out.println("3. Add new vehicle");
        System.out.println("4. Exit");
        System.out.println("---------------------------------");
        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                obj.displayVehicles();
                break;
            case 2:
                obj.bookVehicle();
                break;
            case 3:
                obj.addVehicle();
                break;
            case 4:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    } while (choice != 4);
    scanner.close();
}
}
