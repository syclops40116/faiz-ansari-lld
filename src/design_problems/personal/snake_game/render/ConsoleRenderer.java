package design_problems.personal.snake_game.render;

import design_problems.personal.snake_game.game.GameController;
import design_problems.personal.snake_game.model.Board;
import design_problems.personal.snake_game.model.Position;
import design_problems.personal.snake_game.model.Snake;

public class ConsoleRenderer implements Renderer{

    @Override
    public void render(GameController game) {

        Board board = game.getBoard();
        Snake snake = game.getSnake();
        Position food = game.getFood().getPosition();

        int w = board.getWidth();
        int h = board.getHeight();

        char[][] grid = new char[h][w];

        // Fill
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                grid[i][j] = '.';
            }
        }

        // Food
        grid[food.y][food.x] = 'F';

        // Snake
        boolean head = true;

        for (Position p : snake.getBody()) {

            grid[p.y][p.x] = head ? 'H' : 'S';
            head = false;
        }

        // Print
        System.out.println("Score: " + game.getScore());

        for (int i = 0; i < h; i++) {

            for (int j = 0; j < w; j++) {
                System.out.print(grid[i][j] + " ");
            }

            System.out.println();
        }

        System.out.println("--------------------");
    }
}
