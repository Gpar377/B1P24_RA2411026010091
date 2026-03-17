package Weekly_Problem_Statments;
import java.util.*;

public class ParkingLotManager {

    static class Spot {
        String licensePlate;
        boolean occupied;

        Spot() {
            this.licensePlate = null;
            this.occupied = false;
        }
    }

    private final Spot[] spots;
    private final int size;

    public ParkingLotManager(int capacity) {
        this.size = capacity;
        spots = new Spot[capacity];
        for (int i = 0; i < capacity; i++) {
            spots[i] = new Spot();
        }
    }

    // Hash function for license plate
    private int hash(String licensePlate) {
        return Math.abs(licensePlate.hashCode()) % size;
    }

    // Park vehicle using linear probing
    public int parkVehicle(String licensePlate) {
        int start = hash(licensePlate);
        int pos = start;
        int probes = 0;

        do {
            if (!spots[pos].occupied) {
                spots[pos].licensePlate = licensePlate;
                spots[pos].occupied = true;
                System.out.printf("Assigned spot #%d (%d probes)\n", pos, probes);
                return pos;
            }
            pos = (pos + 1) % size;
            probes++;
        } while (pos != start);

        System.out.println("Parking lot full!");
        return -1;
    }

    // Exit vehicle
    public void exitVehicle(String licensePlate, int hours, double ratePerHour) {
        int start = hash(licensePlate);
        int pos = start;

        do {
            if (spots[pos].occupied && licensePlate.equals(spots[pos].licensePlate)) {
                spots[pos].occupied = false;
                spots[pos].licensePlate = null;
                double fee = hours * ratePerHour;
                System.out.printf("Spot #%d freed, Duration: %dh, Fee: $%.2f\n", pos, hours, fee);
                return;
            }
            pos = (pos + 1) % size;
        } while (pos != start);

        System.out.println("Vehicle not found!");
    }

    // --- Demo ---
    public static void main(String[] args) {
        ParkingLotManager lot = new ParkingLotManager(500);

        lot.parkVehicle("ABC-1234");
        lot.parkVehicle("ABC-1235");
        lot.parkVehicle("XYZ-9999");

        lot.exitVehicle("ABC-1234", 2, 6.25);
    }
}