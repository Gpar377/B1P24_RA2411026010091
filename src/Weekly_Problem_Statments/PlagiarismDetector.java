package Weekly_Problem_Statments;
import java.io.*;
import java.util.*;

public class PlagiarismDetector {

    // Map n-gram hash → set of document IDs containing it
    private final Map<Integer, Set<String>> ngramMap = new HashMap<>();
    private final int N = 5; // 5-grams

    // Extract n-grams from text
    private List<String> extractNGrams(String text) {
        String[] words = text.split("\\s+");
        List<String> ngrams = new ArrayList<>();
        for (int i = 0; i <= words.length - N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < N; j++) {
                sb.append(words[i + j]).append(" ");
            }
            ngrams.add(sb.toString().trim());
        }
        return ngrams;
    }

    // Analyze a document for plagiarism
    public Map<String, Double> analyzeDocument(String docId, String content) {
        Map<String, Integer> matchCounts = new HashMap<>();
        List<String> ngrams = extractNGrams(content);

        for (String ng : ngrams) {
            int hash = ng.hashCode();
            Set<String> docs = ngramMap.getOrDefault(hash, new HashSet<>());
            for (String otherDoc : docs) {
                matchCounts.put(otherDoc, matchCounts.getOrDefault(otherDoc, 0) + 1);
            }
            docs.add(docId);
            ngramMap.put(hash, docs);
        }

        // Calculate similarity percentage
        Map<String, Double> similarity = new HashMap<>();
        for (Map.Entry<String, Integer> entry : matchCounts.entrySet()) {
            double percent = (entry.getValue() * 100.0) / ngrams.size();
            similarity.put(entry.getKey(), percent);
        }

        return similarity;
    }

    // --- Demo ---
    public static void main(String[] args) {
        PlagiarismDetector detector = new PlagiarismDetector();

        String doc1 = "This is a sample essay to test plagiarism detection system with some content";
        String doc2 = "This essay is a test of plagiarism detection system with similar content";
        String doc3 = "Completely unrelated content for a different document";

        System.out.println("Analyzing doc2:");
        Map<String, Double> result2 = detector.analyzeDocument("doc2", doc2);
        result2.forEach((k, v) -> System.out.printf("Similarity with %s: %.2f%%\n", k, v));

        System.out.println("\nAnalyzing doc3:");
        Map<String, Double> result3 = detector.analyzeDocument("doc3", doc3);
        result3.forEach((k, v) -> System.out.printf("Similarity with %s: %.2f%%\n", k, v));
    }
}