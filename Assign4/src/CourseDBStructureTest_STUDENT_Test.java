import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class CourseDBStructureTest_STUDENT_Test {
    CourseDBStructure cds, testStructure;

    @Before
    public void setUp() throws Exception {
        cds = new CourseDBStructure(30);
        testStructure = new CourseDBStructure("Testing", 30);
    }

    @After
    public void tearDown() throws Exception {
        cds = testStructure = null;
    }

    /**
     * Test the tableSize for CourseDBStructures constructed
     * with both constructors
     */
    @Test
    public void testGetTableSize()
    {
        assertEquals(30, cds.getTableSize());
        assertEquals(30, testStructure.getTableSize());
    }

    /**
     * Test the hashTable for CourseDBStructures constructed
     * with both constructors
     */
    @Test
    public void testHashTable()
    {
        //CourseDBStructure cds = new CourseDBStructure(500);
        assertEquals(30, cds.hashTable.length);
        CourseDBElement cde = new CourseDBElement("CMSC400", 30000, 4, "SC100", "Me");

        cds.add(cde);

        LinkedList<CourseDBElement> list = cds.hashTable[cde.hashCode()%cds.getTableSize()];
        System.out.print(cde.hashCode()%cds.getTableSize());
        assertEquals(30000, list.get(0).getCRN());


        cds = new CourseDBStructure("Testing",30);
        assertEquals(30, cds.hashTable.length);
        cds.add(cde);

        list = cds.hashTable[cde.hashCode()%30];
        assertEquals(30000, list.get(0).getCRN());

    }
}
