package design_problems.personal.bookmyshow.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Screen {
    private final String id;
    private final Map<String, Seat> seats;

    public Screen(String id) {
        this.id = id;
        this.seats = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void addSeat(Seat seat) {
        seats.put(seat.getId(), seat);
    }

    public List<Seat> getSeats() {
        return new ArrayList<>(seats.values());
    }
}
