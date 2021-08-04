package test;

import org.junit.*;
import static org.junit.Assert.*;
import game.*;

public class DeckTest {
    Deck d;
    Builder b;
    Building b2;

    @Before()
    public void setUp() {
        d = new Deck();
        b = new Builder("Jean",5,4,2,1,18, "../data/images/cartes/ouvriers/1.png");
        d.addBuilder(b);
        b2 = new Building("Pont",5,4,2,1, 5, 8, "../data/images/cartes/batiments/chantiers/1.png", "../data/images/cartes/batiments/finis/1.png");
        d.addBuilding(b2);
    }

    @Test()
    public void testDeck() {
        assertNotNull(d);
        assertNotNull(b);
        assertNotNull(b2);
    }

    @Test()
    public void testGetter() {
        assertEquals(b, d.getBuilderCard(0));
        assertEquals(b2, d.getBuildingCard(0));
    }

    @After()
    public void clean() {
        d = null;
        b = null;
        b2 = null;
    }

}
