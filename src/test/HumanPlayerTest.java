package test;

import org.junit.*;
import static org.junit.Assert.*;
import game.*;

public class HumanPlayerTest {
    HumanPlayer h1;

    @Before()
    public void setUp() {
        Game g = new Game(null,null,null,null,null);
        h1 = new HumanPlayer("Fabrice", 15, g);
    }

    @Test()
    public void testHumanPlayer() {
        assertNotNull(h1);
    }

    @Test()
    public void testNom() {
        assertEquals("Fabrice", h1.getName());
        h1.setName("Yoko");
        assertEquals("Yoko", h1.getName());
    }

    @Test()
    public void testEcus() {
        h1.modifyNbEcus(3);
        assertEquals(18, h1.getNbEcus());
    }

    @Test()
    public void testNotNull() {
        assertNotNull(h1.getPlayerDeck());
    }

    @Test()
    public void testRemainingTurn() {
        assertEquals(3, h1.getRemainingTurn());
    }

    @After()
    public void clean() {
        h1 = null;
    }

}
