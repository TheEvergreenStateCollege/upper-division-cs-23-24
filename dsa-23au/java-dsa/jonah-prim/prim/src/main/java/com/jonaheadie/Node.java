package com.jonaheadie;

import java.util.List;
import java.util.ArrayList;

public class Node {
    private List<Edge> edges = new ArrayList<Edge>();

    public Node() {
        
    }

    public List<Edge> getEdges() {
        return new ArrayList<Edge>(edges);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public boolean hasEdge(Edge edge) {
        if (edges.contains(edge)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean removeEdge(Edge edge) {
        if (hasEdge(edge)) {
            edges.remove(edge);
            return true;
        } else {
            return false;
        }
    }
}
