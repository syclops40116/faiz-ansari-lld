package design_problems.personal.due.amazon_locker;

import design_problems.personal.due.amazon_locker.model.Locker;
import design_problems.personal.due.amazon_locker.model.Package;
import design_problems.personal.due.amazon_locker.enums.Size;
import design_problems.personal.due.amazon_locker.model.Slot;
import design_problems.personal.due.amazon_locker.service.LockerService;
import design_problems.personal.due.amazon_locker.strategy.slotassignment.FirstFitSlotAssignmentStrategy;

import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        LockerService lockerService = new LockerService(new FirstFitSlotAssignmentStrategy());

        lockerService.addLocker("123456", "Twin tower");

        design_problems.personal.due.amazon_locker.model.Package pkg = new Package(Size.MEDIUM, UUID.randomUUID().toString());

        List<Locker> eligibleLockers = lockerService.getEligibleLockersByZipAndSize("123456", pkg);

        Locker selectedLocker = eligibleLockers.getFirst();

        Slot slot = lockerService.reserveSlotForPackage(selectedLocker, pkg);


        // This will be remaining
    }
}
