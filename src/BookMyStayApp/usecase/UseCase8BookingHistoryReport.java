package BookMyStayApp.usecase;

import BookMyStayApp.domain.Reservation;
import BookMyStayApp.service.BookingHistory;
import BookMyStayApp.service.BookingReportService;

public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        history.add(new Reservation("R1", "Alice", "Single Room"));
        history.add(new Reservation("R2", "Bob", "Double Room"));

        BookingReportService report = new BookingReportService(history);

        report.summary();
    }
}