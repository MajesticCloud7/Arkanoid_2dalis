package game;

import objects.*;

import java.util.List;

public class GameRound {
    private Playfield playfield;
    private List<Brick> bricks;
    private Vaus vaus;
    private Ball ball;
    private int score = 0;
    private String result = "*~*~*GAME~OVER*~*~*";

    public GameRound(Playfield playfield, List<Brick> bricks, Vaus vaus, Ball ball) {
        setPlayfield(playfield);
        setBricks(bricks);
        setVaus(vaus);
        setBall(ball);
    }

    public void addToScore(int points) {
        this.score += points;
    }

    public void createBricks(int rows) {
        int startY = 5;
        int endY = startY + rows;
        for (int y = startY; y < endY; y++)
            for (int x = 1; x < playfield.getWidth() - 2; x += 2)
                if (y % 2 == 0)
                    this.bricks.add(new WhiteBrick(new Position(x, y)));
                else
                    this.bricks.add(new SilverBrick(new Position(x, y)));
    }

    public boolean isBrickAt(GameRound gameRound, int x, int y) {
        for (Brick brick : gameRound.getBricks())
            if (brick.isAt(x, y) || brick.isAt(x - 1, y))
                return true;
        return false;
    }

    public Playfield getPlayfield() {
        return playfield;
    }

    public void setPlayfield(Playfield playfield) {
        this.playfield = playfield;
    }

    public Vaus getVaus() {
        return vaus;
    }

    public void setVaus(Vaus vaus) {
        this.vaus = vaus;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public void setBricks(List<Brick> bricks) {
        this.bricks = bricks;
    }

    public int getScore() {
        return score;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
