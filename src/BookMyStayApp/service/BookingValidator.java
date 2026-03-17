package BookMyStayApp.service;

import BookMyStayApp.domain.Reservation;
import BookMyStayApp.exception.InvalidBookingException;

import java.util.Set;

public class BookingValidator {

    private static final Set<String> VALID_ROOM_TYPES = Set.of(
            "Single Room",
            "Double Room",
            "Suite Room"
    );

    public static void validate(Reservation reservation, RoomInventory inventory)
            throws InvalidBookingException {

        if (reservation == null) {
            throw new InvalidBookingException("Reservation cannot be null");
        }

        //  Guest name validation
        if (reservation.getGuestName() == null || reservation.getGuestName().isEmpty()) {
            throw new InvalidBookingException("Guest name is required");
        }

        // Room type validation
        if (!VALID_ROOM_TYPES.contains(reservation.getRoomType())) {
            throw new InvalidBookingException(
                    "Invalid room type: " + reservation.getRoomType());
        }

        // Inventory check (fail-fast)
        if (inventory.getAvailability(reservation.getRoomType()) <= 0) {
            throw new InvalidBookingException(
                    "No availability for room type: " + reservation.getRoomType());
        }
    }
}