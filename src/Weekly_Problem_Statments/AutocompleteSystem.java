package Weekly_Problem_Statments;
import java.util.*;

// Trie node
class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    Map<String, Integer> frequencyMap = new HashMap<>(); // query -> frequency
    boolean isEndOfWord = false;
}

public class AutocompleteSystem {

    private final TrieNode root = new TrieNode();

    // Insert a search query into the trie
    public void insertQuery(String query, int frequency) {
        TrieNode node = root;
        for (char c : query.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
            node.frequencyMap.put(query, node.frequencyMap.getOrDefault(query, 0) + frequency);
        }
        node.isEndOfWord = true;
    }

    // Search for top 10 suggestions for a prefix
    public List<String> search(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            node = node.children.get(c);
            if (node == null) return new ArrayList<>();
        }

        // Min-heap for top 10
        PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>(
                Map.Entry.comparingByValue()
        );

        for (Map.Entry<String, Integer> entry : node.frequencyMap.entrySet()) {
            heap.offer(entry);
            if (heap.size() > 10) heap.poll();
        }

        List<String> result = new ArrayList<>();
        while (!heap.isEmpty()) result.add(heap.poll().getKey());
        Collections.reverse(result); // highest frequency first
        return result;
    }

    // Update frequency of a search query
    public void updateFrequency(String query) {
        insertQuery(query, 1);
    }

    // --- Demo ---
    public static void main(String[] args) {
        AutocompleteSystem system = new AutocompleteSystem();

        // Insert queries with frequencies
        system.insertQuery("java tutorial", 1234567);
        system.insertQuery("javascript", 987654);
        system.insertQuery("java download", 456789);
        system.insertQuery("javabeans", 20000);

        System.out.println("Suggestions for 'jav': " + system.search("jav"));

        // Update frequency
        system.updateFrequency("javabeans");
        system.updateFrequency("javabeans");
        System.out.println("Updated suggestions for 'jav': " + system.search("jav"));
    }
}