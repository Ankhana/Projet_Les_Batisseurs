package test;

import org.junit.*;
import static org.junit.Assert.*;
import game.*;

public class GameTest {
    Game game1;
    Mode m;

    @Before()
    public void setUp() {
        m = Mode.HH;
        Game g = new Game(null,null,null,null,null);
        HumanPlayer h1 = new HumanPlayer("Fabrice", 15, g);
        HumanPlayer h2 = new HumanPlayer("Fabrice", 15, g);
        HumanPlayer h3 = new HumanPlayer("Fabrice", 15, g);
        HumanPlayer h4 = new HumanPlayer("Fabrice", 15, g);
        game1 = new Game(m, h1, h2, h3, h4);
    }

    @Test()
    public void testGame() {
        assertNotNull(game1);
    }

    @Test()
    public void testMode() {
        assertEquals(Mode.HH, game1.getMode());
    }

    @Test()
    public void testJoueurs() {
        assertNotNull(game1.getPlayer1());
        assertNotNull(game1.getPlayer2());
        assertNotNull(game1.getPlayer3());
        assertNotNull(game1.getPlayer4());
        assertNotNull(game1.getCurrentPlayer());
    }

    @Test()
    public void testDeck() {
        assertNotNull(game1.getDeck());
    }

    @After()
    public void clean() {
        game1 = null;
        m = null;
    }

}
