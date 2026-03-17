package BookMyStayApp.usecase;

import BookMyStayApp.domain.Reservation;
import BookMyStayApp.service.BookingQueue;
import BookMyStayApp.service.PersistenceService;
import BookMyStayApp.service.RoomInventory;

import java.util.LinkedList;
import java.util.Queue;

public class UseCase12DataPersistenceRecovery {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay - Data Persistence & Recovery ===");

        PersistenceService persistence = new PersistenceService();
        String fileName = "bookmystay_state.ser";

        // Restore previous state or initialize
        PersistenceService.State state = persistence.restoreState(fileName);
        RoomInventory inventory = state.inventory;
        Queue<Reservation> queue = state.bookingQueue;

        // Add new booking request
        queue.add(new Reservation("Eve", "Single Room"));
        queue.add(new Reservation("Frank", "Suite Room"));

        // Save current state
        persistence.saveState(inventory, queue, fileName);

        System.out.println("=== Persistence & Recovery Simulation Complete ===");
    }
}