package Weekly_Problem_Statments;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UsernameChecker {

    // Existing usernames → O(1) lookup
    private final Set<String> usernames = ConcurrentHashMap.newKeySet();

    // Track frequency of attempted usernames
    private final Map<String, AtomicInteger> attemptFrequency = new ConcurrentHashMap<>();

    // Add existing usernames
    public void addUser(String username) {
        usernames.add(username);
    }

    // Check availability in O(1)
    public boolean checkAvailability(String username) {
        // Increment attempt frequency
        attemptFrequency.putIfAbsent(username, new AtomicInteger(0));
        attemptFrequency.get(username).incrementAndGet();
        return !usernames.contains(username);
    }

    // Suggest similar usernames if taken
    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        int counter = 1;
        while (suggestions.size() < 3) {
            String suggestion = username + counter;
            if (!usernames.contains(suggestion)) {
                suggestions.add(suggestion);
            }
            counter++;
        }
        return suggestions;
    }

    // Get the most attempted username
    public String getMostAttempted() {
        return attemptFrequency.entrySet()
                .stream()
                .max(Comparator.comparingInt(e -> e.getValue().get()))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    // --- Demo ---
    public static void main(String[] args) {
        UsernameChecker checker = new UsernameChecker();
        checker.addUser("john_doe");
        checker.addUser("alice");
        checker.addUser("admin");

        System.out.println("Check john_doe: " + checker.checkAvailability("john_doe")); // false
        System.out.println("Check jane_smith: " + checker.checkAvailability("jane_smith")); // true

        System.out.println("Suggestions for john_doe: " + checker.suggestAlternatives("john_doe"));
        System.out.println("Most attempted: " + checker.getMostAttempted());
    }
}