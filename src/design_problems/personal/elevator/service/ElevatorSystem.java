package design_problems.personal.elevator.service;

import design_problems.personal.elevator.model.Elevator;
import design_problems.personal.elevator.model.Request;

import java.util.*;

public class ElevatorSystem {

    private final Dispatcher dispatcher;

    public ElevatorSystem(int numberOfElevators) {

        List<Elevator> elevators = new ArrayList<>();

        for (int i = 1; i <= numberOfElevators; i++) {
            elevators.add(new Elevator(i));
        }

        this.dispatcher = new Dispatcher(elevators);
    }

    public void requestLift(int src, int dest) {

        Request request = new Request(src, dest);

        dispatcher.submitRequest(request);
    }

    public void step() {
        dispatcher.moveAllElevators();
    }
}

