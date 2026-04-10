package design_problems.personal.due.amazon_locker.model;

import design_problems.personal.due.amazon_locker.enums.Size;

public class Package {
    private final String packageId;
    private final Size packageSize;

    public Package(Size size, String id) {
        this.packageId = id;
        this.packageSize = size;
    }

    public Size getPackageSize() {
        return packageSize;
    }

    public String getPackageId() {
        return packageId;
    }
}
