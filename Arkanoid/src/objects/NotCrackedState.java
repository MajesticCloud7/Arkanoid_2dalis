package objects;

public class NotCrackedState implements SilverBrickState {
    SilverBrick silverBrick;

    public NotCrackedState(SilverBrick silverBrick) {
        this.silverBrick = silverBrick;
    }

    @Override
    public void decreaseLives() {
        silverBrick.setState(silverBrick.getCrackedState());
    }
}
