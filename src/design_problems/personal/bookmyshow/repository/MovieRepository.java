package design_problems.personal.bookmyshow.repository;

import design_problems.personal.bookmyshow.model.Movie;

import java.util.HashMap;
import java.util.Map;

public class MovieRepository {
    private final Map<String, Movie> map  = new HashMap<>();

    public void save(Movie movie) {
        map.put(movie.getId(), movie);
    }

    public Movie getById(String id) {
        return map.get(id);
    }
}
