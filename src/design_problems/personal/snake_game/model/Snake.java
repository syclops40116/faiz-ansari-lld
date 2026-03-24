package design_problems.personal.snake_game.model;

import design_problems.personal.snake_game.enums.Direction;

import java.util.Deque;
import java.util.LinkedList;

public class Snake {

    private Deque<Position> body = new LinkedList<>();

    private Direction direction = Direction.RIGHT;

    public Snake(Position start) {
        body.add(start);
    }

    public Position getHead() {
        return body.peekFirst();
    }

    public void setDirection(Direction d) {
        this.direction = d;
    }

    public boolean contains(Position p) {
        return body.contains(p);
    }

    public void move(Position next, boolean grow) {

        body.addFirst(next);

        if (!grow) {
            body.removeLast();
        }
    }

    public Deque<Position> getBody() {
        return body;
    }

    public Direction getDirection() {
        return direction;
    }
}
