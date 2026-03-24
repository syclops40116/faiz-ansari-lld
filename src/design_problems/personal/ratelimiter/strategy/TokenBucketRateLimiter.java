package design_problems.personal.ratelimiter.strategy;

import design_problems.personal.ratelimiter.configuration.RateLimitConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class TokenBucketRateLimiter extends RateLimiter {

    private static class Bucket {
        int tokens;
        long lastRefillTime;

        Bucket(int tokens, long lastRefillTime) {
            this.tokens = tokens;
            this.lastRefillTime = lastRefillTime;
        }
    }

    // Thread-safe → atomic updates per userId
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    public TokenBucketRateLimiter(RateLimitConfig config) {
        super(config);
    }

    @Override
    public boolean allowRequest(String userId) {
        AtomicBoolean allowed = new AtomicBoolean(false);
        long now = System.currentTimeMillis();

        buckets.compute(userId, (id, bucket) -> {
            if(bucket == null) {
                bucket = new Bucket(config.getMaxRequests(), now);
            }

            double refillRate = (double) config.getWindowInSeconds() / config.getMaxRequests();

            long elapsedSeconds = (now - bucket.lastRefillTime) / 1000;

            int refill = (int) (elapsedSeconds / refillRate);

            if (refill > 0) {
                bucket.tokens = Math.min(
                        config.getMaxRequests(),
                        bucket.tokens + refill
                );
                bucket.lastRefillTime = now;
            }

            if (bucket.tokens > 0) {
                bucket.tokens--;
                allowed.set(true);
            }

            return bucket;
        });

        return allowed.get();
    }
}
