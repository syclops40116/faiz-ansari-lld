package design_problems.personal.due.ride_sharing_uber_ola.models;

import design_problems.personal.due.ride_sharing_uber_ola.products.Product;

import java.util.HashSet;
import java.util.Set;

public class Vehicle {

    public String numberPlate;
    public Set<Product> supportedProducts;

    public Vehicle(String numberPlate) {
        this.numberPlate = numberPlate;
        this.supportedProducts = new HashSet<>();
    }

    public void addProduct(Product product) {
        supportedProducts.add(product);
    }
}
