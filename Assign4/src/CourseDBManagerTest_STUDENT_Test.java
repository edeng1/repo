import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CourseDBManagerTest_STUDENT_Test {

    private CourseDBManagerInterface dataMgr = new CourseDBManager();

    /**
     * Create an instance of CourseDBManager
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        dataMgr = new CourseDBManager();
    }

    /**
     * Set dataMgr reference to null
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        dataMgr = null;
    }

    /**
     * Test for the add method
     */
    @Test
    public void testAddToDB() {
        try {
            dataMgr.add("CMSC203",30504,4,"SC450","Eric Derek");
            dataMgr.add("CMSC203",30504,4,"SC450","Johnny John");
        }
        catch(Exception e) {
            fail("This should not have caused an Exception");
        }
    }

    /**
     * Test for the showAll method
     */
    @Test
    public void testShowAll() {
        dataMgr.add("CMSC203",35233,1,"SC451","Johnny John");
        dataMgr.add("CMSC203",16234,5,"SC457","Jill B. Who-Dunit");
        dataMgr.add("CMSC204",61234,2,"SC458","BillyBob Jones");
        ArrayList<String> list = dataMgr.showAll();

        assertEquals(list.get(0),"\nCourse:CMSC203 CRN:16234 Credits:5 Instructor:Jill B. Who-Dunit Room:SC457");
        assertEquals(list.get(1),"\nCourse:CMSC203 CRN:35233 Credits:1 Instructor:Johnny John Room:SC451");
        assertEquals(list.get(2),"\nCourse:CMSC204 CRN:61234 Credits:2 Instructor:BillyBob Jones Room:SC458");

    }
    /**
     * Test for the read method
     */
    @Test
    public void testRead() {
        try {
            File inputFile = new File("Test1.txt");
            PrintWriter inFile = new PrintWriter(inputFile);
            inFile.println("CMSC203 30504 4 SC450 Joey Bag-O-Donuts");
            inFile.print("CMSC203 30503 4 SC450 Jill B. Who-Dunit");

            inFile.close();
            dataMgr.readFile(inputFile);
            //System.out.println(dataMgr.showAll());
        } catch (Exception e) {
            fail("Should not have thrown an exception");
        }
    }
}

