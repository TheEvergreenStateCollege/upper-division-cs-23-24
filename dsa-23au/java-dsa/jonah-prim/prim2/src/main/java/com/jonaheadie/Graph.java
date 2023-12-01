package com.jonaheadie;

import java.util.List;
import java.util.ArrayList;

public class Graph {
    List<Edge> edges;
    List<String> isolatedNodes; // nodes without edges

    public Graph() {
        edges = new ArrayList<Edge>();
    }

    public boolean addNode(String node) {
        if (!hasNode(node)) {
            isolatedNodes.add(node);
            return true;
        }

        return false;
    }

    public boolean removeNode(String node) {

    }

    public boolean addEdge(Edge edge) {

    }

    public boolean addEdge(String node1, String node2, int weight) {

    }

    public boolean removeEdge(Edge edge) {

    }

    public boolean removeEdge(String node1, String node2) {

    }

    public List<String> getAllNodes() {
        List<String> nodes;

        for (Edge e : edges) {
            if (!nodes.contains(e.getFirst())) {
                nodes.add(e.getFirst());
            }

            if (!nodes.contains(e.getSecond())) {
                nodes.add(e.getSecond());
            }
        }

        for (String n : isolatedNodes) {
            if (!nodes.contains(n)) {
                nodes.add(n);
            }
        }

        return nodes;
    }

    public boolean hasNode(String node) {
        List<String> nodes = getAllNodes();

        if (nodes.contains(node)) {
            return true;
        }

        return false;
    }

    public boolean hasEdge(Edge edge) {

    }

    public boolean hasEdge(String node1, String node2, int weight) {

    }

    public boolean hasNodes() {
        if (!edges.isEmpty() || !isolatedNodes.isEmpty()) {
            return true;
        }

        return false;
    }

    public boolean hasEdges() {
        if (!edges.isEmpty()) {
            return true;
        }

        return false;
    }
}
