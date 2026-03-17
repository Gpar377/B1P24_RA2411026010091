package BookMyStayApp.usecase;

import BookMyStayApp.domain.DoubleRoom;
import BookMyStayApp.domain.Room;
import BookMyStayApp.domain.SingleRoom;
import BookMyStayApp.domain.SuiteRoom;
import BookMyStayApp.service.RoomInventory;
import BookMyStayApp.service.RoomSearchService;

public class UseCase4RoomSearch {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        RoomSearchService service = new RoomSearchService(inventory);
        service.searchAvailableRooms(rooms);
    }
}