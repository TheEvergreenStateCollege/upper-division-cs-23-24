package com.devincompany.app;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

public class PrimsMST<N extends Comparable<N>, E extends Comparable<E>> {


    private static <N1> N1 getItemFromSet(int index, Set<N1> nodes) {
            Iterator<N1> iterator = nodes.iterator();
            for (int i = 0; i < index; i++) {
                iterator.next();
            }
        return(iterator.next());
    }

    public static <N extends Comparable<N>, E extends Comparable<E>> Set<Graph.Edge<Integer>> findMinimumSpanningTree(Graph<N, E> graph) {
        Set<N> nodes = graph.getNodes();
        // Put all nodes in an unvisited bag to start
//         Step 1. Put the entire graph (all nodes and edges) in a bag.

        // Step 2. Pull any one node out of the bag; the edges that are incident on this node
        // are now crossing the boundary of the bag.
        ArrayList<N> removedNodes = new ArrayList<N>();

        while (!nodes.isEmpty()) {
            removedNodes.add(getItemFromSet(0, nodes));
            int size = removedNodes.size();
            nodes.remove(removedNodes.get(size));
            Graph.Edge<E> minimumEdge = graph.getMinEdgeToUnvisitedNode(removedNodes.get(0));
            
            for (N node : removedNodes){
                Graph.Edge<E> minEdge = graph.getMinEdgeToUnvisitedNode(node);
                E minEdgeWeight = minEdge.getWeight();
                if (minimumEdge.getWeight().compareTo(minEdgeWeight) == -1) {
                    minimumEdge = minEdge;
                }
            }
            minimumEdge.
        }
// Step 3. Among all edges that cross the boundary of the bag, pick the one with the
// minimum weight. Add this edge to the MST.
// Step 4. Follow this edge to the node inside the bag, and pull that node out of the
// bag. In doing so, if some edge has both its end nodes outside the bag, then
// this edge must no longer cross the boundary of the bag.
// Step 5. Repeat steps 3 and 4 until the bag is empty.
         return null;
    }
}
