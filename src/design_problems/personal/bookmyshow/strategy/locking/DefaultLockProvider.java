package design_problems.personal.bookmyshow.strategy.locking;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultLockProvider implements LockProvider{

    private record Expiry(long deadline, String owner) {}

    private final Map<String, Expiry> locks = new ConcurrentHashMap<>();

    @Override
    public boolean tryLock(String key, String userId, long ttlMs) {
        long now = System.currentTimeMillis();
        Expiry expiry = new Expiry(now + ttlMs, userId);
        return locks.compute(key, (k, v) -> (v == null || v.deadline < now) ? expiry : v) == expiry;
    }

    @Override
    public void unlock(String key) {
        locks.remove(key);
    }

    @Override
    public boolean isLockExpired(String key) {
        Expiry e = locks.get(key);
        return e == null || e.deadline < System.currentTimeMillis();
    }

    @Override
    public boolean isLockedBy(String key, String userId) {
        Expiry e = locks.get(key);
        return e != null && e.owner.equals(userId);
    }
}
