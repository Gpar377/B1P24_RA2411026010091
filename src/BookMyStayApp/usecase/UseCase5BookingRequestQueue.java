package BookMyStayApp.usecase;

import BookMyStayApp.domain.Reservation;
import BookMyStayApp.service.BookingQueue;

public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        BookingQueue queue = new BookingQueue();

        queue.addRequest(new Reservation("", "Alice", "Single Room"));
        queue.addRequest(new Reservation("", "Bob", "Double Room"));

        System.out.println("Requests added to queue.");
    }
}