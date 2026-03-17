package Weekly_Problem_Statments;
import java.util.*;

class Transaction {
    int id;
    int amount;
    String merchant;
    String time;

    Transaction(int id, int amount, String merchant, String time) {
        this.id = id;
        this.amount = amount;
        this.merchant = merchant;
        this.time = time;
    }
}

public class TwoSumTransactions {

    private final List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    // Classic Two-Sum
    public List<int[]> findTwoSum(int target) {
        Map<Integer, Transaction> map = new HashMap<>();
        List<int[]> result = new ArrayList<>();

        for (Transaction t : transactions) {
            int complement = target - t.amount;
            if (map.containsKey(complement)) {
                result.add(new int[]{map.get(complement).id, t.id});
            }
            map.put(t.amount, t);
        }

        return result;
    }

    // Detect duplicates (same amount, same merchant)
    public List<String> detectDuplicates() {
        Map<String, Set<Integer>> map = new HashMap<>();
        List<String> duplicates = new ArrayList<>();

        for (Transaction t : transactions) {
            String key = t.amount + ":" + t.merchant;
            map.putIfAbsent(key, new HashSet<>());
            Set<Integer> ids = map.get(key);
            if (!ids.isEmpty()) {
                duplicates.add("Duplicate detected for " + key + " in accounts: " + ids + " & " + t.id);
            }
            ids.add(t.id);
        }

        return duplicates;
    }

    // --- Demo ---
    public static void main(String[] args) {
        TwoSumTransactions processor = new TwoSumTransactions();

        processor.addTransaction(new Transaction(1, 500, "Store A", "10:00"));
        processor.addTransaction(new Transaction(2, 300, "Store B", "10:15"));
        processor.addTransaction(new Transaction(3, 200, "Store C", "10:30"));
        processor.addTransaction(new Transaction(4, 500, "Store A", "11:00")); // duplicate

        System.out.println("Two-Sum pairs for 500:");
        for (int[] pair : processor.findTwoSum(500)) {
            System.out.println(Arrays.toString(pair));
        }

        System.out.println("\nDuplicates:");
        for (String s : processor.detectDuplicates()) {
            System.out.println(s);
        }
    }
}