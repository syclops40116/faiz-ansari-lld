package design_problems.personal.snake_game.model;

public class Board {

    private int width;
    private int height;

    public Board(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isInside(Position p) {

        return p.x >= 0 && p.y >= 0
                && p.x < width
                && p.y < height;
    }
}

