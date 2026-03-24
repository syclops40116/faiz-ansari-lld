package design_problems.personal.elevator.model;

import design_problems.personal.elevator.enums.Direction;

public class Request {

    private final int sourceFloor;
    private final int destinationFloor;

    public Request(int sourceFloor, int destinationFloor) {
        this.sourceFloor = sourceFloor;
        this.destinationFloor = destinationFloor;
    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public Direction getDirection() {
        return destinationFloor > sourceFloor
                ? Direction.UP
                : Direction.DOWN;
    }
}
