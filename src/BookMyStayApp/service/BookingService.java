package BookMyStayApp.service;

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

            if (inventory.getAvailability(r.getRoomType()) > 0) {

                String id = generateId(r.getRoomType());

                if (!allocatedIds.contains(id)) {
                    allocatedIds.add(id);
                    inventory.reduceAvailability(r.getRoomType());

                    System.out.println("Confirmed: " + r.getGuestName()
                            + " | ID: " + id);
                }
            } else {
                System.out.println("Failed: " + r.getGuestName());
            }
        }
    }

    private String generateId(String type) {
        return type.substring(0, 2).toUpperCase() + "-" +
                UUID.randomUUID().toString().substring(0, 4);
    }
}