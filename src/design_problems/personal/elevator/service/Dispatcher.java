package design_problems.personal.elevator.service;

import design_problems.personal.elevator.enums.Direction;
import design_problems.personal.elevator.model.Elevator;
import design_problems.personal.elevator.model.Request;

import java.util.*;

public class Dispatcher {

    private final List<Elevator> elevators;

    // Pending requests
    private final Queue<Request> waitingQueue;

    public Dispatcher(List<Elevator> elevators) {
        this.elevators = elevators;
        this.waitingQueue = new LinkedList<>();
    }

    public void submitRequest(Request request) {

        Elevator best = findBestElevator(request);

        if (best != null) {
            System.out.println("Assigned to Elevator " + best.getId());
            best.addRequest(request);
        } else {
            System.out.println("No elevator free. Queued request.");
            waitingQueue.offer(request);
        }
    }

    // Find nearest idle elevator
    private Elevator findBestElevator(Request request) {

        Elevator best = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {

            if(isSuitable(request, elevator)) {
                return elevator;
            }

            if(elevator.isIdle()) {
                int distance =
                        Math.abs(elevator.getCurrentFloor()
                                - request.getSourceFloor());

                if (distance < minDistance) {
                    minDistance = distance;
                    best = elevator;
                }
            }
        }

        return best;
    }

    private boolean isSuitable(Request request, Elevator elevator) {
        if(request.getDirection() == elevator.getDirection()) {
            if(request.getDirection() == Direction.DOWN && elevator.getCurrentFloor() > request.getSourceFloor()) {
                return true;
            }
            return request.getDirection() == Direction.UP && elevator.getCurrentFloor() < request.getSourceFloor();
        }
        return false;
    }

    // Called periodically
    public void processQueue() {

        if (waitingQueue.isEmpty()) return;

        Iterator<Request> iterator = waitingQueue.iterator();

        while (iterator.hasNext()) {

            Request req = iterator.next();

            Elevator elevator = findBestElevator(req);

            if (elevator != null) {
                System.out.println("Queued request assigned now.");
                elevator.addRequest(req);
                iterator.remove();
            }
        }
    }

    public void moveAllElevators() {

        for (Elevator elevator : elevators) {
            elevator.move();
        }

        processQueue();
    }
}
