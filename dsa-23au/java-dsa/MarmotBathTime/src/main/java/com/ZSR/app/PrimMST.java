package com.ZSR.app;
import java.util.*;
import java.util.Map;
import java.util.Set;
    
public class PrimMST<N extends Comparable<N>, E extends Comparable<E>> {
    
    
    public static <N extends Comparable<N>,E extends Comparable<E>> Set<Graph.Edge<E>> findMinimumSpanningTree(Graph<N,E> graph) {
        Set<N> nodes = graph.getNodes();
        PriorityQueue<E> bagEdge = new PriorityQueue<>();

        /*All edges where isIncluded = false attached to visited nodes go into a PriorityQueue
        Need to figure comparator for bagEdge for edge weights 
        poll bagEdge and check if attached to unvisted node 
        if yes add to mst, if no check next edge, change to isIncluded = true either way
        Attached node is now visited, repeat
          
         */
               
        // Put all nodes in an unvisited bag to start
        
        //Step 1. Put the entire graph (all nodes and edges) in a bag.
        
        //Step 2. Pull any one node out of the bag; the edges that are incident on this node
        //are now crossing the boundary of the bag.
        
        //Step 3. Among all edges that cross the boundary of the bag, pick the one with the
        //minimum weight. Add this edge to the MST.
        
        //Step 4. Follow this edge to the node inside the bag, and pull that node out of the
        //bag. In doing so, if some edge has both its end nodes outside the bag, then
        //this edge must no longer cross the boundary of the bag.
        
        //Step 5. Repeat steps 3 and 4 until the bag is empty
        return null;
        }
    }
        

