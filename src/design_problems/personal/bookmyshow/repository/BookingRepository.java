package design_problems.personal.bookmyshow.repository;

import design_problems.personal.bookmyshow.model.Booking;

import java.util.HashMap;
import java.util.Map;

public class BookingRepository {
    private final Map<String, Booking> map  = new HashMap<>();

    public void save(Booking booking) {
        map.put(booking.getBookingId(), booking);
    }

    public Booking getById(String id) {
        return map.get(id);
    }
}
