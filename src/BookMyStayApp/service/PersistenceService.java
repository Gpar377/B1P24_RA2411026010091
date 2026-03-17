package BookMyStayApp.service;

import java.io.*;
import BookMyStayApp.domain.Reservation;
import java.util.LinkedList;
import java.util.Queue;

public class PersistenceService {

    public void saveState(RoomInventory inventory, Queue<Reservation> bookingQueue, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(inventory);
            out.writeObject(new LinkedList<>(bookingQueue)); // serialize queue
            System.out.println("System state saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving system state: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public State restoreState(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            RoomInventory inventory = (RoomInventory) in.readObject();
            Queue<Reservation> queue = (Queue<Reservation>) in.readObject();
            System.out.println("System state restored successfully.");
            return new State(inventory, queue);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error restoring state. Initializing fresh state.");
            return new State(new RoomInventory(), new LinkedList<>());
        }
    }

    // Wrapper class to return multiple objects
    public static class State {
        public RoomInventory inventory;
        public Queue<Reservation> bookingQueue;

        public State(RoomInventory inventory, Queue<Reservation> bookingQueue) {
            this.inventory = inventory;
            this.bookingQueue = bookingQueue;
        }
    }
}