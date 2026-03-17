import java.util.*;

/**
 * Book My Stay Application
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * This program processes booking requests from a queue,
 * assigns unique room IDs, prevents double-booking using Set,
 * and updates inventory in real-time.
 *
 * Version: 6.0
 *
 * @author YourName
 * @version 6.0
 */


// ===================== RESERVATION =====================

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
}


// ===================== INVENTORY =====================

class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void reduceAvailability(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    public void displayInventory() {
        System.out.println("\n--- Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }
}


// ===================== BOOKING QUEUE =====================

class BookingQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.add(r);
    }

    public Reservation getNextRequest() {
        return queue.poll(); // FIFO
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}


// ===================== BOOKING SERVICE =====================

class BookingService {

    private RoomInventory inventory;

    // Map → Room Type → Set of Room IDs
    private Map<String, Set<String>> allocatedRooms = new HashMap<>();

    // Global Set → Prevent duplicates across system
    private Set<String> allAllocatedRoomIds = new HashSet<>();

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    // Process queue
    public void processBookings(BookingQueue queue) {

        System.out.println("\n--- Processing Bookings ---");

        while (!queue.isEmpty()) {

            Reservation request = queue.getNextRequest();
            String roomType = request.getRoomType();

            // Check availability
            if (inventory.getAvailability(roomType) > 0) {

                // Generate unique room ID
                String roomId = generateRoomId(roomType);

                // Ensure uniqueness
                if (!allAllocatedRoomIds.contains(roomId)) {

                    // Store globally
                    allAllocatedRoomIds.add(roomId);

                    // Store per room type
                    allocatedRooms
                            .computeIfAbsent(roomType, k -> new HashSet<>())
                            .add(roomId);

                    // Update inventory (atomic step)
                    inventory.reduceAvailability(roomType);

                    // Confirm booking
                    System.out.println("Booking Confirmed → Guest: "
                            + request.getGuestName()
                            + " | Room Type: " + roomType
                            + " | Room ID: " + roomId);

                } else {
                    System.out.println("Duplicate Room ID detected! Skipping...");
                }

            } else {
                System.out.println("Booking Failed → No availability for "
                        + roomType + " (Guest: " + request.getGuestName() + ")");
            }
        }
    }

    // Generate Room ID
    private String generateRoomId(String roomType) {
        return roomType.substring(0, 2).toUpperCase() + "-" + UUID.randomUUID().toString().substring(0, 4);
    }
}


// ===================== MAIN APPLICATION =====================

public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("     Book My Stay - Booking Engine    ");
        System.out.println("======================================");
        System.out.println("Version: 6.0");
        System.out.println("--------------------------------------");

        // Initialize components
        RoomInventory inventory = new RoomInventory();
        BookingQueue queue = new BookingQueue();

        // Add booking requests
        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Single Room"));
        queue.addRequest(new Reservation("Charlie", "Single Room")); // should fail
        queue.addRequest(new Reservation("David", "Suite Room"));

        // Process bookings
        BookingService service = new BookingService(inventory);
        service.processBookings(queue);

        // Final inventory state
        inventory.displayInventory();

        System.out.println("\nAll bookings processed.");
        System.out.println("======================================");
    }
}