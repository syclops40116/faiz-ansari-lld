package design_problems.personal.due.amazon_locker;

import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        LockerService lockerService = new LockerService(new FirstFitSlotAssignmentStrategy());

        lockerService.addLocker("123456", "Twin tower");

        Package pkg = new Package(Size.MEDIUM, UUID.randomUUID().toString());

        List<Locker> eligibleLockers = lockerService.getEligibleLockersByZipAndSize("123456", pkg);

        Locker selectedLocker = eligibleLockers.getFirst();

        Slot slot = lockerService.reserveSlotForPackage(selectedLocker, pkg);


        // This will be remaining
    }
}
