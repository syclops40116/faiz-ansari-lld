package design_problems.personal.due.amazon_locker.service;

import design_problems.personal.due.amazon_locker.model.Locker;
import design_problems.personal.due.amazon_locker.model.Package;
import design_problems.personal.due.amazon_locker.model.Slot;
import design_problems.personal.due.amazon_locker.repository.LockerRepository;
import design_problems.personal.due.amazon_locker.strategy.slotassignment.SlotAssignmentStrategy;

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

    public List<Locker> getEligibleLockersByZipAndSize(String zip, design_problems.personal.due.amazon_locker.model.Package pkg) {
        List<Locker> lockers = lockerRepository.getLockerByZipcode(zip);
        List<Locker> eligibleLockers = lockers.stream().filter(locker -> locker.getAllSlots().stream()
                .anyMatch(slot -> slot.canFit(pkg)))
                .collect(Collectors.toList());
        if(eligibleLockers.isEmpty()) {
            throw new RuntimeException("No locker available for package");
        }
        return eligibleLockers;
    }

    private List<Slot> getEligibleSlots(Locker locker, design_problems.personal.due.amazon_locker.model.Package pkg) {
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
        System.out.println("The slot {" + reservedSlot.getSlotId() + "} has been reserved for package {" + pkg + "} \n" );
        return reservedSlot;
    }
}
