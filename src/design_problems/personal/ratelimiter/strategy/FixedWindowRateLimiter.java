package design_problems.personal.ratelimiter.strategy;

import design_problems.personal.ratelimiter.configuration.RateLimitConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class FixedWindowRateLimiter extends RateLimiter {

    private final Map<String, Integer> requestCount = new ConcurrentHashMap<>();
    private final Map<String, Long> windowStart = new ConcurrentHashMap<>();

    public FixedWindowRateLimiter(RateLimitConfig config) {
        super(config);
    }

    @Override
    public boolean allowRequest(String userId) {
        AtomicBoolean allowed = new AtomicBoolean(false);

        long currentReqWindow = (System.currentTimeMillis() / 1000) / config.getWindowInSeconds();

        requestCount.compute(userId, (id, count) -> {

            if (count == null) {
                windowStart.put(id, currentReqWindow);
                allowed.set(true);
                return 1;
            }

            long lastWindow = windowStart.get(id);

            if (lastWindow != currentReqWindow) {
                windowStart.put(id, currentReqWindow);
                allowed.set(true);
                return 1;
            }

            if (count < config.getMaxRequests()) {
                allowed.set(true);
                return count + 1;
            }

            return count;
        });
        return allowed.get();
    }
}
