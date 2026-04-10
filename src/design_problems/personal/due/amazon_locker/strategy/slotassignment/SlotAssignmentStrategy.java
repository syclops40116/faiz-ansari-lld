package design_problems.personal.due.amazon_locker.strategy.slotassignment;

import design_problems.personal.due.amazon_locker.model.Package;
import design_problems.personal.due.amazon_locker.model.Slot;

import java.util.List;

public interface SlotAssignmentStrategy {
    Slot assignSlot(Package pkg, List<Slot> eligibleSlots);
}
