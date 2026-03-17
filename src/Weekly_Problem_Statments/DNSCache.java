package Weekly_Problem_Statments;
import java.util.*;
import java.util.concurrent.*;

public class DNSCache {

    static class DNSEntry {
        String ipAddress;
        long expiryTime; // in milliseconds

        DNSEntry(String ip, long ttlSeconds) {
            this.ipAddress = ip;
            this.expiryTime = System.currentTimeMillis() + ttlSeconds * 1000;
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }
    }

    private final Map<String, DNSEntry> cache = new ConcurrentHashMap<>();
    private long hits = 0, misses = 0;

    // Background cleanup thread
    public DNSCache() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            cache.entrySet().removeIf(entry -> entry.getValue().isExpired());
        }, 1, 1, TimeUnit.SECONDS);
    }

    // Resolve domain
    public String resolve(String domain) {
        DNSEntry entry = cache.get(domain);
        if (entry != null && !entry.isExpired()) {
            hits++;
            return entry.ipAddress + " (Cache HIT)";
        } else {
            misses++;
            String ip = queryUpstream(domain);
            cache.put(domain, new DNSEntry(ip, 300)); // TTL 300s
            return ip + " (Cache MISS)";
        }
    }

    // Simulated upstream DNS
    private String queryUpstream(String domain) {
        // In reality, would query actual DNS server
        return "172.217." + new Random().nextInt(256) + "." + new Random().nextInt(256);
    }

    public void getCacheStats() {
        long total = hits + misses;
        double hitRate = total == 0 ? 0 : (hits * 100.0 / total);
        System.out.printf("Cache HIT: %.2f%%, MISS: %.2f%%\n", hitRate, 100 - hitRate);
    }

    // --- Demo ---
    public static void main(String[] args) throws InterruptedException {
        DNSCache dns = new DNSCache();
        System.out.println(dns.resolve("google.com"));
        System.out.println(dns.resolve("google.com"));
        Thread.sleep(310 * 1000); // simulate expiry
        System.out.println(dns.resolve("google.com"));
        dns.getCacheStats();
    }
}