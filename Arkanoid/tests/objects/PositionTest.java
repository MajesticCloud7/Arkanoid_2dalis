package objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {
    private Position position;

    @Before
    public void setUp() {
        position = new Position(7, 7);
    }

    @Test
    public void testAddToX() {
        Position actual = position.addToX(3);
        Position expected = new Position(10, 7);
        assertEquals(expected, actual);
    }

    @Test
    public void testAddToY() {
        Position actual = position.addToY(3);
        Position expected = new Position(7, 10);
        assertEquals(expected, actual);
    }

    @Test
    public void testToLeft() {
        Position actual = position.toLeft();
        Position expected = new Position(6, 7);
        assertEquals(expected, actual);
    }

    @Test
    public void testToRight() {
        Position actual = position.toRight();
        Position expected = new Position(8, 7);
        assertEquals(expected, actual);
    }

    @Test
    public void testAbove() {
        Position actual = position.above();
        Position expected = new Position(7, 6);
        assertEquals(expected, actual);
    }

    @Test
    public void testBelow() {
        Position actual = position.below();
        Position expected = new Position(7, 8);
        assertEquals(expected, actual);
    }

    @Test
    public void testEquals() {
        assertEquals(position, new Position(7, 7));
    }
}