package BookMyStayApp;

import java.util.HashMap;
import java.util.Map;

/**
 * Book My Stay Application
 *
 * Use Case 4: BookMyStayApp.Room Search & Availability Check
 *
 * This program demonstrates read-only access to inventory.
 * Guests can view available rooms without modifying system state.
 *
 * Application Name: Book My Stay
 * Version: 4.0
 *
 * @author YourName
 * @version 4.0
 */


// ===================== DOMAIN MODEL =====================

// Abstract BookMyStayApp.Room
abstract class Room {
    private String roomType;
    private int beds;
    private double price;

    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    public String getRoomType() { return roomType; }
    public int getBeds() { return beds; }
    public double getPrice() { return price; }

    public abstract void displayDetails();
}


// Concrete Rooms
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single BookMyStayApp.Room", 1, 2000);
    }

    public void displayDetails() {
        System.out.println("BookMyStayApp.Room: " + getRoomType() +
                " | Beds: " + getBeds() +
                " | Price: ₹" + getPrice());
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double BookMyStayApp.Room", 2, 3500);
    }

    public void displayDetails() {
        System.out.println("BookMyStayApp.Room: " + getRoomType() +
                " | Beds: " + getBeds() +
                " | Price: ₹" + getPrice());
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite BookMyStayApp.Room", 3, 6000);
    }

    public void displayDetails() {
        System.out.println("BookMyStayApp.Room: " + getRoomType() +
                " | Beds: " + getBeds() +
                " | Price: ₹" + getPrice());
    }
}


// ===================== INVENTORY =====================

class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single BookMyStayApp.Room", 5);
        inventory.put("Double BookMyStayApp.Room", 0); // intentionally 0 to test filtering
        inventory.put("Suite BookMyStayApp.Room", 2);
    }

    // Read-only access
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}


// ===================== SEARCH SERVICE =====================

class RoomSearchService {

    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void searchAvailableRooms(Room[] rooms) {

        System.out.println("\n--- Available Rooms ---\n");

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getRoomType());

            // Defensive check → only show available rooms
            if (available > 0) {
                room.displayDetails();
                System.out.println("Available: " + available);
                System.out.println("----------------------------------");
            }
        }
    }
}


// ===================== MAIN APPLICATION =====================

public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("     Welcome to Book My Stay App      ");
        System.out.println("======================================");
        System.out.println("Version: 4.0");
        System.out.println("--------------------------------------");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Initialize rooms (domain objects)
        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        // Search service (READ ONLY)
        RoomSearchService searchService = new RoomSearchService(inventory);

        // Perform search
        searchService.searchAvailableRooms(rooms);

        System.out.println("\nSearch completed. No data was modified.");
        System.out.println("======================================");
    }
}