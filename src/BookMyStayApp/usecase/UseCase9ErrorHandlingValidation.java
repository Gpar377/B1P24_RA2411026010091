package BookMyStayApp.usecase;

import BookMyStayApp.domain.Reservation;
import BookMyStayApp.service.BookingQueue;
import BookMyStayApp.service.BookingService;
import BookMyStayApp.service.RoomInventory;

public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println(" Book My Stay - Validation System ");
        System.out.println("======================================");

        RoomInventory inventory = new RoomInventory();
        BookingQueue queue = new BookingQueue();

        // Valid booking
        queue.addRequest(new Reservation("R1", "Alice", "Single Room"));

        // Invalid room type
        queue.addRequest(new Reservation("R2", "Bob", "Deluxe Room"));

        // Empty guest name
        queue.addRequest(new Reservation("R3", "", "Double Room"));

        // No availability case (simulate by exhausting)
        queue.addRequest(new Reservation("R4", "Charlie", "Suite Room"));
        queue.addRequest(new Reservation("R5", "David", "Suite Room")); // should fail

        BookingService service = new BookingService(inventory);
        service.processBookings(queue);

        System.out.println("\nSystem remained stable after handling errors.");
        System.out.println("======================================");
    }
}