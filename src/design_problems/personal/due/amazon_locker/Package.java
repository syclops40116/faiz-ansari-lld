package design_problems.personal.due.amazon_locker;

public class Package {
    String packageId;
    Size packageSize;

    public Package(Size size, String id) {
        this.packageId = id;
        this.packageSize = size;
    }
}
