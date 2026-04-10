package design_problems.personal.due.amazon_locker.repository;

import design_problems.personal.due.amazon_locker.model.Locker;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class LockerRepository {
    Map<String, List<Locker>> zipcodeToLockersMap = new HashMap<>();
    Map<String, Locker> lockerMap = new HashMap<>();

    public void save(Locker locker) {
        if(!lockerMap.containsKey(locker.getLockerId())) {
            String lockerId = locker.getLockerId();
            String zipcode = locker.getZipcode();

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
