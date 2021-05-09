import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Eric Deng
 */
public class TownGraphManager implements TownGraphManagerInterface {
    Graph graph;

    /**
     * Constructor
     */
    public TownGraphManager() {
        graph=new Graph();
    }

    /**
     * Adds a road with 2 towns and a road name
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @param weight distance between towns
     * @param roadName name of road
     * @return
     */
    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName) {
        Town firstTown=null;
        Town secondTown=null;
        for(Town t:graph.towns){
            if(t.getName().equals(town1)){
                firstTown=t;
            }
            else if(t.getName().equals(town2)){
                secondTown=t;
            }

        }
        graph.addEdge(firstTown,secondTown,weight,roadName);
        if(!graph.containsEdge(firstTown,secondTown)){
            return false;
        }
        return true;
    }

    /**
     * Returns the name of the road that both towns are connected through
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return
     */
    @Override
    public String getRoad(String town1, String town2) {
        Town firstTown = null;
        Town secondTown = null;
        for (Town t : graph.towns) {
            if (t.getName().equals(town1)) {
                firstTown = t;
            } else if (t.getName().equals(town2)) {
                secondTown = t;
            }

        }
        return graph.getEdge(firstTown, secondTown).toString();

    }

    /**
     * Adds a town to the graph
     * @param v the town's name  (lastname, firstname)
     * @return true if the town was successfully added, false if not
     */
    @Override
    public boolean addTown(String v) {

        return graph.addVertex(new Town(v));
    }

    /**
     * Gets a town with a given name
     * @param name the town's name
     * @return the Town specified by the name, or null if town does not exist
     */
    @Override
    public Town getTown(String name) {

        for(Town t:graph.towns){
            if(t.getName().equals(name)){
                return t;
            }
        }
        return null;
    }

    /**
     * Determines if a town is already in the graph
     * @param v the town's name
     * @return true if the town is in the graph, false if not
     */
    @Override
    public boolean containsTown(String v) {

        ArrayList<String> arr=new ArrayList<>();

        for(Town t:graph.towns){
            if(t!=null)
                arr.add(t.getName());

        }
        if(arr.contains(v)){
            return true;
        }
        return false;
    }

    /**
     * Determines if a road is in the graph
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return true if the road is in the graph, false if not
     */
    @Override
    public boolean containsRoadConnection(String town1, String town2) {
        Town firstTown = null;
        Town secondTown = null;
        for (Town t : graph.towns) {
            if (t.getName().equals(town1)) {
                firstTown = t;
            } else if (t.getName().equals(town2)) {
                secondTown = t;
            }

        }
        return graph.containsEdge(firstTown,secondTown);
    }

    /**
     * Creates an arraylist of all road titles in sorted order by road name
     * @return an arraylist of all road titles in sorted order by road name
     */
    @Override
    public ArrayList<String> allRoads() {
        ArrayList<String> arr=new ArrayList<>();
        for(Road r:graph.edgeSet()){
            arr.add(r.toString());
        }
        Collections.sort(arr);

        return arr;
    }

    /**
     * Deletes a road from the graph
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @param road the road name
     * @return true if the road was successfully deleted, false if not
     */
    @Override
    public boolean deleteRoadConnection(String town1, String town2, String road) {

        if(containsRoadConnection(town1,town2) && getRoad(town1,town2).equals(road)){
            Town firstTown = null;
            Town secondTown = null;
            for (Town t : graph.towns) {
                if (t.getName().equals(town1)) {
                    firstTown = t;
                } else if (t.getName().equals(town2)) {
                    secondTown = t;
                }

            }
            Road r=graph.getEdge(firstTown,secondTown);
            graph.removeEdge(r.getSource(),r.getDestination(),r.getWeight(),r.getName());
            if(graph.containsEdge(firstTown,secondTown)){
                return true;
            }
        }

        return false;
    }

    /**
     * Deletes a town from the graph
     * @param v name of town (lastname, firstname)
     * @return
     */
    @Override
    public boolean deleteTown(String v) {
        for(Town t:graph.towns){
            if(t.getName().equals(v)){
                return graph.removeVertex(t);
            }
        }
        return false;
    }

    /**
     * Creates an arraylist of all towns in alphabetical order (last name, first name)
     * @return an arraylist of all towns in alphabetical order (last name, first name)
     */
    @Override
    public ArrayList<String> allTowns() {
        ArrayList<String> arr=new ArrayList<>();
        for(Town t:graph.towns){
            arr.add(t.toString());
        }
        Collections.sort(arr);
        return arr;
    }

    /**
     * Returns the shortest path from town 1 to town 2
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return an Arraylist of roads connecting the two towns together, null if the
     * towns have no path to connect them.
     */
    @Override
    public ArrayList<String> getPath(String town1, String town2) {
        Town firstTown = null;
        Town secondTown = null;
        for (Town t : graph.towns) {
            if (t.getName().equals(town1)) {
                firstTown = t;
            } else if (t.getName().equals(town2)) {
                secondTown = t;
            }

        }

        return  graph.shortestPath(firstTown,secondTown);
    }

    /**
     * Takes file with each line in format Road,Distance,Town1,Town2
     * @param selectedFile
     * @throws FileNotFoundException if file not found
     */
    public void populateTownGraph(File selectedFile) throws FileNotFoundException {
        Scanner scan=new Scanner(selectedFile);

        while(scan.hasNextLine()){
            String currentLine=scan.nextLine();
            //splits , and ;
           String[] lineSplit=currentLine.split(",|\\;");
           addTown(lineSplit[2]);
           addTown(lineSplit[3]);
           addRoad(lineSplit[2],lineSplit[3],Integer.parseInt(lineSplit[1]),lineSplit[0]);



        }
    }
}
