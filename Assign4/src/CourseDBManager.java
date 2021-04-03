import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Eric Deng
 */
public class CourseDBManager implements CourseDBManagerInterface{


    CourseDBStructure cds;


    public CourseDBManager(){
        cds=new CourseDBStructure(12);

    }

    /**
     * Adds coursedbelement with the given parameters using the add method from the cds
     * @param id
     * @param crn
     * @param credits
     * @param roomNum
     * @param instructor
     */
    @Override
    public void add(String id, int crn, int credits, String roomNum, String instructor){
        CourseDBElement cde= new CourseDBElement(id, crn, credits, roomNum, instructor);

        cds.add(cde);

         }

    /**
     * Gets a cde using the get method from the cds
     * @param crn that matches with cde crn
     * @return the coursedbelement that has the matching crn
     */
    @Override
    public CourseDBElement get(int crn){
        CourseDBElement cde=null;
        try{
            cde=cds.get(crn);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return cde;

    }

    /**
     * Reads a file of courses and adds them to the database
     * @param input file with course info
     * @throws FileNotFoundException
     */
    @Override
    public void readFile(File input) throws FileNotFoundException {
        Scanner scan= new Scanner(input);
        CourseDBElement cde;
        while(scan.hasNextLine()){
            String[] split=scan.nextLine().split(" ");

                add(split[0],Integer.parseInt(split[1]),Integer.parseInt(split[2]),split[3],split[4]);




        }


    }

    /**
     * Gets the database of course info when showAll is clicked
     * @return arraylist of course info
     */
    @Override
    public ArrayList<String> showAll() {
        ArrayList<String> arrayList= new ArrayList<>();
        for (int i = 0; i < cds.hashTable.length; i++) {
            if (cds.hashTable[i] != null) {
                for (CourseDBElement c : cds.hashTable[i]) {
                    arrayList.add(c.toString());
                }
            }


    }
        return arrayList;
    }
}
