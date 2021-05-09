import java.util.Objects;

/**
 * The class Road that can represent the edges of a Graph of Towns.
 * Since this is a undirected graph, an edge from A to B is equal to an edge from B to A.
 * @author Eric Deng
 */
public class Road implements Comparable<Road>{
    Town source;
    Town destination;
    int degrees;
    String name;

    /**
     * Constructor
     * @param source One town on the road
     * @param destination Another town on the road
     * @param degrees Weight of the edge, i.e., distance from one town to the other
     * @param name Name of the road
     */
    public Road(Town source, Town destination, int degrees, String name){
        this.source=source;
        this.destination=destination;
        this.degrees=degrees;
        this.name=name;
    }

    /**
     * Constructor with weight preset at 1
     * @param source One town on the road
     * @param destination Another town on the road
     * @param name Name of the road
     */
    public Road(Town source, Town destination, String name){
        this(source,destination,1,name);
    }

    /**
     * Returns true only if the edge contains the given town
     * @param town a vertex of the graph
     * @return true only if the edge is connected to the given vertex
     */
    public boolean contains(Town town){
        if(getSource().equals(town)||getDestination().equals(town)){
            return true;
        }
        else
            return false;
    }
    /**
     * Returns the first town on the road
     * @return A town on the road
     */
    public Town getSource() {
        return source;
    }

    /**
     * Returns the second town on the road
     * @return the second town on the road
     */
    public Town getDestination() {
        return destination;
    }

    /**
     * Returns the distance of the road
     * @return the distance of the road
     */
    public int getWeight() {
        return degrees;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Returns true if each of the ends of the road r is the same as the ends of this road.
     * Remember that a road that goes from point A to point B is the same as a road that goes from point B to point A.
     * @param o road object to compare it to
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return (Objects.equals(source, road.source) && Objects.equals(destination, road.destination)||(Objects.equals(source, road.destination) && Objects.equals(destination, road.source)));
    }

    /**
     * Roads with the same name have the same hash code
     * @return the hashcode for the name of the road
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     *
     * @param o
     * @return 0 if the road names are the same, a positive or negative number if the road names are not the same
     */
    @Override
    public int compareTo(Road o) {
        if(o.getName().equals(getName())){
            return 0;
        }
       else
           return 1;
    }


    @Override
    public String toString() {
//        return "Road{" +
//                "source=" + source +
//                ", destination=" + destination +
//                ", degrees=" + degrees +
//                ", name='" + name + '\'' +
//                '}';
        return name;
    }
}
