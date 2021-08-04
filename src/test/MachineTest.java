package test;

import org.junit.*;
import static org.junit.Assert.*;
import game.*;

public class MachineTest {
    Machine m;

    @Before()
    public void setUp() {
        m = new Machine("Grue", 1, 2, 3, 4, 5, 6, 7, 8, 9, "../data/images/cartes/machines/chantiers/1.png", "../data/images/cartes/machines/finis/1.png");
    }

    @Test()
    public void testMachine() {
        assertNotNull(m);
    }

    @Test()
    public void testGet() {
        assertEquals(1, m.getStone());
        assertEquals(2, m.getWood());
        assertEquals(3, m.getKnowledge());
        assertEquals(4, m.getTile());
        assertEquals(5, m.getVictoryPoint());
        assertEquals(6, m.getStoneOffered());
        assertEquals(7, m.getWoodOffered());
        assertEquals(8, m.getKnowledgeOffered());
        assertEquals(9, m.getTileOffered());
        assertTrue(m.getName().equals("Grue"));
    }

    @After()
    public void clean() {
        m = null;
    }

}
