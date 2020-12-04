package objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VausTest {
    private Vaus vaus;

    @Before
    public void setUp() {
        vaus = new Vaus(new Position(16, 29), 4);
    }

    @Test
    public void getEndingPosition() {
        Position expected = new Position(19, 29);
        Position actual = vaus.getEndingPosition();
        assertEquals(expected, actual);
    }
}