package Weekly_Problem_Statments;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiter {

    static class TokenBucket {
        private final int maxTokens;
        private final int refillRate; // tokens per second
        private double tokens;
        private long lastRefillTimestamp;

        public TokenBucket(int maxTokens, int refillRate) {
            this.maxTokens = maxTokens;
            this.refillRate = refillRate;
            this.tokens = maxTokens;
            this.lastRefillTimestamp = System.nanoTime();
        }

        // Thread-safe token consumption
        public synchronized boolean allowRequest() {
            refill();
            if (tokens >= 1) {
                tokens -= 1;
                return true;
            }
            return false;
        }

        private void refill() {
            long now = System.nanoTime();
            double seconds = (now - lastRefillTimestamp) / 1_000_000_000.0;
            tokens = Math.min(maxTokens, tokens + seconds * refillRate);
            lastRefillTimestamp = now;
        }

        public synchronized int getRemainingTokens() {
            refill();
            return (int) tokens;
        }
    }

    // Client ID → TokenBucket
    private final ConcurrentHashMap<String, TokenBucket> clients = new ConcurrentHashMap<>();

    private final int MAX_TOKENS = 1000;
    private final int REFILL_RATE = 1000; // per hour (approx 0.277 tokens/sec)

    // Check rate limit for a client
    public boolean checkRateLimit(String clientId) {
        clients.putIfAbsent(clientId, new TokenBucket(MAX_TOKENS, REFILL_RATE));
        TokenBucket bucket = clients.get(clientId);
        return bucket.allowRequest();
    }

    public int getRemaining(String clientId) {
        TokenBucket bucket = clients.get(clientId);
        return bucket != null ? bucket.getRemainingTokens() : MAX_TOKENS;
    }

    // --- Demo ---
    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = new RateLimiter();
        String client = "abc123";

        for (int i = 0; i < 5; i++) {
            boolean allowed = limiter.checkRateLimit(client);
            System.out.printf("Request %d: %s, Remaining: %d\n",
                    i + 1, allowed ? "ALLOWED" : "DENIED", limiter.getRemaining(client));
        }
    }
}