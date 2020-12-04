package objects;

public class CrackedState implements SilverBrickState {
    SilverBrick silverBrick;

    public CrackedState(SilverBrick silverBrick) {
        this.silverBrick = silverBrick;
    }

    @Override
    public void decreaseLives() {
    }
}
