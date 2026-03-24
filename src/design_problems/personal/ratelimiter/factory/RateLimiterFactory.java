package design_problems.personal.ratelimiter.factory;

import design_problems.personal.ratelimiter.constants.RateLimitType;
import design_problems.personal.ratelimiter.strategy.RateLimiter;
import design_problems.personal.ratelimiter.configuration.RateLimitConfig;
import design_problems.personal.ratelimiter.strategy.FixedWindowRateLimiter;
import design_problems.personal.ratelimiter.strategy.SlidingWindowLogRateLimiter;
import design_problems.personal.ratelimiter.strategy.TokenBucketRateLimiter;

public class RateLimiterFactory {

    public static RateLimiter createRateLimiter(
            RateLimitType type,
            RateLimitConfig config
    ) {
        switch (type) {
            case TOKEN_BUCKET:
                return new TokenBucketRateLimiter(config);
            case FIXED_WINDOW:
                return new FixedWindowRateLimiter(config);
            case SLIDING_WINDOW_LOG:
                return new SlidingWindowLogRateLimiter(config);
            default:
                throw new IllegalArgumentException("Unsupported rate limiter type");
        }
    }
}

