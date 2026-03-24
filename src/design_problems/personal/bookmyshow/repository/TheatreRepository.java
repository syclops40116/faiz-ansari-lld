package design_problems.personal.bookmyshow.repository;

import design_problems.personal.bookmyshow.model.Theatre;

import java.util.HashMap;
import java.util.Map;

public class TheatreRepository {
    private final Map<String, Theatre> map = new HashMap<>();

    public void save(Theatre theatre) {
        map.put(theatre.getId(), theatre);
    }

    public Theatre getById(String id) {
        return map.get(id);
    }
}
