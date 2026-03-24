package design_problems.personal.due.car_rental;

import design_problems.personal.due.car_rental.enums.VehicleType;
import design_problems.personal.due.car_rental.factory.VehicleFactory;
import design_problems.personal.due.car_rental.model.Branch;
import design_problems.personal.due.car_rental.model.User;
import design_problems.personal.due.car_rental.repository.BookingRepository;
import design_problems.personal.due.car_rental.repository.BranchRepository;
import design_problems.personal.due.car_rental.service.BookingService;
import design_problems.personal.due.car_rental.strategy.booking.LeastBookedVehicleStrategy;
import design_problems.personal.due.car_rental.strategy.payment.CreditCardPaymentStrategy;
import design_problems.personal.due.car_rental.strategy.payment.WalletPaymentStrategy;
import design_problems.personal.due.car_rental.strategy.pricing.HourlyPricingStrategy;
import design_problems.personal.due.car_rental.utils.DateTimeParser;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        BranchRepository branchRepo = new BranchRepository();
        BookingRepository bookingRepo = new BookingRepository();

        Branch branch1 = new Branch("B1", "Bangalore");
        Branch branch2 = new Branch("B2", "Hyderabad");

        branchRepo.addBranch(branch1);
        branchRepo.addBranch(branch2);

        branch1.addVehicle(VehicleFactory.create(VehicleType.SEDAN, "NY1234", 25, 3.5));
        branch1.addVehicle(VehicleFactory.create(VehicleType.SEDAN, "NY5678", 22, 3));
        branch1.addVehicle(VehicleFactory.create(VehicleType.SUV, "NYB100", 30, 4));

        branch2.addVehicle(VehicleFactory.create(VehicleType.SEDAN, "BO1234", 25, 4));

        User user = new User("U1", "John Doe", "john@example.com");

        LocalDateTime start = DateTimeParser.parse("15 Jun 7:30 AM 2026");
        LocalDateTime end = DateTimeParser.parse("16 Jun 12:30 PM 2026");

        BookingService bookingService = BookingService.getInstance(
                branchRepo,
                bookingRepo,
                new LeastBookedVehicleStrategy(),
                new HourlyPricingStrategy()
        );

        System.out.println("--------------");

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started!");
            bookingService.bookVehicle(
                    "B1",
                    VehicleType.SUV,
                    start,
                    end,
                    user,
                    new CreditCardPaymentStrategy(),
                    branch1,
                    branch2,
                    100.0
            );
            System.out.println(Thread.currentThread().getName() + " ended!");
        });

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started!");
            bookingService.bookVehicle(
                    "B1",
                    VehicleType.SUV,
                    start,
                    end,
                    user,
                    new WalletPaymentStrategy(),
                    branch1,
                    branch2,
                    100.0
            );
            System.out.println(Thread.currentThread().getName() + " ended!");
        });

        t1.start();
        t2.start();

        System.out.println("--------------");
    }
}
