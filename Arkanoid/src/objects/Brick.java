package objects;

public abstract class Brick {
    private static final int size = 2;
    private final Position position;
    private int lives;
    private int points = 50;

    public Brick(Position position) {
        this.position = position;
    }

    public static int getSize() {
        return size;
    }

    public void decreaseLives() {
        this.lives--;
    }

    public boolean isAt(int x, int y) {
        return position.getX() == x && position.getY() == y;
    }

    public boolean isDestroyed() {
        return lives == 0;
    }

    public Position getStartingPosition() {
        return position;
    }

    public Position getEndingPosition() {
        return position.addToX(size - 1);
    }

    public int getPoints() {
        return points;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setPoints() {
        points *= lives;
    }

    public abstract boolean isCracked();
}
