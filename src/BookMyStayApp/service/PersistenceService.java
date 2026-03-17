package BookMyStayApp.service;
import java.io.*;

public class PersistenceService {

    public void saveState(RoomInventory inventory, BookingHistory history, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(inventory);
            out.writeObject(history);
        } catch (IOException e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    public void loadState(RoomInventory inventory, BookingHistory history, String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            RoomInventory inv = (RoomInventory) in.readObject();
            BookingHistory hist = (BookingHistory) in.readObject();
            // Update references
            inventory = inv;
            history = hist;
        } catch (Exception e) {
            System.out.println("State file missing or corrupted. Starting fresh.");
        }
    }
}