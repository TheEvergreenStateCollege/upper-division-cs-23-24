package com.jonaheadie;

import java.util.List;
import java.util.ArrayList;

public class Edge {
    private Node origin;
    private Node dest;
    private int weight;

    public Edge(Node origin, Node dest, int weight) {
        this.origin = origin;
        this.dest = dest;
        this.weight = weight;
    }

    public void setOrigin(Node origin) {
        this.origin = origin;
    }

    public Node getOrigin() {
        return origin;
    }

    public void setDest(Node dest) {
        this.dest = dest;
    }

    public Node getDest() {
        return dest;
    }

    public boolean isEdgeBetween(String node1, String node2) {
        if ((origin.getName() == node1 || dest.getName() == node1) &&
            (origin.getName() == node2 || dest.getName() == node2)) {
            return true;
        }

        return false;
    }

    public boolean isEdgeBetween(Node node1, Node node2) {
        if ((origin == node1 || dest == node1) &&
            (origin == node2 || dest == node2)) {
            return true;
        }

        return false;
    }

    public boolean isEdgeTo(String name) {
        if (origin.getName() == name || dest.getName() == name) {
            return true;
        }

        return false;
    }

    public boolean isEdgeTo(Node node) {
        if (origin == node || dest == node) {
            return true;
        }

        return false;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
