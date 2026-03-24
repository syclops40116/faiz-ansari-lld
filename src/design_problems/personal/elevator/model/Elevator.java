package design_problems.personal.elevator.model;

import design_problems.personal.elevator.enums.Direction;

import java.util.TreeSet;

public class Elevator {

    private final int id;

    private int currentFloor;
    private Direction direction;

    // Sorted stops
    private final TreeSet<Integer> upwardStops;
    private final TreeSet<Integer> downwardStops;

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;

        this.upwardStops = new TreeSet<>();
        this.downwardStops = new TreeSet<>((a, b) -> b - a); // descending
    }

    // Add request to this elevator
    public void addRequest(Request request) {

        int src = request.getSourceFloor();
        int dest = request.getDestinationFloor();

        if (src > currentFloor) {
            upwardStops.add(src);
        } else {
            downwardStops.add(src);
        }

        if (dest > src) {
            upwardStops.add(dest);
        } else {
            downwardStops.add(dest);
        }

        updateDirection();
    }

    // Move elevator step by step
    public void move() {

        if (direction == Direction.UP) {
            moveUp();
        } else if (direction == Direction.DOWN) {
            moveDown();
        }
    }

    private void moveUp() {

        if (upwardStops.isEmpty()) {
            direction = Direction.IDLE;
            return;
        }

        int nextStop = upwardStops.pollFirst();

        System.out.println("Elevator " + id +
                " moving UP to " + nextStop);

        currentFloor = nextStop;

        if (upwardStops.isEmpty()) {
            direction = Direction.IDLE;
        }
    }

    private void moveDown() {

        if (downwardStops.isEmpty()) {
            direction = Direction.IDLE;
            return;
        }

        int nextStop = downwardStops.pollFirst();

        System.out.println("Elevator " + id +
                " moving DOWN to " + nextStop);

        currentFloor = nextStop;

        if (downwardStops.isEmpty()) {
            direction = Direction.IDLE;
        }
    }

    private void updateDirection() {

        if (!upwardStops.isEmpty()) {
            direction = Direction.UP;
        } else if (!downwardStops.isEmpty()) {
            direction = Direction.DOWN;
        } else {
            direction = Direction.IDLE;
        }
    }

    public boolean isIdle() {
        return direction == Direction.IDLE;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getId() {
        return id;
    }
}
