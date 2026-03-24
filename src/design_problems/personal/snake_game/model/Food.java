package design_problems.personal.snake_game.model;

import java.util.Random;

public class Food {

    private Position position;

    public Food(Position p) {
        this.position = p;
    }

    public Position getPosition() {
        return position;
    }

    public static Food generate(Board board) {

        Random r = new Random();

        int x = r.nextInt(board.getWidth());
        int y = r.nextInt(board.getHeight());

        return new Food(new Position(x, y));
    }
}
