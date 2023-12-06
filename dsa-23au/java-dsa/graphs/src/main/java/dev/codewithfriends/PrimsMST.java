package dev.codewithfriends;

import java.util.Map;
import java.util.Set;
import java.util.*;

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
        return null;
        Set<N> removedNodes;
        while (!nodes.isEmpty());
        {
            removedNodes.add(getItemFromSet(0, nodes));
            int size = removedNodes.size();
            nodes.remove(removedNodes.get(size));
            
        }
    }
}
