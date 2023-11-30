package com.ndeanon25.Graph;

import java.util.*;
import java.util.Map.Entry;

public class GraphSparse<N extends Comparable<N>,E extends Comparable<E>> extends Graph<N,E> {

    public Map<N, Vertex> verticesMap;

    public GraphSparse() {
        verticesMap = new HashMap<>();
    }

    public static class Edge<E1> implements Graph.Edge<E1> {
        public E1 weight;
        public boolean isIncluded;

        public Edge(E1 weight){
            this.weight = weight;
            this.isIncluded = false;
        }

        @Override
        public E1 getWeight() {
            return this.weight;
        }
    }

    

    public class Vertex{
        public N label;
        public Map<Vertex, Edge<E>> edges = new HashMap<>();
        // isVisited = false means node is "in the bag"
        //           = true, means we pulled node out of bag
        public boolean isVisited = false;

        public Vertex(N label){
            this.label = label;
            this.edges = new HashMap<>();
            this.isVisited = false;
        }
    }

    @Override
    public boolean addNode(N label) {
        if (label == null || verticesMap.containsKey(label)) {
            return false;
        }
        // Maps a label to a vertex.
        verticesMap.put(label, new Vertex(label));
        return true;
    }

    @Override
    public boolean addEdge(N srcNode, N destNode, E weight)
    {
        //Create node1 from the parameter source node
        Vertex node1 = verticesMap.get(srcNode);
        Vertex node2 = verticesMap.get(destNode);

        Edge<E> edge = new Edge<>(weight);

        node1.edges.put(node2, edge);
        node2.edges.put(node1, edge);
        return true;
    }

    @Override
    public Edge<E> getEdge(N srcNode, N destNode)
    {
        //Create node1 from the parameter source node
        Vertex node1 = verticesMap.get(srcNode);
        Vertex node2 = verticesMap.get(destNode);
        Edge<E> e1 = node1.edges.get(node2);
        Edge<E> e2 = node2.edges.get(node1);
        assert(e1.equals(e2));
        return e1;
    }

    @Override
    public Edge<E> getMinEdgeToUnvisitedNode(N sourceNodeLabel) {
        EdgeComparator<E> ec = new EdgeComparator<>();
        Edge<E> minWeightEdgeSoFar = null;
        E minWeightSoFar = null;
        Vertex source = verticesMap.get(sourceNodeLabel);

        for (Entry<Vertex,Edge<E>> entry : source.edges.entrySet()) {
            Edge<E> e = entry.getValue();
            Vertex dest = entry.getKey();
            if (dest.isVisited) {
                continue;
            }
            if (ec.compare(e.weight, minWeightSoFar) < 0) {
                minWeightSoFar = e.weight;
                minWeightEdgeSoFar = e;
            }
        }
        return minWeightEdgeSoFar;
    }

    @Override
    public void printMermaidDiagram() {
        Collection<Vertex> nodes = this.verticesMap.values();
        System.out.printf("graph TD\n");
        Set<String> graphStrings = new TreeSet<>();
        for (Vertex v : nodes) {
            Set<Vertex> nodes2 = v.edges.keySet();
            for (Vertex v2 : nodes2) {
                // Sort the strings into canonical order
                N w1 = v.label.compareTo(v2.label) < 0 ? v.label : v2.label;
                N w2 = v.label.compareTo(v2.label) < 0 ? v2.label : v.label;
                graphStrings.add(String.format("    %s---%s", w1.toString(), w2.toString()));

            }
        }
        for (String string : graphStrings) {
            System.out.println(string);
        }
    }

}