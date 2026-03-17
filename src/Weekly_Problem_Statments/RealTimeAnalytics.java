package Weekly_Problem_Statments;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RealTimeAnalytics {

    // Page views count
    private final Map<String, AtomicInteger> pageViews = new ConcurrentHashMap<>();

    // Unique visitors per page
    private final Map<String, Set<String>> uniqueVisitors = new ConcurrentHashMap<>();

    // Traffic sources
    private final Map<String, AtomicInteger> trafficSources = new ConcurrentHashMap<>();

    // Top pages (maintain min-heap)
    private final PriorityQueue<Map.Entry<String, AtomicInteger>> topPages = new PriorityQueue<>(
            Comparator.comparingInt(e -> e.getValue().get())
    );

    private final int TOP_N = 10;

    // Process incoming event
    public void processEvent(String url, String userId, String source) {
        // Page views
        pageViews.putIfAbsent(url, new AtomicInteger(0));
        pageViews.get(url).incrementAndGet();

        // Unique visitors
        uniqueVisitors.putIfAbsent(url, ConcurrentHashMap.newKeySet());
        uniqueVisitors.get(url).add(userId);

        // Traffic sources
        trafficSources.putIfAbsent(source, new AtomicInteger(0));
        trafficSources.get(source).incrementAndGet();
    }

    // Get dashboard summary
    public void getDashboard() {
        System.out.println("Top Pages:");
        pageViews.entrySet().stream()
                .sorted((a, b) -> b.getValue().get() - a.getValue().get())
                .limit(TOP_N)
                .forEach(e -> System.out.printf("%s - %d views (%d unique)\n",
                        e.getKey(), e.getValue().get(), uniqueVisitors.get(e.getKey()).size()));

        System.out.println("\nTraffic Sources:");
        int total = trafficSources.values().stream().mapToInt(AtomicInteger::get).sum();
        trafficSources.forEach((source, count) -> {
            double percent = total == 0 ? 0 : (count.get() * 100.0 / total);
            System.out.printf("%s: %.2f%%\n", source, percent);
        });
    }

    // --- Demo ---
    public static void main(String[] args) {
        RealTimeAnalytics analytics = new RealTimeAnalytics();

        // Simulate events
        analytics.processEvent("/article/breaking-news", "user_123", "google");
        analytics.processEvent("/article/breaking-news", "user_456", "facebook");
        analytics.processEvent("/sports/championship", "user_789", "google");
        analytics.processEvent("/article/breaking-news", "user_123", "google"); // same user

        analytics.getDashboard();
    }
}