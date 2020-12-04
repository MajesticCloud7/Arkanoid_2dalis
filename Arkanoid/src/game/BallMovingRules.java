package game;

import objects.Brick;

public class BallMovingRules {
    private final GameRound round;

    public BallMovingRules(GameRound round) {
        this.round = round;
    }

    public boolean ballHitBrickTopOrBottom() {
        for (Brick brick : round.getBricks())
            for (int i = 0; i < Brick.getSize(); i++)
                if (round.getBall().isGoingDown()
                        && round.getBall().getPosition().below().equals(brick.getStartingPosition().addToX(i))
                        || !round.getBall().isGoingDown()
                        && round.getBall().getPosition().above().equals(brick.getStartingPosition().addToX(i))) {
                    brick.decreaseLives();
                    return true;
                }
        return false;
    }

    public boolean ballHitBrickSide() {
        for (Brick brick : round.getBricks())
            for (int i = 0; i < Brick.getSize(); i++)
                if (round.getBall().getPosition().toLeft().equals(brick.getStartingPosition().addToX(i))
                        || round.getBall().getPosition().toRight().equals(brick.getStartingPosition().addToX(i))) {
                    brick.decreaseLives();
                    return true;
                }
        return false;
    }

    public boolean ballHitBrickCorner() {
        for (Brick brick : round.getBricks())
            if (ballHitBottomLeftBrickCorner(brick)
                    || ballHitBottomRightBrickCorner(brick)
                    || ballHitTopLeftBrickCorner(brick)
                    || ballHitTopRightBrickCorner(brick)) {
                brick.decreaseLives();
                return true;
            }
        return false;
    }

    public boolean ballHitBottomLeftBrickCorner(Brick brick) {
        return round.getBall().getPosition().toRight().above().equals(brick.getStartingPosition())
                && round.getBall().getNextPosition().equals(brick.getStartingPosition());
    }

    public boolean ballHitBottomRightBrickCorner(Brick brick) {
        return round.getBall().getPosition().toLeft().above().equals(brick.getEndingPosition())
                && round.getBall().getNextPosition().equals(brick.getEndingPosition());
    }

    public boolean ballHitTopLeftBrickCorner(Brick brick) {
        return round.getBall().getPosition().toRight().below().equals(brick.getStartingPosition())
                && round.getBall().getNextPosition().equals(brick.getStartingPosition());
    }

    public boolean ballHitTopRightBrickCorner(Brick brick) {
        return round.getBall().getPosition().toLeft().below().equals(brick.getEndingPosition())
                && round.getBall().getNextPosition().equals(brick.getEndingPosition());
    }

    public boolean ballHitVaus() {
        if (round.getBall().isGoingDown())
            for (int i = 0; i < round.getVaus().getSize(); i++)
                if (round.getBall().getPosition().below().equals(round.getVaus().getPosition().addToX(i)))
                    return true;
        return false;
    }

    public boolean ballHitVausEdge() {
        return round.getBall().getPosition().below().equals(round.getVaus().getPosition())
                && !round.getBall().isGoingLeft()
                || round.getBall().getPosition().below().equals(round.getVaus().getEndingPosition())
                && round.getBall().isGoingLeft();
    }

    public boolean ballHitSideWall() {
        return round.getPlayfield().isWall(round.getBall().getPosition().toLeft())
                || round.getPlayfield().isWall(round.getBall().getPosition().toRight());
    }

    public boolean ballHitTopWall() {
        return round.getPlayfield().isWall(round.getBall().getPosition().above());
    }
}
