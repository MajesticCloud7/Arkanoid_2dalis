package objects;

public class SilverBrick extends Brick {
    SilverBrickState notCrackedState;
    SilverBrickState crackedState;
    SilverBrickState state;

    public SilverBrick(Position position) {
        super(position);
        super.setLives(2);
        super.setPoints();
        notCrackedState = new NotCrackedState(this);
        crackedState = new CrackedState(this);
        state = notCrackedState;
    }

    @Override
    public void decreaseLives() {
        super.decreaseLives();
        state.decreaseLives();
    }

    public void setState(SilverBrickState state) {
        this.state = state;
    }

    public boolean isCracked() {
        return state == crackedState;
    }

    public SilverBrickState getCrackedState() {
        return crackedState;
    }
}