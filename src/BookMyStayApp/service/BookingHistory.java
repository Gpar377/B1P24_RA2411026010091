package BookMyStayApp.service;

import BookMyStayApp.domain.Reservation;

import java.util.*;

public class BookingHistory {

    private List<Reservation> list = new ArrayList<>();

    public void add(Reservation r) {
        list.add(r);
    }

    public List<Reservation> getAll() {
        return list;
    }
}