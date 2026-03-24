package design_problems.personal.ratelimiter.strategy;

import design_problems.personal.ratelimiter.configuration.RateLimitConfig;

public abstract class RateLimiter {

    protected final RateLimitConfig config;

    protected RateLimiter(RateLimitConfig config) {
        this.config = config;
    }

    public abstract boolean allowRequest(String userId);
}