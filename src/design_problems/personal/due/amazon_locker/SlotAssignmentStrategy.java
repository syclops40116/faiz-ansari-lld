package design_problems.personal.due.amazon_locker;

import java.util.List;

public interface SlotAssignmentStrategy {
    Slot assignSlot(Package pkg, List<Slot> eligibleSlots);
}
