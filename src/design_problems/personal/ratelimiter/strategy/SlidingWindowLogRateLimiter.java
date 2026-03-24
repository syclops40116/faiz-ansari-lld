package design_problems.personal.ratelimiter.strategy;

import design_problems.personal.ratelimiter.configuration.RateLimitConfig;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowLogRateLimiter extends RateLimiter{

    // Stores request timestamps per user (Thread-safe per user)
    private final Map<String, Deque<Long>> userRequestLogs = new ConcurrentHashMap<>();

    public SlidingWindowLogRateLimiter(RateLimitConfig config) {
        super(config);
    }

    @Override
    public boolean allowRequest(String userId) {

        long nowInSeconds = System.currentTimeMillis() / 1000;

        // Get or create request log for user
        Deque<Long> requestLog =
                userRequestLogs.computeIfAbsent(userId, k -> new ArrayDeque<>());

        // 🔥 Per-user locking strategy:
        // We synchronize on the user's deque itself (no global lock)
        synchronized (requestLog) {
            // Remove timestamps outside the sliding window
            while (!requestLog.isEmpty()
                    && requestLog.peekFirst() <= nowInSeconds - config.getWindowInSeconds()) {
                requestLog.pollFirst();
            }

            // Check rate limit
            if (requestLog.size() < config.getMaxRequests()) {
                requestLog.addLast(nowInSeconds);
                return true;
            }

            return false;
        }
    }
}
