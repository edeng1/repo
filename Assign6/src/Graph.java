import sun.reflect.generics.tree.Tree;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.*;

/**
 * Undirected Graph that holds the towns, as well as the roads between towns in an adjacency matrix. Finds the
 * shortest path using dijkstras algorithm.
 * @author Eric Deng
 */
public class Graph implements GraphInterface<Town, Road> {


    ArrayList<Town> towns= new ArrayList();
    ArrayList<ArrayList<Road>> graph= new ArrayList<>();
    //for dijkstras
    HashMap<Town,Integer> distance= new HashMap<>();
    HashMap<Town,Town> previousTown= new HashMap<>();


    /**
     * Constructor
     */
    public Graph(){


    }

    /**
     * Returns an edge connecting source vertex to target vertex
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex) {
        Road road=null;
        if(containsVertex(sourceVertex) && containsVertex((destinationVertex))) {
            int u = towns.indexOf(sourceVertex);
            int v = towns.indexOf(destinationVertex);
            road=graph.get(u).get(v);
            if (road.source.equals(sourceVertex) && road.destination.equals(destinationVertex)) {
                return road;
            }
            else if(road.source.equals(destinationVertex)&&road.destination.equals(sourceVertex)){
                return road;
            }
        }
        return road;
    }

    /**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) throws IllegalArgumentException,NullPointerException{
        Road road1=null;
        Road road2=null;
        if(containsVertex(sourceVertex) && containsVertex((destinationVertex))){
            //gets index of each town in the towns arraylist
            int u=towns.indexOf(sourceVertex);
            int v =towns.indexOf(destinationVertex);
            // creates roads from source to dest and dest to source
            road1=new Road(sourceVertex,destinationVertex,weight,description);
            road2=new Road(destinationVertex,sourceVertex,weight,description);
            //adds destination to adj towns list for source and vice versa
            sourceVertex.adjacentTowns.add(destinationVertex);
            destinationVertex.adjacentTowns.add(sourceVertex);
            //check if the graph contains a list within the list at the index of the source or destination
            //vertex in the towns list,if not add a list of nulls at the specified index
            if(!graph.contains(u)){
                graph.add(new ArrayList<>());
                for(int i=0;i<20;i++){
                    graph.get(u).add(null);
                }
            }
            if(!graph.contains(v)){
                graph.add(new ArrayList<>());
                for(int i=0;i<20;i++){
                    graph.get(v).add(null);
                }
            }
            //sets road from source to dest and dest to source.
            graph.get(u).set(v,road1);
            graph.get(v).set(u,road2);
        }
        else if(!containsVertex(sourceVertex)||!containsVertex(destinationVertex)){
            throw new IllegalArgumentException();
        }
        else if(sourceVertex==null||destinationVertex==null){
            throw new NullPointerException();
        }


        return road1;
    }

    /**
     * Adds the specified vertex to this graph if not already present
     * @param town vertex to be added to this graph.
     * @return true if this graph did not already contain the specified
     * vertex
     * @throws NullPointerException if the specified vertex is null.
     */
    @Override
    public boolean addVertex(Town town) throws NullPointerException{
        if(!containsVertex(town))
            towns.add(town);
        else if(town==null){
            throw new NullPointerException();
        }
        else
            return false;


        return true;
    }

    /**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex.
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
        int u=towns.indexOf(sourceVertex);
        int v =towns.indexOf(destinationVertex);
        if(graph.get(u).get(v)!=null){
            if(graph.get(u).get(v).equals(getEdge(sourceVertex,destinationVertex)))
                return true;
            else
                return false;
        }
        return false;
    }

    /**
     * Returns true if this graph contains the specified vertex.
     * @param town vertex whose presence in this graph is to be tested.
     * @return true if this graph contains the specified vertex.
     */
    @Override
    public boolean containsVertex(Town town) {
        if(towns.contains(town))
        {
            if(towns.get(towns.indexOf(town)).equals(town))
                return true;
        }
        return false;
    }

    /**
     * Returns a set of the edges contained in this graph.
     * @return a set of the edges contained in this graph.
     */
    @Override
    public Set<Road> edgeSet() {

        Set<Road> roadSet = new HashSet<>();
        for(int i=0;i<graph.size();i++){
            for (Road r:graph.get(i)) {
                    if(r!=null){

                            //System.out.println(r);
                            roadSet.add(r);

                    }



                }
        }
        //Sets cannot contain duplicates, but even if the roads have the same name, they may not be the same meaning duplicates can happen.
        //This is why I added a hashCode method in Road class which says, if the names are the same, the
        //hashcode is too. Also the reason I use HashSet here.
        //System.out.println(graph.get(0).get(1).hashCode()+" "+graph.get(1).get(0).hashCode());
        //System.out.println();
        return roadSet;
    }

    /**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices).
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
    @Override
    public Set<Road> edgesOf(Town vertex) throws IllegalArgumentException,NullPointerException{
        if(!containsVertex(vertex)){
            throw new IllegalArgumentException();
        }
        else if(vertex==null){
            throw new NullPointerException();
        }
        Set<Road> roadSet = new HashSet<>();
        int u=towns.indexOf(vertex);
            for (Road r:graph.get(u)) {
                if(r!=null)
                    roadSet.add(r);


            }

        return roadSet;
    }

    /**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph.
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        int u=towns.indexOf(sourceVertex);
        int v =towns.indexOf(destinationVertex);
        Road road;
        road=getEdge(sourceVertex,destinationVertex);

        if (road.source.equals(sourceVertex) && road.destination.equals(destinationVertex)) {
            graph.get(u).set(v,null);
        }
        //printGraph();
        return road;
    }

    /**
     * Removes the specified vertex from this graph including all its touching
     *edges if present.
     * @param town vertex to be removed from this graph, if present.
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
    @Override
    public boolean removeVertex(Town town) {
        int u=towns.indexOf(town);
      if(!containsVertex(town)){
          return false;
      }
      else{

         for(int i=0;i<graph.get(u).size();i++){
             graph.get(u).set(i,null);
         }
          towns.set(u,null);
          //System.out.println(towns);

      }



      return true;
    }

    /**
     * Returns a set of the vertices contained in this graph.
     * @return a set view of the vertices contained in this graph.
     */
    @Override
    public Set<Town> vertexSet() {
        Set<Town> townSet=new HashSet<>();
        for(int i=0;i<towns.size();i++){
            townSet.add(towns.get(i));
        }
        return townSet;
    }

    /**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @returnAn arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     */
    @Override
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
        dijkstraShortestPath(sourceVertex);


        Stack stack = new Stack();
        if(previousTown.get(destinationVertex)!=null || destinationVertex==sourceVertex){
            while(destinationVertex!=null){
                stack.add(destinationVertex);
                destinationVertex=previousTown.get(destinationVertex);
            }
        }
        //System.out.println(stack);
        ArrayList<String> arr=new ArrayList<>();
        int arrSize=0;
        String str="";
        Town t1=null;
        Town t2=null;
        Road r=null;
        while(!stack.isEmpty()){
            arrSize++;
            str="";

                t1=(Town)stack.pop();
                str+=t1;
                if(!stack.isEmpty()) {
                    t2 = (Town) stack.peek();

                    r = getEdge(t1, t2);
                    //System.out.print(r+" ");
                    str += " via " + r + " to ";
                    str += t2 + " " + r.getWeight() + " mi";
                    //System.out.println(str);
                }

            arr.add(str);
        }




        return arr;
    }

    /**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * converted from wikipedia psuedocode tbh
     * @param sourceVertex the vertex to find shortest path from
     */
    @Override
    public void dijkstraShortestPath(Town sourceVertex) {
        Set<Town> vertexSet=new HashSet<>();
        distance= new HashMap<>();
        previousTown= new HashMap<>();
        for(Town t: towns){
            if(t==sourceVertex) {
                distance.put(t,0 );
            }
            else {
                distance.put(t, Integer.MAX_VALUE);
            }
            previousTown.put(t,null);
            vertexSet.add(t);

        }


        while(!vertexSet.isEmpty()){
            int min=Integer.MAX_VALUE;

            Town u=null;
    //gets minimum distance
            for(Town t:vertexSet)
            {

               if(distance.get(t)<=min){
                   min=distance.get(t);
                   u=t;

                }

            }
    //


            vertexSet.remove(u);
            int alt=-1;
            for(Town t: u.adjacentTowns){
                if(vertexSet.contains(t)) {


                    alt = distance.get(u) + getEdge(u, t).getWeight();
                    if (alt < distance.get(t)) {
                        distance.put(t, alt);
                        previousTown.put(t, u);
                    }
                }
            }

        }

    }

    //printed graph so I could see it while testing
//    @Override
//    public void printGraph(){
//        ArrayList<ArrayList<Road>> roads;
//        roads=graph;
//        for(int i=0; i<roads.size();i++)
//        {
//            System.out.println();
//            for(int j=0;j<roads.get(i).size();j++){
//                System.out.print(roads.get(i).get(j)+" ");
//            }
//        }
//        System.out.println();
//
//    }

}
