package BookMyStayApp.service;

import BookMyStayApp.domain.Reservation;
import java.util.LinkedList;
import java.util.Queue;

public class BookingQueue {
    private final Queue<Reservation> queue = new LinkedList<>();

    // Thread-safe add
    public synchronized void addRequest(Reservation reservation) {
        queue.add(reservation);
        System.out.println("Booking request added for " + reservation.getGuestName());
    }

    // Thread-safe retrieve
    public synchronized Reservation getNextRequest() {
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}