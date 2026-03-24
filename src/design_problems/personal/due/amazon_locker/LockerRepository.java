package design_problems.personal.due.amazon_locker;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class LockerRepository {
    Map<String, List<Locker>> zipcodeToLockersMap = new HashMap<>();
    Map<String, Locker> lockerMap = new HashMap<>();

    public void save(Locker locker) {
        if(!lockerMap.containsKey(locker.lockerId)) {
            String lockerId = locker.lockerId;
            String zipcode = locker.zipcode;

            lockerMap.put(lockerId, locker);
            List<Locker> lockers = zipcodeToLockersMap.getOrDefault(zipcode, new ArrayList<>());
            lockers.add(locker);
            zipcodeToLockersMap.put(zipcode, lockers);
        }
    }

    public Locker getLockerByLockerId(String lockerId) {
        return lockerMap.getOrDefault(lockerId, null);
    }

    public List<Locker> getLockerByZipcode(String zipcode) {
        return zipcodeToLockersMap.getOrDefault(zipcode, new ArrayList<>());
    }
}
