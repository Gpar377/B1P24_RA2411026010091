import java.util.*;

/**
 * Book My Stay Application
 *
 * Use Case 7: Add-On Service Selection
 *
 * This program demonstrates how optional services can be attached
 * to an existing reservation without modifying core booking logic.
 *
 * Version: 7.0
 *
 * @author YourName
 * @version 7.0
 */


// ===================== SERVICE =====================

class AddOnService {

    private String serviceName;
    private double price;

    public AddOnService(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getPrice() {
        return price;
    }

    public void display() {
        System.out.println(serviceName + " → ₹" + price);
    }
}


// ===================== SERVICE MANAGER =====================

class AddOnServiceManager {

    // Map → Reservation ID → List of Services
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    // Add service to reservation
    public void addService(String reservationId, AddOnService service) {

        serviceMap
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);

        System.out.println("Added service: " + service.getServiceName()
                + " to Reservation ID: " + reservationId);
    }

    // Display services for a reservation
    public void displayServices(String reservationId) {

        System.out.println("\n--- Services for Reservation: " + reservationId + " ---");

        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services selected.");
            return;
        }

        double totalCost = 0;

        for (AddOnService s : services) {
            s.display();
            totalCost += s.getPrice();
        }

        System.out.println("--------------------------------------");
        System.out.println("Total Add-On Cost: ₹" + totalCost);
    }
}


// ===================== MAIN APPLICATION =====================

public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("     Book My Stay - Add-On Services   ");
        System.out.println("======================================");
        System.out.println("Version: 7.0");
        System.out.println("--------------------------------------");

        // Simulated reservation ID (from UC6)
        String reservationId = "SR-1234";

        // Initialize service manager
        AddOnServiceManager serviceManager = new AddOnServiceManager();

        // Guest selects services
        serviceManager.addService(reservationId, new AddOnService("Breakfast", 500));
        serviceManager.addService(reservationId, new AddOnService("Airport Pickup", 1200));
        serviceManager.addService(reservationId, new AddOnService("Extra Bed", 800));

        // Display selected services
        serviceManager.displayServices(reservationId);

        System.out.println("\nCore booking and inventory remain unchanged.");
        System.out.println("======================================");
    }
}