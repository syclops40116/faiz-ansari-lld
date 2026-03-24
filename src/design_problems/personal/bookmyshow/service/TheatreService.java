package design_problems.personal.bookmyshow.service;

import design_problems.personal.bookmyshow.model.Screen;
import design_problems.personal.bookmyshow.model.Seat;
import design_problems.personal.bookmyshow.model.Theatre;
import design_problems.personal.bookmyshow.repository.TheatreRepository;

public class TheatreService {

    private final TheatreRepository theatreRepository;

    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public Theatre createTheatre(String id, String name, String city) {
        Theatre theatre = new Theatre(id, name, city);
        theatreRepository.save(theatre);
        return theatre;
    }

    public Theatre getTheatre(String id) {
        return theatreRepository.getById(id);
    }

    public void addScreen(String id, Screen screen) {
        Theatre theatre = theatreRepository.getById(id);
        theatre.addScreen(screen);
    }

    public void addSeat(String theatreId, String screenId, Seat seat) {
        Theatre theatre = theatreRepository.getById(theatreId);
        Screen screen = theatre.getScreen(screenId);
        screen.addSeat(seat);
    }
}
