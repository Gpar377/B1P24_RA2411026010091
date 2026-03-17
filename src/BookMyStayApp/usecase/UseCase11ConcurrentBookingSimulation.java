package BookMyStayApp.usecase;

import BookMyStayApp.domain.Reservation;
import BookMyStayApp.service.BookingQueue;
import BookMyStayApp.service.BookingService;
import BookMyStayApp.service.RoomInventory;

public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Book My Stay - Concurrent Booking Simulation ===");

        RoomInventory inventory = new RoomInventory();
        BookingQueue queue = new BookingQueue();
        BookingService service = new BookingService(inventory);

        // Add booking requests
        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Single Room"));
        queue.addRequest(new Reservation("Charlie", "Suite Room"));
        queue.addRequest(new Reservation("David", "Double Room"));

        // Simulate multi-threaded processing
        Runnable worker = () -> {
            while (!queue.isEmpty()) {
                Reservation res = queue.getNextRequest();
                if (res != null) {
                    service.processBooking(res);
                }
            }
        };

        Thread t1 = new Thread(worker);
        Thread t2 = new Thread(worker);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        inventory.displayInventory();

        System.out.println("=== Simulation Complete ===");
    }
}