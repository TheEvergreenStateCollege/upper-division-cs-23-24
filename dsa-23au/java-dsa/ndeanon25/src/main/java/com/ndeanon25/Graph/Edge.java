package com.ndeanon25.Graph;

public class Edge implements Comparable<Edge> {
    int src, des, weight;
    
    public Edge(int source, int destination, int weight){
        this.src = source;
        this.des = destination;
        this.weight = weight;
    }
    public int compareTo(Edge other){
        return Integer.compare(this.weight, other.weight);
    }
}