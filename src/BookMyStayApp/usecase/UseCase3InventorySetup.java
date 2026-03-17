package BookMyStayApp.usecase;

import BookMyStayApp.service.RoomInventory;

public class UseCase3InventorySetup {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        inventory.displayInventory();
    }
}