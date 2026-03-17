package BookMyStayApp;

import java.util.*;

/**
 * Book My Stay Application
 *
 * Use Case 8: Booking History & Reporting
 *
 * This program stores confirmed bookings in a list to maintain
 * historical records and generates reports without modifying data.
 *
 * Version: 8.0
 *
 * @author YourName
 * @version 8.0
 */


// ===================== RESERVATION =====================

class Reservation {

    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() { return reservationId; }
    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }

    public void display() {
        System.out.println("BookMyStayApp.Reservation ID: " + reservationId +
                " | Guest: " + guestName +
                " | BookMyStayApp.Room: " + roomType);
    }
}


// ===================== BOOKING HISTORY =====================

class BookingHistory {

    // List preserves insertion order (chronological)
    private List<Reservation> history = new ArrayList<>();

    // Add confirmed booking
    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }

    // Get all reservations (read-only usage)
    public List<Reservation> getAllReservations() {
        return history;
    }
}


// ===================== REPORT SERVICE =====================

class BookingReportService {

    private BookingHistory history;

    public BookingReportService(BookingHistory history) {
        this.history = history;
    }

    // Display all bookings
    public void displayAllBookings() {

        System.out.println("\n--- Booking History ---");

        for (Reservation r : history.getAllReservations()) {
            r.display();
        }

        System.out.println("----------------------------------");
    }

    // Generate summary report
    public void generateSummary() {

        System.out.println("\n--- Booking Summary Report ---");

        Map<String, Integer> summary = new HashMap<>();

        for (Reservation r : history.getAllReservations()) {
            summary.put(
                    r.getRoomType(),
                    summary.getOrDefault(r.getRoomType(), 0) + 1
            );
        }

        for (Map.Entry<String, Integer> entry : summary.entrySet()) {
            System.out.println(entry.getKey() + " → Total Bookings: " + entry.getValue());
        }

        System.out.println("----------------------------------");
    }
}


// ===================== MAIN APPLICATION =====================

public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("     Book My Stay - Booking History   ");
        System.out.println("======================================");
        System.out.println("Version: 8.0");
        System.out.println("--------------------------------------");

        // Initialize history
        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings (from UC6)
        history.addReservation(new Reservation("SR-1001", "Alice", "Single BookMyStayApp.Room"));
        history.addReservation(new Reservation("DR-1002", "Bob", "Double BookMyStayApp.Room"));
        history.addReservation(new Reservation("SR-1003", "Charlie", "Single BookMyStayApp.Room"));
        history.addReservation(new Reservation("SU-1004", "David", "Suite BookMyStayApp.Room"));

        // Reporting service
        BookingReportService reportService = new BookingReportService(history);

        // Admin views data
        reportService.displayAllBookings();

        // Admin generates summary
        reportService.generateSummary();

        System.out.println("\nReport generated successfully.");
        System.out.println("======================================");
    }
}