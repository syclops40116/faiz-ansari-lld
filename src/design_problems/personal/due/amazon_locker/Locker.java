package design_problems.personal.due.amazon_locker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Locker {

    String lockerId;

    String zipcode;

    String address;

    List<Slot> slots;

    public List<Slot> getAllSlots() {
        return this.slots;
    }

    public Locker(String zipcode, String address) {
        this.lockerId = UUID.randomUUID().toString();
        this.zipcode = zipcode;
        this.address = address;
        this.slots = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Size slotSize;
            if(i <= 4) {
                slotSize = Size.SMALL;
            } else if (i <= 8) {
                slotSize = Size.MEDIUM;
            } else {
                slotSize = Size.LARGE;
            }
            slots.add(new Slot(lockerId, lockerId + ":" + i, slotSize));
        }
    }
}
