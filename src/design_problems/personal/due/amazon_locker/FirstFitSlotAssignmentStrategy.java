package design_problems.personal.due.amazon_locker;

import java.util.List;

public class FirstFitSlotAssignmentStrategy implements SlotAssignmentStrategy{

    @Override
    public Slot assignSlot(Package pkg, List<Slot> eligibleSlots) {
        for(Slot slot: eligibleSlots) {
            if(slot.acquire(pkg)) {
                return slot;
            }
        }
        return null;
    }
}
