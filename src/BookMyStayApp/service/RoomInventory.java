package BookMyStayApp.service;

import java.util.HashMap;
import java.util.Map;

public class RoomInventory {
    private final Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    // Thread-safe availability check
    public synchronized int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Thread-safe reduce inventory
    public synchronized boolean allocateRoom(String roomType) {
        int available = inventory.getOrDefault(roomType, 0);
        if (available > 0) {
            inventory.put(roomType, available - 1);
            return true;
        }
        return false;
    }

    public synchronized void displayInventory() {
        System.out.println("\n--- Inventory ---");
        inventory.forEach((type, count) -> System.out.println(type + " → " + count));
    }
    public synchronized void incrementRoom(String roomType) {
        int current = inventory.getOrDefault(roomType, 0);
        inventory.put(roomType, current + 1);
    }
}