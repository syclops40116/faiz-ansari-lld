package design_problems.personal.ratelimiter.service;

import design_problems.personal.ratelimiter.configuration.RateLimitConfig;
import design_problems.personal.ratelimiter.constants.RateLimitType;
import design_problems.personal.ratelimiter.constants.UserTier;
import design_problems.personal.ratelimiter.factory.RateLimiterFactory;
import design_problems.personal.ratelimiter.model.User;
import design_problems.personal.ratelimiter.strategy.RateLimiter;

import java.util.HashMap;
import java.util.Map;

public class RateLimiterService {

    private final Map<UserTier, RateLimiter> rateLimiters = new HashMap<>();

    public RateLimiterService() {

        // FREE users → Token Bucket
        rateLimiters.put(
                UserTier.STANDARD,
                RateLimiterFactory.createRateLimiter(
                        RateLimitType.TOKEN_BUCKET,
                        new RateLimitConfig(10, 60)
                )
        );

        // PREMIUM users → Fixed Window
        rateLimiters.put(
                UserTier.PREMIUM,
                RateLimiterFactory.createRateLimiter(
                        RateLimitType.SLIDING_WINDOW_LOG,
                        new RateLimitConfig(1, 30)
                )
        );
    }

    public boolean allowRequest(User user) {
        RateLimiter limiter = rateLimiters.get(user.getTier());

        if (limiter == null) {
            throw new IllegalArgumentException(
                    "No limiter configured for tier: " + user.getTier()
            );
        }

        return limiter.allowRequest(user.getUserId());
    }
}

