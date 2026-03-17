/**
 * Book My Stay Application
 *
 * Use Case 2: Basic Room Types & Static Availability
 *
 * This program demonstrates object-oriented design using abstraction,
 * inheritance, encapsulation, and polymorphism. It models different
 * room types and displays their availability using static variables.
 *
 * Application Name: Book My Stay
 * Version: 2.0
 *
 * @author YourName
 * @version 2.0
 */

// ABSTRACT CLASS
abstract class Room {

    private String roomType;
    private int numberOfBeds;
    private double pricePerNight;

    // Constructor
    public Room(String roomType, int numberOfBeds, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.pricePerNight = pricePerNight;
    }

    // Getters (Encapsulation)
    public String getRoomType() {
        return roomType;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    // Abstract method
    public abstract void displayRoomDetails();
}


// SINGLE ROOM
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 2000.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Beds: " + getNumberOfBeds());
        System.out.println("Price per night: ₹" + getPricePerNight());
    }
}


// DOUBLE ROOM
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 3500.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Beds: " + getNumberOfBeds());
        System.out.println("Price per night: ₹" + getPricePerNight());
    }
}


// SUITE ROOM
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 6000.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Beds: " + getNumberOfBeds());
        System.out.println("Price per night: ₹" + getPricePerNight());
    }
}


// MAIN APPLICATION CLASS
public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("     Welcome to Book My Stay App      ");
        System.out.println("======================================");
        System.out.println("Version: 2.0");
        System.out.println("--------------------------------------");

        // Polymorphism (Parent reference)
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Static availability (NO data structures)
        int singleRoomAvailable = 5;
        int doubleRoomAvailable = 3;
        int suiteRoomAvailable = 2;

        // Display details
        System.out.println("\n--- Room Details & Availability ---\n");

        singleRoom.displayRoomDetails();
        System.out.println("Available: " + singleRoomAvailable);
        System.out.println("----------------------------------");

        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + doubleRoomAvailable);
        System.out.println("----------------------------------");

        suiteRoom.displayRoomDetails();
        System.out.println("Available: " + suiteRoomAvailable);
        System.out.println("----------------------------------");

        System.out.println("Application execution completed.");
        System.out.println("======================================");
    }
}