package design_problems.personal.bookmyshow.service;

import design_problems.personal.bookmyshow.model.Movie;
import design_problems.personal.bookmyshow.repository.MovieRepository;

public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie createMovie(String id, String title, int durationInMinutes) {
        Movie movie = new Movie(id, title, durationInMinutes);
        movieRepository.save(movie);
        return movie;
    }

    public Movie getMovie(String id) {
        return movieRepository.getById(id);
    }
}
