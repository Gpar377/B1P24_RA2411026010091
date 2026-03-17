package BookMyStayApp.usecase;

import BookMyStayApp.domain.DoubleRoom;
import BookMyStayApp.domain.Room;
import BookMyStayApp.domain.SingleRoom;
import BookMyStayApp.domain.SuiteRoom;

public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        Room single = new SingleRoom();
        Room dbl = new DoubleRoom();
        Room suite = new SuiteRoom();

        single.displayDetails();
        dbl.displayDetails();
        suite.displayDetails();
    }
}