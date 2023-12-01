package com.jonaheadie;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Graph {
    List<Edge> edges;
    List<String> isolatedNodes; // nodes without edges
    List<String> outsideNodes; // nodes "pulled out" of the "bag" when used as a prim algo. bag

    public Graph() {
        edges = new ArrayList<Edge>();
        isolatedNodes = new ArrayList<String>();
        outsideNodes = new ArrayList<String>();
    }

    public boolean addNode(String node) {
        if (!hasNode(node)) {
            isolatedNodes.add(node);
            return true;
        }

        return false;
    }

    public boolean pullOutNode(String node) {
        if (!hasNode(node)) {
            System.err.println("pullOutNode: does not have " + node);
            return false;
        }

        if (!isNodeOutside(node)) {
            outsideNodes.add(node);
            return true;
        }

        System.err.println("pullOutNode: already outside " + node);
        return false;
    }

    //TODO: remove accompanying edges
    public boolean removeNode(String node) {
        if (!hasNode(node)) {
            return false;
        }

        if (isNodeIsolated(node)) {
            isolatedNodes.remove(node);
        }

        if (isNodeOutside(node)) {
            outsideNodes.remove(node);
        }

        return true;
    }

    public void printAllEdges() {
        for (Edge e : edges) {
            System.out.println(e.getFirst() + ", " + e.getSecond() + ", " + e.getWeight());
        }
    }

    public void printAllNodes() {
        List<String> nodes = getAllNodes();

        for (String s : nodes) {
            if (isNodeIsolated(s)) {
                System.out.println(s + " (isolated)");
            } else if (isNodeOutside(s)) {
                System.out.println(s + " (outside)");
            } else {
                System.out.println(s);
            }
        }
    }

    //TODO: check if edge between nodes exists
    //TODO: remove node from isolatedNodes if edge added
    public boolean addEdge(Edge edge) {
        edges.add(edge);

        if (doesListContain(edge.getFirst(), isolatedNodes)) {
            removeFromList(edge.getFirst(), isolatedNodes);
        }

        if (doesListContain(edge.getSecond(), isolatedNodes)) {
            removeFromList(edge.getSecond(), isolatedNodes);
        }

        return true;
    }

    //TODO: check if edge between nodes exists
    public boolean addEdge(String node1, String node2, int weight) {
        Edge edge = new Edge(node1, node2, weight);
        addEdge(edge);
        return true;
    }

    //TODO: add connected nodes to isolated if necessary
    public boolean removeEdge(Edge edge) {
        for (Edge e : edges) {
            if (e.isEqual(edge)) {
                edges.remove(e);
                return true;
            }
        }

        if (!isNodeConnected(edge.getFirst())) {
            isolatedNodes.add(edge.getFirst());
        }

        if (!isNodeConnected(edge.getSecond())) {
            isolatedNodes.add(edge.getSecond());
        }

        return false; // edge not found
    }

    public boolean removeEdge(String node1, String node2) {
        Edge edge = new Edge(node1, node2, 0);

        for (Edge e : edges) {
            if (e.sameNodes(edge)) {
                edges.remove(e);
                return true;
            }
        }

        if (!isNodeConnected(edge.getFirst())) {
            isolatedNodes.add(edge.getFirst());
        }

        if (!isNodeConnected(edge.getSecond())) {
            isolatedNodes.add(edge.getSecond());
        }

        return false; // edge not found
    }

    public List<String> getAllNodes() {
        List<String> nodes = new ArrayList<String>();

        for (Edge e : edges) {
            if (!doesListContain(e.getFirst(), nodes)) {
                nodes.add(e.getFirst());
            }

            if (!doesListContain(e.getSecond(), nodes)) {
                nodes.add(e.getSecond());
            }
        }

        for (String n : isolatedNodes) {
            if (!doesListContain(n, nodes)) {
                nodes.add(n);
            }
        }

        return nodes;
    }

    public List<String> getAllIsolatedNodes() {
        List<String> result = new ArrayList<String>(isolatedNodes);
        return result;
    }

    public List<String> getAllOutsideNodes() {
        List<String> result = new ArrayList<String>(outsideNodes);
        return result;
    }

    public List<Edge> getAllBoundaryEdges() {
        List<Edge> boundary = new ArrayList<Edge>();

        for (Edge e : edges) {
            for (String out : outsideNodes) {
                if (out.equals(e.getFirst())) {
                    boundary.add(e);
                } else if (out.equals(e.getSecond())) {
                    boundary.add(e);
                }
            }
        }

        return boundary;
    }

    public boolean hasNode(String node) {
        List<String> nodes = getAllNodes();

        return doesListContain(node, nodes);
    }

    public boolean isNodeIsolated(String node) {
        return doesListContain(node, isolatedNodes);
    }

    public boolean isNodeOutside(String node) {
        return doesListContain(node, outsideNodes);
    }

    public boolean hasEdge(Edge edge) {
        return false;
    }

    public boolean hasEdge(String node1, String node2, int weight) {
        return false;
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

    public boolean isNodeConnected(String node) {
        boolean isConnected = false;

        for (Edge e : edges) {
            if (node.equals(e.getFirst())) {
                isConnected = true;
            }
            if (node.equals(e.getSecond())) {
                isConnected = true;
            }
        }

        return isConnected;
    }

    private boolean doesListContain(String string, List<String> list) {
        boolean contains = false;
        for (String n : list) {
            if (string.equals(n)) {
                contains = true;
            }
        }

        return contains;
    }

    private void removeFromList(String string, List<String> list) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String curr = iterator.next();
            
            if (curr.equals(string)) {
                iterator.remove();
            }
        }
    }
}
