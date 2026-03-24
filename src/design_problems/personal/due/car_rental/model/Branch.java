package design_problems.personal.due.car_rental.model;

import design_problems.personal.due.car_rental.enums.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Branch {
    private String id;
    private String city;

    private Map<VehicleType, List<Vehicle>> vehicles = new HashMap<>();

    public Branch(String id, String city){
        this.id = id;
        this.city = city;
    }

    public List<Vehicle> getVehiclesByType(VehicleType type) {
        return vehicles.getOrDefault(type, new ArrayList<>());
    }

    public void addVehicle(Vehicle vehicle){
        vehicles.computeIfAbsent(vehicle.getType(), k -> new ArrayList<>()).add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle){
        List<Vehicle> list = vehicles.get(vehicle.getType());
        if(list != null) list.remove(vehicle);
    }

    public String getId() {
        return id;
    }

    public String getCity() {
        return city;
    }
}