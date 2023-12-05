package com.pswishcorp.app;

import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import com.pswishcorp.*;

public class primsMST<N,E> {
    public static <N extends Comparable<N>, E extends Comparable<E>> Set<Graph.Edge<Integer>> findMinimumSpanningTree(Graph<N,E> graph) {
        //Set<N> nodes = graph.getNodes();
        Set<N> nodes = new HashSet<>();
        Set<Graph.Edge<E1> minimumSpanningTree = new HashSet<>();
        Set<N> isVisited = new HashSet<>();
        
        if (!graph.getNode.isEmpty()){
            N startNode = graph.getNode().iterator().next();
            isVisited.add(startNode);

            PriorityQueue<Graph.Edge<N,E>> priorityQueue = new PriorityQueue<>(graph.getEdge(startNode));
        
            while (!priorityQueue.isEmpty() && isVisited.size()< graph.getNode().size());
                Graph.Edge<N, E> edge = priorityQueue.poll();

                N source = edge.getSource();
                N destination = edge.getDestination();
        
                if (!(isVisited.contains(source) && isVisited.contains(destination))){
                    minimumSpanningTree.add(edge);
                    isVisited.add(source);
                    isVisited.add(destination);

                    for (Graph.Edge<N, E> adjacentEdge : graph.getEdge(destination)){
                        if (!isVisited.containsEdge(adjacentEdge.getDestination())){
                            priorityQueue.add(adjacentEdge);
                }
            }
        }
    }        
    }
}
