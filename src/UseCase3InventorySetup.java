import java.util.HashMap;
import java.util.Map;

/**
 * Book My Stay Application
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * This program demonstrates how HashMap is used to centralize
 * room availability, replacing scattered variables from previous use cases.
 *
 * Application Name: Book My Stay
 * Version: 3.0
 *
 * @author YourName
 * @version 3.0
 */

// INVENTORY CLASS (Single Source of Truth)
class RoomInventory {

    private Map<String, Integer> inventory;

    // Constructor → initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        // Register room types with availability
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Get availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability (controlled modification)
    public void updateAvailability(String roomType, int count) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, count);
        } else {
            System.out.println("Room type not found: " + roomType);
        }
    }

    // Display full inventory
    public void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " → Available: " + entry.getValue());
        }
        System.out.println("--------------------------------");
    }
}


// MAIN APPLICATION
public class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("     Welcome to Book My Stay App      ");
        System.out.println("======================================");
        System.out.println("Version: 3.0");
        System.out.println("--------------------------------------");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        inventory.displayInventory();

        // Simulate update
        System.out.println("\nUpdating availability for Single Room...\n");
        inventory.updateAvailability("Single Room", 4);

        // Display updated inventory
        inventory.displayInventory();

        System.out.println("Application execution completed.");
        System.out.println("======================================");
    }
}