package com.jonaheadie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Graph {
    private List<Node> nodes = new ArrayList<Node>();
    private List<Edge> edges = new ArrayList<Edge>();
    
    public Graph() {

    }

    public void printAllEdges() {
        for (Edge e : edges) {
            System.out.print(e.getOrigin().getName());
            System.out.print(", ");
            System.out.print(e.getDest().getName());
            System.out.print(", ");
            System.out.print(e.getWeight());
            System.out.print("\n");
        }
    }

    public void addNode(String name) {
        if (!hasNode(name)) {
            Node node = new Node(name);
        nodes.add(node);
        }
    }

    public void addNode(Node node) {
        if (!nodes.contains(node) && !hasNode(node.getName())) {
            nodes.add(node);
        }
    }

    public void removeNodeAndEdges(String name) {
        Node node = getNode(name);

        if (node == null) {
            return;
        }

        nodes.remove(node);
        Iterator<Edge> iterator = edges.iterator();
        while (iterator.hasNext()) {
            Edge e = iterator.next();
            if (e.isEdgeTo(name)) {
                iterator.remove();
            }
        }
    }

    public void removeNodeAndEdges(Node node) {
        if (nodes.contains(node)) {
            nodes.remove(node);
            Iterator<Edge> iterator = edges.iterator();
            while (iterator.hasNext()) {
                Edge e = iterator.next();
                if (e.isEdgeTo(node)) {
                    iterator.remove();
                }
            }
        }
        System.err.println("ERROR (removeNodeAndEdges)");
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

    public List<Edge> getNodeEdges(String name) {
        List<Edge> result = new ArrayList<Edge>();

        for (Edge e : edges) {
            if (e.isEdgeTo(name)) {
                result.add(e);
            }
        }

        return result;
    }

    public List<Edge> getNodeEdges(Node node) {
        if (nodes.contains(node)) {
            List<Edge> result = new ArrayList<Edge>();

            for (Edge e : edges) {
                if (e.isEdgeTo(node)) {
                    result.add(e);
                }
            }

            return result;
        }
    
        System.err.println("ERROR (getNodeEdges): does not contain node");
        return null;
    }

    public boolean hasNode(String name) {
        Node node = getNode(name);

        if (node == null) {
            return false;
        }

        return true;
    }

    public boolean hasNode(Node node) {
        if (nodes.contains(node)) {
            return true;
        }

        return false;
    }

    public void addEdge(String node1, String node2, int weight) {
        //TODO: check to see if edge already exists
        Node origin = getNode(node1);
        Node dest = getNode(node2);

        if(hasEdge(node1, node2)) {
            System.err.println("Edge already exists between " + node1 + " and " + node2);
            return;
        }

        Edge edge = new Edge(origin, dest, weight);
        edges.add(edge);
    }

    public void addEdge(Edge edge) {
        if (!edges.contains(edge)) {
            edges.add(edge);
            return;
        }

        System.err.println("ERROR (addEdge): Already contains edge");
    }

    public void addEdge(Node node1, Node node2, int weight) {
        //TODO: check to see if edge already exists
        if (nodes.contains(node1) && nodes.contains(node2)) {
            if(hasEdge(node1, node2)) {
                System.err.println("Edge already exists between " + node1 + " and " + node2);
                return;
            }
    
            Edge edge = new Edge(node1, node2, weight);
            edges.add(edge);
            return;
        }
        
        System.err.println("ERROR (addEdge): Graph does not contain given nodes");
    }

    public void removeEdge(String node1, String node2) {
        Node origin = getNode(node1);
        Node dest = getNode(node2);

        if (origin == null || dest == null) {
            return;
        }

        Iterator<Edge> iterator = edges.iterator();
        while (iterator.hasNext()) {
            Edge e = iterator.next();
            if (e.isEdgeTo(node1) && e.isEdgeTo(node2)) {
                iterator.remove();
            }
        }
    }

    public void removeEdge(Node node1, Node node2) {
        if (nodes.contains(node1) && nodes.contains(node2)) {
            Iterator<Edge> iterator = edges.iterator();
            while (iterator.hasNext()) {
                Edge e = iterator.next();
                if (e.isEdgeTo(node1) && e.isEdgeTo(node2)) {
                    iterator.remove();
                }
            }
        }
    }

    public void removeEdge(Edge edge) {
        if (edges.contains(edge)) {
            edges.remove(edge);
        }

        System.err.println("ERROR (removeEdge)");
    }

    public boolean hasEdge(String node1, String node2) {
        for (Edge e : edges) {
            if (e.isEdgeTo(node1) && e.isEdgeTo(node2)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasEdge(Node node1, Node node2) {
        for (Edge e : edges) {
            if (e.isEdgeTo(node1) && e.isEdgeTo(node2)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasEdge(Edge edge) {
        if (edges.contains(edge)) {
            return true;
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

    public int getEdgeWeight(Node node1, Node node2) {
        if (nodes.contains(node1) && nodes.contains(node2)) {
            for (Edge e : edges) {
                if (e.isEdgeTo(node1) && e.isEdgeTo(node2)) {
                    return e.getWeight();
                }
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

    public void setEdgeWeight(Node node1, Node node2, int weight) {
        if (nodes.contains(node1) && nodes.contains(node2)) {
            Edge edge = new Edge(node1, node2, weight);
            edges.add(edge);
            return;
        }

        System.err.println("ERROR: node is not in graph, cannot add edge");
    }

    public Graph cloneGraph() {
        Graph clone = new Graph();

        for (Node n : nodes) {
            clone.addNode(n.getName());
        }

        for (Edge e : edges) {
            clone.addEdge(e.getOrigin().getName(), e.getDest().getName(), e.getWeight());
        }

        return clone;
    }

    public List<String> getAllNodesNames() {
        List<String> result = new ArrayList<String>();

        for (Node n : nodes) {
            result.add(n.getName());
        }

        return result;
    }

    public List<Node> getAllNodes() {
        List<Node> result = new ArrayList<Node>();

        for (Node n : nodes) {
            result.add(n);
        }

        return result;
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
