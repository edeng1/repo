import java.util.Objects;

/**
 *
 * @author Eric Deng
 */
public class CourseDBElement<T> implements Comparable<T>{

    String courseID;
    int CRN;
    int credits;
    String roomNum;
    String insName;

    public CourseDBElement(){

    }


    public CourseDBElement(String courseID,int CRN,int credits,String roomNum,String insName){
        this.courseID=courseID;
        this.CRN=CRN;
        this.credits=credits;
        this.roomNum=roomNum;
        this.insName=insName;

    }


    @Override
    public int hashCode() {
        return Objects.hash(getCRN()+"");
    }

    @Override
    public int compareTo(T o) {
        CourseDBElement other= (CourseDBElement) o;
        return hashCode()-other.hashCode();
    }

    public void setCRN(int CRN) {
        this.CRN = CRN;
    }

    public int getCRN() {
        return CRN;
    }

    @Override
    public String toString() {
        return "\nCourse:"+ courseID +
                " CRN:" + CRN +
                " Credits:" + credits +
                " Instructor:" + insName +
                " Room:" + roomNum;
    }
}
