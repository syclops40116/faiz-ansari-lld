package design_problems.personal.snake_game.game;

import design_problems.personal.snake_game.enums.Direction;
import design_problems.personal.snake_game.model.Board;
import design_problems.personal.snake_game.model.Food;
import design_problems.personal.snake_game.model.Position;
import design_problems.personal.snake_game.model.Snake;

public class GameController {

    private final Board board;
    private final Snake snake;
    private Food food;

    private boolean gameOver = false;
    private int score = 0;

    public GameController(int w, int h) {

        board = new Board(w, h);
        snake = new Snake(new Position(2, 2));
        food = Food.generate(board);
    }

    public void changeDirection(Direction d) {
        snake.setDirection(d);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getScore() {
        return score;
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

    public void tick() {

        if (gameOver) return;

        Position head = snake.getHead();
        Position next = nextPosition(head);

        // Wall collision
        if (!board.isInside(next)) {
            gameOver = true;
            return;
        }

        // Self collision
        if (snake.contains(next)) {
            gameOver = true;
            return;
        }

        // Eat food
        if (next.equals(food.getPosition())) {

            snake.move(next, true);
            score += 10;
            food = Food.generate(board);
        }

        // Normal move
        else {
            snake.move(next, false);
        }
    }

    public Board getBoard() {
        return board;
    }


    private Position nextPosition(Position p) {

        return switch (snake.getDirection()) {
            case UP -> new Position(p.x, p.y - 1);
            case DOWN -> new Position(p.x, p.y + 1);
            case LEFT -> new Position(p.x - 1, p.y);
            case RIGHT -> new Position(p.x + 1, p.y);
        };

    }
}

