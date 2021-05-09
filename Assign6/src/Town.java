import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents an town as a node of a graph.
 * @author Eric Deng
 */
public class Town implements Comparable<Town>{

    public String name;
    ArrayList<Town> adjacentTowns;

    /**
     * Constructor. Requires town's name.
     * @param name
     */
    public Town(String name) {
        this.name=name;
        adjacentTowns=new ArrayList<>();
    }

    /**
     * Copy constructor.
     * @param templateTown
     */
    public Town(Town templateTown){
        templateTown=this;
    }

    /**
     * Sets the town name
     * @param name of town
     */
    public void setName(String name){
        this.name=name;
    }

    /**
     * Returns the town's name
     * @return the town's name
     */
    public String getName() {
        return name;
    }

    /**
     * equals method
     * @param o
     * @return true if the town names are equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Town town = (Town) o;
        return Objects.equals(name, town.name);
    }

    /**
     * hashCode method
     * @return the hashcode for the name of the town
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Compare to method
     * @param o
     * @return 0 if names are equal, a positive or negative number if the names are not equal
     */
    @Override
    public int compareTo(Town o) {

        if(o.getName().equals(getName())){
            return 0;
        }
        else
            return 1;
    }

    @Override
    public String toString() {
        return name;
    }
}
