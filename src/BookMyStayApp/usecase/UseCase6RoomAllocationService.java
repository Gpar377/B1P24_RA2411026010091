package BookMyStayApp.usecase;

import BookMyStayApp.domain.Reservation;
import BookMyStayApp.service.BookingQueue;
import BookMyStayApp.service.BookingService;
import BookMyStayApp.service.RoomInventory;

public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingQueue queue = new BookingQueue();

        queue.addRequest(new Reservation("", "Alice", "Single Room"));
        queue.addRequest(new Reservation("", "Bob", "Suite Room"));

        BookingService service = new BookingService(inventory);
        service.processBookings(queue);
    }
}