package design_problems.personal.due.ride_sharing_uber_ola.models;

public class Location {

    double lat;
    double lon;

    public Location(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double distanceTo(Location other) {
        double dx = this.lat - other.lat;
        double dy = this.lon - other.lon;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString() {
        return "(" + lat + "," + lon + ")";
    }
}

