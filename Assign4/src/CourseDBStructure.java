import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * @author Eric Deng
 */
public class CourseDBStructure implements CourseDBStructureInterface{


    LinkedList<CourseDBElement>[] hashTable;
    int hashSize=0;

    public CourseDBStructure(int hashSize){
        hashTable=new LinkedList[hashSize];
        this.hashSize=hashSize;
    }
    public CourseDBStructure(String testing, int hashSize){
        hashTable=new LinkedList[hashSize];
        this.hashSize=hashSize;
    }

    /**
     * Creates a linked list in the hashtable and adds the CDE to it
     * @param element the CDE to be added
     */
    @Override
    public void add(CourseDBElement element) {

        int tableIndex=element.hashCode()%getTableSize();

        if(hashTable[tableIndex]==null)
            hashTable[tableIndex]=new LinkedList<>();

        hashTable[tableIndex].add(element);

    }

    /**
     * Goes through the hashtable and finds the CDE with the matching crn
     * @param crn
     * @return CDE with matcing crn
     * @throws IOException
     */
    @Override
    public CourseDBElement get(int crn) throws IOException {


        for(int i=0; i<hashTable.length;i++){
            if(hashTable[i]!=null){
                for(int j=0;j<hashTable[i].size();j++){
                    if(hashTable[i].get(j)!=null&&hashTable[i].get(j).getCRN()==crn){
                        return hashTable[i].get(j);
                    }

                }

            }
        }


        throw new IOException();
    }

    /**
     * Gets hashtable size
     * @return size of the hashtable
     */
    @Override
    public int getTableSize() {
        return hashTable.length;
    }
}
