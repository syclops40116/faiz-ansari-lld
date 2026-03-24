package design_problems.personal.due.amazon_locker;

import java.util.List;
import java.util.stream.Collectors;

public class LockerService {
    LockerRepository lockerRepository;
    SlotAssignmentStrategy slotAssignmentStrategy;

    public LockerService(SlotAssignmentStrategy slotAssignmentStrategy) {
        this.lockerRepository = new LockerRepository();
        this.slotAssignmentStrategy = slotAssignmentStrategy;
    }

    public void addLocker(String zipcode, String address) {
        Locker locker = new Locker(zipcode, address);
        lockerRepository.save(locker);
    }

    public List<Locker> getEligibleLockersByZipAndSize(String zip, Package pkg) {
        List<Locker> lockers = lockerRepository.getLockerByZipcode(zip);
        List<Locker> eligibleLockers = lockers.stream().filter(locker -> locker.getAllSlots().stream()
                .anyMatch(slot -> slot.canFit(pkg)))
                .collect(Collectors.toList());
        if(eligibleLockers.isEmpty()) {
            throw new RuntimeException("No locker available for package");
        }
        return eligibleLockers;
    }

    private List<Slot> getEligibleSlots(Locker locker, Package pkg) {
        return locker.getAllSlots().stream()
                .filter(Slot::isAvailable)
                .filter(slot -> slot.canFit(pkg))
                .collect(Collectors.toList());
    }

    public Slot reserveSlotForPackage(Locker locker, Package pkg) {
        List<Slot> eligibleSlots = getEligibleSlots(locker, pkg);
        Slot reservedSlot = slotAssignmentStrategy.assignSlot(pkg, eligibleSlots);
        if(reservedSlot == null) {
            throw new RuntimeException("Not able to reserve the slot for the package");
        }
        System.out.println("The slot {" + reservedSlot.slotId + "} has been reserved for package {" + pkg + "} \n" );
        return reservedSlot;
    }
}
