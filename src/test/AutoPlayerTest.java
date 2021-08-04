package test;

import org.junit.*;
import static org.junit.Assert.*;
import game.*;

public class AutoPlayerTest {
    AutoPlayer a;

    @Before()
    public void setUp() {
        Game g = new Game(null,null,null,null,null);
        a = new AutoPlayer("IA", 15, g, Difficulty.EASY);
    }

    @Test()
    public void testAutoPlayer() {
        assertNotNull(a);
    }

    @Test()
    public void testDifficulty() {
        assertEquals(Difficulty.EASY, a.getDifficulty());
        a.setDifficulty(Difficulty.HARD);
        assertEquals(Difficulty.HARD, a.getDifficulty());
    }

    @Test()
    public void testName() {
        assertTrue(a.getName().equals("IA"));
        a.setName("Fabrice");
        assertTrue(a.getName().equals("Fabrice"));
    }

    @Test()
    public void testEcus() {
        a.modifyNbEcus(15);
        assertEquals(30, a.getNbEcus());
    }

    @After()
    public void clean() {
        a = null;
    }

}
