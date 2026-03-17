package BookMyStayApp.service;
import BookMyStayApp.domain.Reservation;

import java.util.Stack;

public class CancellationService {
    private RoomInventory inventory;
    private BookingHistory history;
    private Stack<String> rollbackStack = new Stack<>();

    public CancellationService(RoomInventory inventory, BookingHistory history) {
        this.inventory = inventory;
        this.history = history;
    }

    public boolean cancelBooking(String guestName, String roomType) {
        Reservation r = history.findReservation(guestName, roomType);
        if (r == null) return false;

        rollbackStack.push(r.getRoomId());
        history.cancelReservation(r);
        inventory.increaseAvailability(roomType);
        return true;
    }

    public Stack<String> getRollbackStack() { return rollbackStack; }
}
