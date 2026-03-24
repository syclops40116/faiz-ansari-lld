package design_problems.personal.bookmyshow.service;

import design_problems.personal.bookmyshow.enums.BookingStatus;
import design_problems.personal.bookmyshow.enums.PaymentType;
import design_problems.personal.bookmyshow.factory.PaymentStrategyFactory;
import design_problems.personal.bookmyshow.model.Booking;
import design_problems.personal.bookmyshow.model.Seat;
import design_problems.personal.bookmyshow.model.Show;
import design_problems.personal.bookmyshow.repository.BookingRepository;
import design_problems.personal.bookmyshow.strategy.locking.LockProvider;
import design_problems.personal.bookmyshow.strategy.payment.PaymentStrategy;

import java.util.List;
import java.util.UUID;

public class BookingService {
    private final BookingRepository bookingRepository;
    private final LockProvider lockProvider;

    private static final long TTLMs = 5000L;

    public BookingService(BookingRepository bookingRepository, LockProvider lockProvider) {
        this.bookingRepository = bookingRepository;
        this.lockProvider = lockProvider;
    }

    public Booking createBooking(String userId, Show show, List<String> seatIds) {
        for (String seatId: seatIds) {
            String key = show.getId() + ":" + seatId;
            if(!lockProvider.tryLock(key, userId, TTLMs)) {
                throw new RuntimeException("Seat " + seatId + " is temporarily unavailable");
            }
        }

        double totalPrice = 0;
        for (Seat seat: show.getSeats()) {
            if(seatIds.contains(seat.getId())) {
                totalPrice += seat.getPrice();
            }
        }

        Booking booking =
                new Booking(
                        UUID.randomUUID().toString(),
                        show.getId(),
                        userId,
                        seatIds,
                        totalPrice
                );

        bookingRepository.save(booking);

        return booking;
    }

    public void confirmBooking(Booking booking, PaymentType paymentType) {
        for (String seatId: booking.getSeatIds()) {
            String key = booking.getShowId() + ":" + seatId;

            if(lockProvider.isLockExpired(key)) {
                booking.setStatus(BookingStatus.CANCELLED);
                throw new RuntimeException("Seat " + seatId + " is temporarily unavailable");
            }
        }
        booking.setPaymentType(paymentType);
        PaymentStrategy paymentStrategy = PaymentStrategyFactory.createPaymentStrategy(paymentType);
        paymentStrategy.pay(booking, booking.getAmount());

        booking.setStatus(BookingStatus.CONFIRMED);
        System.out.println("Booking confirmed: " + booking.getBookingId());
    }
}
