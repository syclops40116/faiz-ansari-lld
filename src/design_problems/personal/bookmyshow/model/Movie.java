package design_problems.personal.bookmyshow.model;

public class Movie {
    private String id;
    private String title;
    private int durationInMinutes;

    public Movie(String id, String title, int durationInMinutes) {
        this.id = id;
        this.title = title;
        this.durationInMinutes = durationInMinutes;
    }

    public String getId() {
        return id;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public String getTitle() {
        return title;
    }
}

