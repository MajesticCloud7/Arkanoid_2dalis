package game;

import objects.Brick;

import java.util.ArrayList;
import java.util.List;

public class GameRules {
    private final GameRound round;
    private final BallMovingRules ballMovingRules;
    private boolean gameOver = false;

    public GameRules(GameRound round) {
        this.round = round;
        ballMovingRules = new BallMovingRules(round);
    }

    public void processInput(int key) {
        switch (key) {
            case 'a':
                if (round.getPlayfield().isEmpty(round.getVaus().getPosition().toLeft())) {
                    round.getVaus().moveLeft();
                    if (!round.getBall().isReleased())
                        round.getBall().moveLeft();
                }
                break;
            case 'd':
                if (round.getPlayfield().isEmpty(round.getVaus().getEndingPosition().toRight())) {
                    round.getVaus().moveRight();
                    if (!round.getBall().isReleased())
                        round.getBall().moveRight();
                }
                break;
            case 'w':
                if (!round.getBall().isReleased())
                    round.getBall().setReleased(true);
                break;
            case 'x':
                setGameOver(true);
                break;
        }
    }

    public void moveBall() {
        if (round.getBall().isReleased()) {
            round.getBall().move();
            if (ballMovingRules.ballHitBrickTopOrBottom())
                round.getBall().invertDy();
            else if (ballMovingRules.ballHitBrickSide())
                round.getBall().invertDx();
            else if (ballMovingRules.ballHitBrickCorner()) {
                round.getBall().invertDx();
                round.getBall().invertDy();
            }
        }
        if (ballMovingRules.ballHitVaus()) {
            round.getBall().invertDy();
            if (ballMovingRules.ballHitVausEdge())
                round.getBall().invertDx();
        }
        if (ballMovingRules.ballHitSideWall())
            round.getBall().invertDx();
        if (ballMovingRules.ballHitTopWall())
            round.getBall().invertDy();
        if (isBallOutOfPlayfield())
            setGameOver(true);
    }

    public boolean isBallOutOfPlayfield() {
        return round.getBall().isOutOfPlayfield(round.getPlayfield());
    }

    public void removeBricks() {
        List<Brick> toRemove = destroyedBricks();
        for (Brick brick : toRemove)
            round.addToScore(brick.getPoints());
        round.getBricks().removeAll(toRemove);
        if (round.getBricks().isEmpty()) {
            round.setResult("*~*~*VICTORY*~*~*");
            setGameOver(true);
        }
    }

    public List<Brick> destroyedBricks() {
        List<Brick> destroyedBricks = new ArrayList<>();
        for (Brick brick : round.getBricks())
            if (brick.isDestroyed())
                destroyedBricks.add(brick);
        return destroyedBricks;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
