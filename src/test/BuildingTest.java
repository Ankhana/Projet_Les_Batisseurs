package test;

import org.junit.*;
import static org.junit.Assert.*;
import game.*;

public class BuildingTest {
    Building b;

    @Before()
    public void setUp() {
        b = new Building("Eglise",1,2,3,4,5,6, "../data/images/cartes/batiments/chantiers/1.png", "../data/images/cartes/batiments/finis/1.png");
    }

    @Test()
    public void testBuilding() {
        assertNotNull(b);
    }

    @Test()
    public void testGet() {
        assertEquals(1, b.getStone());
        assertEquals(2, b.getWood());
        assertEquals(3, b.getKnowledge());
        assertEquals(4, b.getTile());
        assertEquals(5, b.getVictoryPoint());
        assertEquals(6, b.getEcus());
        assertTrue(b.getName().equals("Eglise"));
    }

    @After()
    public void clean() {
        b = null;
    }

}
