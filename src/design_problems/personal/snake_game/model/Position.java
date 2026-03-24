package design_problems.personal.snake_game.model;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Position p = (Position) obj;

        return this.x == p.x && this.y == p.y;
    }
}
