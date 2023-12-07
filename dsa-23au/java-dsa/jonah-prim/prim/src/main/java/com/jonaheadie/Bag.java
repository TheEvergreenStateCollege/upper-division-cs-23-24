package com.jonaheadie;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Bag {
    private Graph bag;
    private Graph mst;
    private List<Node> inside;
    private List<Node> outside = new ArrayList<Node>();
    private List<Edge> crossing = new ArrayList<Edge>();

    public Bag(Graph bag, Graph mst) {
        this.bag = bag;
        this.mst = mst;
        inside = bag.getAllNodes();
    }

    public Graph prim() {
        Node firstNode = pullOutNode();
        Edge firstEdge = getLowestWeightCrossing();
        mst.addNode(firstNode);
        mst.addEdge(firstEdge);
        bag.removeEdge(firstEdge);
        Node activeNode = getInsideNodeOfEdge(firstEdge);

        while (!inside.isEmpty()) {
            pullOutNode(activeNode);
            Edge lowestEdge = getLowestWeightCrossing();
            mst.addNode(activeNode);
            mst.addEdge(lowestEdge);
            bag.removeEdge(lowestEdge);
            activeNode = getInsideNodeOfEdge(lowestEdge);
        }

        return mst;
    }

    public Node getInsideNodeOfEdge(Edge edge) {
        if ((isInside(edge.getOrigin())) && (isInside(edge.getDest()))) {
            System.err.println("ERROR (getInsideNodeOfEdge): both ends inside");
            return null;
        }

        if (isInside(edge.getOrigin())) {
            return edge.getOrigin();
        } else if (isInside(edge.getDest())) {
            return edge.getDest();
        } else {
            System.err.println("ERROR (getInsideNodeOfEdge)");
            return null;
        }
    }

    public Graph getRawGraph() {
        return bag;
    }

    public List<Node> getAllInside() {
        List<Node> result = new ArrayList<Node>(inside);
        return result;
    }

    public List<Node> getAllOutside() {
        List<Node> result = new ArrayList<Node>(outside);
        return result;
    }

    public boolean isOutside(Node node) {
        if (outside.contains(node)) {
            return true;
        }

        return false;
    }

    public boolean isInside(Node node) {
        if (inside.contains(node)) {
            return true;
        }

        return false;
    }

    // Pulls out first node
    public Node pullOutNode() {
        System.out.println("pull out 1");
        Node node = inside.get(0);

        outside.add(node);
        inside.remove(0);

        addEdgesToCrossing(node);
        return node;
    }

    public Node pullOutNode(Node node) {
        System.out.println("pull out 2");
        if (inside.contains(node)) {
            outside.add(node);
            inside.remove(node);
        }

        addEdgesToCrossing(node);
        return node;
    }

    // Returns lowest weight crossing edge
    public Edge getLowestWeightCrossing() {
        System.out.println("lowest");
        pruneCrossing(); // Prunes crossing of all non-crossing

        Edge lowest = crossing.get(0);
        for (Edge e: crossing) {
            if (e.getWeight() < lowest.getWeight()) {
                lowest = e;
            }
        }

        return lowest;
    }

    // Prunes crossing of all non-crossing edges
    public void pruneCrossing() {
        Syste.out.println("pruning");
        Iterator<Edge> iterator = crossing.iterator();
        while (iterator.hasNext()) {
            Edge e = iterator.next();

            // Are both ends of edge outside the bag? If so, remove this edge from list of crossing
            if (outside.contains(e.getOrigin()) && outside.contains(e.getDest())) {
                iterator.remove();
            }
        }
    }

    

    public boolean hasNodesInside() {
        if (inside.isEmpty()) {
            return false;
        }

        return true;
    }

    private void addEdgesToCrossing(Node node) {
        List<Edge> newCrossing = bag.getNodeEdges(node);

        for (Edge e : newCrossing) {
            if (!crossing.contains(e)) {
                crossing.add(e);
            }
        }
    }
}
