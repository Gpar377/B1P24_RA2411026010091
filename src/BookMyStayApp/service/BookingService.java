package BookMyStayApp.service;

import BookMyStayApp.domain.Reservation;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BookingService {
    private final RoomInventory inventory;
    private final Set<String> allocatedRoomIds = new HashSet<>();

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void processBooking(Reservation reservation) {
        synchronized (this) {
            String roomType = reservation.getRoomType();
            if (inventory.allocateRoom(roomType)) {
                String roomId = roomType.substring(0, 2).toUpperCase() + "-" + UUID.randomUUID().toString().substring(0,4);
                allocatedRoomIds.add(roomId);
                System.out.println("Booking Confirmed → Guest: " + reservation.getGuestName() + " | Room: " + roomType + " | Room ID: " + roomId);
            } else {
                System.out.println("Booking Failed → No availability for " + roomType + " (Guest: " + reservation.getGuestName() + ")");
            }
        }
    }
    // UC10: Cancel Booking
    public void cancelBooking(String guestName, String roomType, RoomInventory inventory) {
        synchronized (this) {
            // For simplicity, just increment inventory
            int current = inventory.getAvailability(roomType);
            inventory.incrementRoom(roomType); // implement incrementRoom method in RoomInventory
            System.out.println("Booking cancelled for " + guestName + " | Room Type: " + roomType);
        }
    }
}