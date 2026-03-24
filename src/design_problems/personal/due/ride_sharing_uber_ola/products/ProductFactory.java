package design_problems.personal.due.ride_sharing_uber_ola.products;

import design_problems.personal.due.ride_sharing_uber_ola.enums.ProductType;

public class ProductFactory {

    public static Product create(ProductType type) {

        return switch (type) {
            case UBER_GO -> new UberGo();
            case UBER_BIKE -> new UberBike();
            case UBER_AUTO -> new UberAuto();
            default -> throw new RuntimeException("Invalid type");
        };
    }
}

