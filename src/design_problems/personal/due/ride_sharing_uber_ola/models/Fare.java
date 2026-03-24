package design_problems.personal.due.ride_sharing_uber_ola.models;

import design_problems.personal.due.ride_sharing_uber_ola.products.Product;

import java.time.Instant;

public class Fare {
    public String id;
    public Product product;
    public Location source;
    public Location destination;
    public double estimatedFare;
    public Instant createdAt;
}
