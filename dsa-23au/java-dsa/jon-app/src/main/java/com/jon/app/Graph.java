package com.jon.app;

import java.util.HashMap;
import java.util.Map;

public class Graph {

    public class Edge{
        private int weight;
        private boolean isIncluded = false; 
    }

    public class Vertex{
        private String label = null;
        private Map<Vertex, Edge> edges = new HashMap<>();
        private boolean isVisited = false;
    }

    public void addNode(String string) {
    }

}
