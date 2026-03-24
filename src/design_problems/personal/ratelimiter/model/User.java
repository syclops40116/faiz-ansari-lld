package design_problems.personal.ratelimiter.model;

import design_problems.personal.ratelimiter.constants.UserTier;

public class User {
    private final String userId;
    private final UserTier tier;

    public User(String userId, UserTier tier) {
        this.userId = userId;
        this.tier = tier;
    }

    public String getUserId() {
        return userId;
    }

    public UserTier getTier() {
        return tier;
    }
}

