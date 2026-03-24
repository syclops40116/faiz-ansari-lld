package design_problems.personal.snake_game;

import design_problems.personal.snake_game.enums.Direction;
import design_problems.personal.snake_game.game.GameController;
import design_problems.personal.snake_game.render.ConsoleRenderer;
import design_problems.personal.snake_game.render.Renderer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)
            throws Exception {

        GameController game = new GameController(10, 10);
        Renderer gameRenderer = new ConsoleRenderer();

        Scanner sc = new Scanner(System.in);

        while (!game.isGameOver()) {

            game.tick();

            gameRenderer.render(game);

            System.out.print("Enter direction (w/a/s/d) or Enter to continue: ");

            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            char dir = input.charAt(0);

            Direction d = null;

            switch (dir) {

                case 'w' -> d = Direction.UP;
                case 's' -> d = Direction.DOWN;
                case 'a' -> d = Direction.LEFT;
                case 'd' -> d = Direction.RIGHT;
            }

            if (d != null) {
                game.changeDirection(d);
            }
        }

        System.out.println("Game Over!");
        System.out.println("Score: " + game.getScore());
    }
}
