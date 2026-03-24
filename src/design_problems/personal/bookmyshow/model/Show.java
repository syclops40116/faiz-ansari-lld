package design_problems.personal.bookmyshow.model;

import java.time.LocalDateTime;
import java.util.List;

public class Show {
    private String id;
    private Movie movie;
    private Theatre theatre;
    private Screen screen;
    private LocalDateTime start;

    public Show(String id, Movie movie, Theatre theatre, Screen screen, LocalDateTime start) {
        this.id = id;
        this.movie = movie;
        this.theatre = theatre;
        this.screen = screen;
        this.start = start;
    }

    public List<Seat> getSeats() {
        return screen.getSeats();
    }

    public String getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }
}
