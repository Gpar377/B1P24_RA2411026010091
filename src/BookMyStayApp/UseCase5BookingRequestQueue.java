package BookMyStayApp;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Book My Stay Application
 *
 * Use Case 5: Booking Request Queue (FIFO)
 *
 * This program demonstrates how booking requests are collected
 * and stored in a queue to ensure fair processing using FIFO.
 *
 * Application Name: Book My Stay
 * Version: 5.0
 *
 * @author YourName
 * @version 5.0
 */


// ===================== RESERVATION =====================

class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void display() {
        System.out.println("Guest: " + guestName + " | Requested BookMyStayApp.Room: " + roomType);
    }
}


// ===================== BOOKING QUEUE =====================

class BookingQueue {

    private Queue<Reservation> queue;

    public BookingQueue() {
        queue = new LinkedList<>();
    }

    // Add booking request
    public void addRequest(Reservation reservation) {
        queue.add(reservation);
        System.out.println("Booking request added for " + reservation.getGuestName());
    }

    // Display queue (FIFO order)
    public void displayQueue() {

        System.out.println("\n--- Booking Request Queue (FIFO) ---\n");

        for (Reservation r : queue) {
            r.display();
        }

        System.out.println("------------------------------------");
    }

    // Get queue (for future UC)
    public Queue<Reservation> getQueue() {
        return queue;
    }
}


// ===================== MAIN APPLICATION =====================

public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("     Welcome to Book My Stay App      ");
        System.out.println("======================================");
        System.out.println("Version: 5.0");
        System.out.println("--------------------------------------");

        // Initialize booking queue
        BookingQueue bookingQueue = new BookingQueue();

        // Simulate incoming booking requests
        bookingQueue.addRequest(new Reservation("Alice", "Single BookMyStayApp.Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double BookMyStayApp.Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite BookMyStayApp.Room"));
        bookingQueue.addRequest(new Reservation("David", "Single BookMyStayApp.Room"));

        // Display queue (FIFO order preserved)
        bookingQueue.displayQueue();

        System.out.println("\nAll requests are queued. No allocation done yet.");
        System.out.println("======================================");
    }
}