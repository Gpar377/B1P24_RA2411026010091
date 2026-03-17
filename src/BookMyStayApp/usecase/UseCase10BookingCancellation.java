package BookMyStayApp.usecase;

import BookMyStayApp.domain.Reservation;
import BookMyStayApp.exception.BookingNotFoundException;
import BookMyStayApp.service.BookingHistory;
import BookMyStayApp.service.BookingService;
import BookMyStayApp.service.CancellationService;
import BookMyStayApp.service.RoomInventory;

public class UseCase10BookingCancellation {

    public static void main(String[] args) {
        // Initialize core services
        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();
        CancellationService cancellationService = new CancellationService(inventory, history);
        BookingService bookingService = new BookingService(inventory, history);

        // Setup inventory
        inventory.addRoomType("Single", 2);
        inventory.addRoomType("Double", 1);

        // Add some bookings
        Reservation r1 = new Reservation("Alice", "Single", "S101");
        Reservation r2 = new Reservation("Bob", "Double", "D201");
        Reservation r3 = new Reservation("Charlie", "Single", "S102");

        bookingService.processBooking(r1);
        bookingService.processBooking(r2);
        bookingService.processBooking(r3);

        // Display current availability
        System.out.println("Before Cancellation:");
        System.out.println("Single rooms available: " + inventory.getAvailability("Single"));
        System.out.println("Double rooms available: " + inventory.getAvailability("Double"));
        System.out.println();

        // Cancel a booking
        try {
            boolean cancelled = cancellationService.cancelBooking("Alice", "Single");
            if (!cancelled) throw new BookingNotFoundException("Booking not found or already cancelled.");
            System.out.println("Cancelled Alice's Single room booking.");
        } catch (BookingNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Attempt to cancel a non-existent booking
        try {
            boolean cancelled = cancellationService.cancelBooking("David", "Double");
            if (!cancelled) throw new BookingNotFoundException("Booking not found or already cancelled.");
        } catch (BookingNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Display availability after cancellation
        System.out.println();
        System.out.println("After Cancellation:");
        System.out.println("Single rooms available: " + inventory.getAvailability("Single"));
        System.out.println("Double rooms available: " + inventory.getAvailability("Double"));

        // Display rollback stack
        System.out.println("\nRollback Stack (recently released rooms): " + cancellationService.getRollbackStack());
    }
}