package BookMyStayApp.service;

import BookMyStayApp.exception.InvalidBookingException;
import BookMyStayApp.domain.Reservation;

import java.util.*;

public class BookingService {

    private RoomInventory inventory;
    private Set<String> allocatedIds = new HashSet<>();

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void processBookings(BookingQueue queue) {

        while (!queue.isEmpty()) {

            Reservation r = queue.getNext();

            try {
                // VALIDATION STEP (NEW)
                BookingValidator.validate(r, inventory);

                String id = generateId(r.getRoomType());

                allocatedIds.add(id);
                inventory.reduceAvailability(r.getRoomType());

                System.out.println("Booking Confirmed → "
                        + r.getGuestName()
                        + " | Room ID: " + id);

            } catch (InvalidBookingException e) {

                // Graceful failure
                System.out.println("Booking Failed → "
                        + r.getGuestName()
                        + " | Reason: " + e.getMessage());
            }
        }
    }

    private String generateId(String type) {
        return type.substring(0, 2).toUpperCase() + "-" +
                UUID.randomUUID().toString().substring(0, 4);
    }
}}