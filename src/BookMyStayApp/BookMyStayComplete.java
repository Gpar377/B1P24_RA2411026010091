package BookMyStayApp;

import BookMyStayApp.domain.Reservation;
import BookMyStayApp.exception.InvalidBookingException;
import BookMyStayApp.service.BookingService;
import BookMyStayApp.service.BookingQueue;
import BookMyStayApp.service.PersistenceService;
import BookMyStayApp.service.RoomInventory;

import java.util.LinkedList;
import java.util.Queue;

public class BookMyStayComplete {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Book My Stay - Complete System ===");

        // --- Initialize Services ---
        RoomInventory inventory = new RoomInventory();
        BookingQueue bookingQueue = new BookingQueue();
        BookingService bookingService = new BookingService(inventory);
        PersistenceService persistenceService = new PersistenceService();
        String persistenceFile = "bookmystay_complete_state.ser";

        // --- Restore Previous State (UC12) ---
        PersistenceService.State restoredState = persistenceService.restoreState(persistenceFile);
        inventory = restoredState.inventory;
        Queue<Reservation> restoredQueue = restoredState.bookingQueue;
        if (!restoredQueue.isEmpty()) {
            System.out.println("Restored pending bookings from previous session:");
            restoredQueue.forEach(Reservation::display);
            restoredQueue.forEach(bookingQueue::addRequest);
        }

        // --- UC9: Input Validation / Booking ---
        System.out.println("\n--- UC9: Booking Validation ---");
        try {
            Reservation r1 = new Reservation("Alice", "Single Room");
            validateBooking(r1, inventory);
            bookingQueue.addRequest(r1);

            Reservation r2 = new Reservation("Bob", "Single Room");
            validateBooking(r2, inventory);
            bookingQueue.addRequest(r2);

            Reservation r3 = new Reservation("Charlie", "Suite Room");
            validateBooking(r3, inventory);
            bookingQueue.addRequest(r3);

        } catch (InvalidBookingException e) {
            System.out.println("Booking Error: " + e.getMessage());
        }

        // --- UC11: Concurrent Booking Simulation ---
        System.out.println("\n--- UC11: Concurrent Booking Simulation ---");
        Runnable worker = () -> {
            while (!bookingQueue.isEmpty()) {
                Reservation res = bookingQueue.getNextRequest();
                if (res != null) bookingService.processBooking(res);
            }
        };

        Thread t1 = new Thread(worker);
        Thread t2 = new Thread(worker);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        inventory.displayInventory();

        // --- UC10: Cancellation & Rollback ---
        System.out.println("\n--- UC10: Booking Cancellation ---");
        // Simple demo: cancel one booking
        System.out.println("Cancelling a booking for Alice (Single Room)...");
        bookingService.cancelBooking("Alice", "Single Room", inventory);

        inventory.displayInventory();

        // --- UC12: Persist Current State ---
        System.out.println("\n--- UC12: Persisting State ---");
        Queue<Reservation> currentQueue = new LinkedList<>(); // Empty for demo
        persistenceService.saveState(inventory, currentQueue, persistenceFile);

        System.out.println("\n=== Complete System Simulation Finished ===");
    }

    // --- Helper Method: UC9 Validation ---
    private static void validateBooking(Reservation reservation, RoomInventory inventory) throws InvalidBookingException {
        String roomType = reservation.getRoomType();
        if (roomType == null || roomType.isEmpty()) {
            throw new InvalidBookingException("Room type cannot be empty.");
        }
        if (inventory.getAvailability(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for type: " + roomType);
        }
    }
}