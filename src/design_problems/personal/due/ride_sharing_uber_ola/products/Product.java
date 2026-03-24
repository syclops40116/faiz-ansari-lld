package design_problems.personal.due.ride_sharing_uber_ola.products;

// Product.java
public abstract class Product {

    public abstract double getBaseFare();
    public abstract double getPerKmRate();

    public String toString(){
        return this.getClass().getSimpleName();
    }
}

