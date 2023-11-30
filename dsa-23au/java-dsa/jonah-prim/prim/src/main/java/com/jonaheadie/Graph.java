package com.jonaheadie;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Node> nodes = new ArrayList<Node>();
    private List<Edge> edges = new ArrayList<Edge>();
    
    public Graph() {

    }

    public void addNode(String name) {
        Node node = new Node(name);
        nodes.add(node);
    }

    public void removeNodeAndEdges(String name) {
        Node node = getNode(name);

        if (node == null) {
            return;
        }

        nodes.remove(node);
        for (Edge e : edges) {
            if (e.isEdgeTo(name)) {
                edges.remove(e);
            }
        }
    }

    public List<String> getAdjacentNodes(String name) {
        Node node = getNode(name);
        if (node == null) {
            return null;
        }

        List<Edge> nodeEdges = getNodeEdges(name);
        List<String> adj = new ArrayList<String>();
        for (Edge e : nodeEdges) {
            adj.add(e.getDest().getName());
        }

        return adj;
    }

    private List<Edge> getNodeEdges(String name) {
        List<Edge> result = new ArrayList<Edge>();

        for (Edge e : edges) {
            if (e.isEdgeTo(name)) {
                result.add(e);
            }
        }

        return result;
    }

    public boolean hasNode(String name) {
        Node node = getNode(name);

        if (node == null) {
            return false;
        }

        return true;
    }

    public void addEdge(String node1, String node2, int weight) {
        //TODO: check to see if edge already exists
        Node origin = getNode(node1);
        Node dest = getNode(node2);

        Edge edge = new Edge(origin, dest, weight);
        edges.add(edge);
    }

    public void removeEdge(String node1, String node2) {
        Node origin = getNode(node1);
        Node dest = getNode(node2);

        if (origin == null || dest == null) {
            return;
        }

        for (Edge e : edges) {
            if (e.isEdgeTo(node1) && e.isEdgeTo(node2)) {
                edges.remove(e);
            }
        }
    }

    public boolean hasEdge(String node1, String node2) {
        for (Edge e : edges) {
            if (e.isEdgeTo(node1) && e.isEdgeTo(node2)) {
                return true;
            }
        }

        return false;
    }

    public int getEdgeWeight(String node1, String node2) {
        for (Edge e : edges) {
            if (e.isEdgeTo(node1) && e.isEdgeTo(node2)) {
                return e.getWeight();
            }
        }

        return -1; //error
    }

    public void setEdgeWeight(String node1, String node2, int weight) {
        for (Edge e : edges) {
            if (e.isEdgeTo(node1) && e.isEdgeTo(node2)) {
                e.setWeight(weight);
            }
        }
    }

    private Node getNode(String name) {
        for (Node n : nodes) {
            if (n.getName() == name) {
                return n;
            }
        }

        return null;
    }
}
