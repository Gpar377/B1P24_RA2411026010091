package Weekly_Problem_Statments;
import java.util.*;
import java.util.concurrent.*;

// Video data placeholder
class VideoData {
    String videoId;
    String content;

    VideoData(String videoId, String content) {
        this.videoId = videoId;
        this.content = content;
    }
}

public class MultiLevelCache {

    // L1: In-memory cache (LinkedHashMap for LRU)
    private final LinkedHashMap<String, VideoData> L1;
    private final int L1_CAPACITY = 10000;

    // L2: SSD-backed (simulated with HashMap)
    private final Map<String, VideoData> L2 = new ConcurrentHashMap<>();
    private final int L2_CAPACITY = 100000;

    // L3: Database (all videos)
    private final Map<String, VideoData> L3 = new HashMap<>();

    // Access count for promotions
    private final Map<String, Integer> accessCount = new ConcurrentHashMap<>();
    private final int PROMOTION_THRESHOLD = 5;

    public MultiLevelCache() {
        L1 = new LinkedHashMap<>(L1_CAPACITY, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, VideoData> eldest) {
                return size() > L1_CAPACITY;
            }
        };
    }

    // Add video to database
    public void addVideoToDB(String videoId, String content) {
        VideoData video = new VideoData(videoId, content);
        L3.put(videoId, video);
        L2.put(videoId, video); // simulate SSD
    }

    // Get video from cache system
    public VideoData getVideo(String videoId) {
        long start = System.currentTimeMillis();

        // L1
        if (L1.containsKey(videoId)) {
            accessCount.put(videoId, accessCount.getOrDefault(videoId, 0) + 1);
            System.out.println("L1 Cache HIT (0.5ms)");
            return L1.get(videoId);
        }

        // L2
        if (L2.containsKey(videoId)) {
            VideoData video = L2.get(videoId);
            accessCount.put(videoId, accessCount.getOrDefault(videoId, 0) + 1);
            if (accessCount.get(videoId) >= PROMOTION_THRESHOLD) {
                L1.put(videoId, video); // promote to L1
            }
            System.out.println("L2 Cache HIT (5ms)");
            return video;
        }

        // L3
        if (L3.containsKey(videoId)) {
            VideoData video = L3.get(videoId);
            L2.put(videoId, video); // add to L2
            accessCount.put(videoId, 1);
            System.out.println("L3 Database HIT (150ms)");
            return video;
        }

        System.out.println("Video not found!");
        return null;
    }

    // --- Demo ---
    public static void main(String[] args) {
        MultiLevelCache cache = new MultiLevelCache();

        // Add videos
        cache.addVideoToDB("video_123", "Content 123");
        cache.addVideoToDB("video_999", "Content 999");

        System.out.println("\nFirst request:");
        cache.getVideo("video_123"); // L2 -> L1 promotion

        System.out.println("\nSecond request:");
        cache.getVideo("video_123"); // L1 HIT

        System.out.println("\nRequest for L3 only video:");
        cache.getVideo("video_999"); // L3 -> L2
    }
}