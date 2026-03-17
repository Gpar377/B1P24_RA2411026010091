package BookMyStayApp.service;

import BookMyStayApp.domain.Reservation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookingHistory implements Serializable {
    private List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation r) { reservations.add(r); }

    public Reservation findReservation(String guestName, String roomType) {
        return reservations.stream()
                .filter(r -> r.getGuestName().equals(guestName) &&
                        r.getRoomType().equals(roomType) &&
                        !r.isCancelled())
                .findFirst().orElse(null);
    }

    public void cancelReservation(Reservation r) {
        if (r != null) r.cancel();
    }

    public List<Reservation> getAllReservations() { return reservations; }
}