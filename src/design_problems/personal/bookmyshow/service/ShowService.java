package design_problems.personal.bookmyshow.service;

import design_problems.personal.bookmyshow.model.Movie;
import design_problems.personal.bookmyshow.model.Screen;
import design_problems.personal.bookmyshow.model.Show;
import design_problems.personal.bookmyshow.model.Theatre;
import design_problems.personal.bookmyshow.repository.MovieRepository;
import design_problems.personal.bookmyshow.repository.ShowRepository;
import design_problems.personal.bookmyshow.repository.TheatreRepository;

import java.time.LocalDateTime;
import java.util.List;

public class ShowService {
    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final TheatreRepository theatreRepository;

    public ShowService(ShowRepository showRepository, MovieRepository movieRepository, TheatreRepository theatreRepository) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.theatreRepository = theatreRepository;
    }

    public Show createShow(String id, String movieId, String theatreId, String screenId, LocalDateTime startTime) {
        Movie movie = movieRepository.getById(movieId);
        Theatre theatre = theatreRepository.getById(theatreId);
        Screen screen = theatre.getScreen(screenId);
        Show show = new Show(id, movie, theatre, screen, startTime);
        showRepository.save(show);
        return show;
    }

    public List<Show> getShowsByMovieTitle(String title) {
        return showRepository.getAll()
                .stream()
                .filter(show -> show.getMovie().getTitle().equals(title))
                .toList();
    }
}
