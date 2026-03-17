package BookMyStayApp.usecase;


import BookMyStayApp.service.AddOnService;
import BookMyStayApp.service.AddOnServiceManager;

public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        manager.addService("R1", new AddOnService("Breakfast", 500));
        manager.addService("R1", new AddOnService("Airport Pickup", 1200));

        manager.display("R1");
    }
}