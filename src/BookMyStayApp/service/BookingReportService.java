package BookMyStayApp.service;

import BookMyStayApp.domain.Reservation;

import java.util.*;

public class BookingReportService {

    private BookingHistory history;

    public BookingReportService(BookingHistory history) {
        this.history = history;
    }

    public void summary() {

        Map<String, Integer> map = new HashMap<>();

        for (Reservation r : history.getAll()) {
            map.put(r.getRoomType(),
                    map.getOrDefault(r.getRoomType(), 0) + 1);
        }

        map.forEach((k, v) ->
                System.out.println(k + " → " + v));
    }
}