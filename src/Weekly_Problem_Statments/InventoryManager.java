package Weekly_Problem_Statments;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InventoryManager {

    // Product stock → O(1) lookup
    private final Map<String, AtomicInteger> stockMap = new ConcurrentHashMap<>();

    // Waiting list (FIFO) for sold-out products
    private final Map<String, Queue<Integer>> waitingList = new ConcurrentHashMap<>();

    // Add a product with initial stock
    public void addProduct(String productId, int stock) {
        stockMap.put(productId, new AtomicInteger(stock));
        waitingList.put(productId, new LinkedList<>());
    }

    // Check stock availability
    public int checkStock(String productId) {
        AtomicInteger stock = stockMap.get(productId);
        return stock != null ? stock.get() : 0;
    }

    // Purchase item
    public String purchaseItem(String productId, int userId) {
        stockMap.putIfAbsent(productId, new AtomicInteger(0));
        waitingList.putIfAbsent(productId, new LinkedList<>());

        AtomicInteger stock = stockMap.get(productId);

        synchronized (stock) {
            if (stock.get() > 0) {
                stock.decrementAndGet();
                return "Success, " + stock.get() + " units remaining";
            } else {
                Queue<Integer> queue = waitingList.get(productId);
                queue.add(userId);
                return "Added to waiting list, position #" + queue.size();
            }
        }
    }

    // --- Demo ---
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        manager.addProduct("IPHONE15_256GB", 5);

        System.out.println(manager.checkStock("IPHONE15_256GB")); // 5
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 101));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 102));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 103));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 104));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 105));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 106)); // waiting list
    }
}