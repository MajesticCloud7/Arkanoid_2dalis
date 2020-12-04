package objects;

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position addToX(int dx) {
        return new Position(x + dx, y);
    }

    public Position addToY(int dy) {
        return new Position(x, y + dy);
    }

    public Position toLeft() {
        return new Position(x - 1, y);
    }

    public Position toRight() {
        return new Position(x + 1, y);
    }

    public Position above() {
        return new Position(x, y - 1);
    }

    public Position below() {
        return new Position(x, y + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Position position = (Position) o;
        return getX() == position.getX() &&
                getY() == position.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
