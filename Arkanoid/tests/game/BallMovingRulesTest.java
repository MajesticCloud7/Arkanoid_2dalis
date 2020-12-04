package game;

import objects.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BallMovingRulesTest {
    private BallMovingRules rules;

    @Before
    public void setUp() {
        Playfield playfield = Playfield.getInstance();
        List<Brick> bricks = new ArrayList<>();
        bricks.add(new WhiteBrick(new Position(3, 5)));
        bricks.add(new SilverBrick(new Position(5, 5)));
        Vaus vaus = new Vaus(new Position(12, 29), 4);
        Ball ball = new Ball(new Position(5, 6));
        ball.setReleased(true);
        GameRound round = new GameRound(playfield, bricks, vaus, ball);

        rules = new BallMovingRules(round);
    }

    @Test
    public void testBallHitBrickTopOrBottom() {
        assertTrue(rules.ballHitBrickTopOrBottom());
    }

    @Test
    public void testBallHitBrickCorner() {
        assertFalse(rules.ballHitBrickCorner());
    }

    @Test
    public void testBallHitBrickSide() {
        assertFalse(rules.ballHitBrickSide());
    }
}