package design_problems.personal.bookmyshow.model;

import java.util.HashMap;
import java.util.Map;

public class Theatre {
    private final String id;
    private final String name;
    private final String city;
    private final Map<String, Screen> screens;

    public Theatre(String id, String name, String city) {
        this.city = city;
        this.id = id;
        this.name = name;
        screens = new HashMap<>();
    }

    public void addScreen(Screen screen) {
        screens.put(screen.getId(), screen);
    }

    public Screen getScreen(String id) {
        return screens.get(id);
    }

    public String getId() {
        return id;
    }
}
