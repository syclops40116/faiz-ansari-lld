package design_problems.personal.due.amazon_locker;

public class Slot {
    String lockerId;
    String slotId;
    Size slotSize;
    long acquiredAt;
    Package storedPackage;

    public Slot(String lockerId, String slotId, Size slotSize) {
        this.lockerId = lockerId;
        this.slotId = slotId;
        this.slotSize = slotSize;
        storedPackage = null;
    }

    public boolean canFit(Package pkg) {
        return slotSize == pkg.packageSize;
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
}
