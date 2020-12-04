package objects;

public class WhiteBrick extends Brick {
    public WhiteBrick(Position position) {
        super(position);
        super.setLives(1);
    }

    @Override
    public boolean isCracked() {
        return false;
    }
}
