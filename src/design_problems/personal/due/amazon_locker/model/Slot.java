package design_problems.personal.due.amazon_locker.model;

import design_problems.personal.due.amazon_locker.enums.Size;

public class Slot {
    private String lockerId;
    private String slotId;
    private Size slotSize;
    private long acquiredAt;
    private Package storedPackage;

    public Slot(String lockerId, String slotId, Size slotSize) {
        this.lockerId = lockerId;
        this.slotId = slotId;
        this.slotSize = slotSize;
        storedPackage = null;
    }

    public boolean canFit(Package pkg) {
        return slotSize == pkg.getPackageSize();
    }

    public boolean isAvailable() {
        return storedPackage == null;
    }

    public synchronized boolean acquire(Package pkg) {
        if( storedPackage != null ) {
            return false;
        }
        storedPackage = pkg;
        acquiredAt = System.currentTimeMillis();
        return true;
    }

    public void release(){
        storedPackage = null;
    }

    public String getSlotId() {
        return slotId;
    }

    public String getLockerId() {
        return lockerId;
    }

    public long getAcquiredAt() {
        return acquiredAt;
    }

    public Package getStoredPackage() {
        return storedPackage;
    }

    public Size getSlotSize() {
        return slotSize;
    }
}
