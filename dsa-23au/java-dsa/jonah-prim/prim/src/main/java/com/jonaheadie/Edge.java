package com.jonaheadie;

public class Edge {
    class Node {
        String name;

        public Node(String name) {
            this.name = name;
        }
    }

    Node origin;
    Node dest;
    int weight;

    public Edge(Node origin, Node dest) {
        this.origin = origin;
        this.dest = dest;
    }
}
