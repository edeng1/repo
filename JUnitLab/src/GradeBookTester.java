import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GradeBookTester {
    private GradeBook g1;
    private GradeBook g2;
    @Before
    public void setUp() throws Exception {
        g1= new GradeBook(5);
        g2= new GradeBook(5);
        g1.addScore(44);
        g1.addScore(23);
        g2.addScore(50);
        g2.addScore(85);
    }

    @After
    public void tearDown() throws Exception {
        g1=null;
        g2=null;
    }

    @Test
    public void testAddScore() {
        assertTrue(g1.toString().equals("44.0 23.0 "));
        assertTrue(g2.toString().equals("50.0 85.0 "));
    }

    @Test
    public void testSum() {
        assertEquals(67, g1.sum(), .0001);
        assertEquals(135, g2.sum(), .0001);
    }

    @Test
    public void testMinimum() {
        assertEquals(23, g1.minimum(), .001);
        assertEquals(50, g2.minimum(), .001);
    }

    @Test
    public void testFinalScore() {
    }
}