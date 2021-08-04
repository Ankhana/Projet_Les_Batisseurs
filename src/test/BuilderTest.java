package test;

import org.junit.*;
import static org.junit.Assert.*;
import game.*;

public class BuilderTest {
    Builder b;

    @Before()
    public void setUp() {
        b = new Builder("Jean",4,3,2,1,18, "../data/images/cartes/ouvriers/1.png");
    }

    @Test()
    public void testBuilder() {
        assertNotNull(b);
    }

    @Test()
    public void testGetter() {
        assertEquals(4, b.getStone());
        assertEquals(3, b.getWood());
        assertEquals(2, b.getKnowledge());
        assertEquals(1, b.getTile());
        assertEquals(18, b.getCost());
    }

    @Test()
    public void testBuildingSite() {
        b.setOnBuildingSite(false);
        assertEquals(false, b.getOnBuildingSite());
    }

    @After()
    public void clean() {
        b = null;
    }

}
