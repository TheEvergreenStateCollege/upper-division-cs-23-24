package com.ndeanon25.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class PrimsMST{
    private int v;
    private List<List<Edge>> graph;

    public PrimsMST(int verticles){
        this.v = verticles;
        this.graph = new ArrayList<>(verticles);
        for(int i = 0; i < verticles; i++){
            this.graph.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight){
        Edge edge = new Edge(source, destination, weight);
        graph.get(source).add(edge);
        edge = new Edge(destination, source, weight);
        graph.get(destination).add(edge);
    }

    public void primMST() {
        boolean[] inMST = new boolean[v];
        Edge[] parent = new Edge[v];
        int[] key = new int[v];
        Arrays.fill(key, Integer.MAX_VALUE);

        PriorityQueue<Edge> minHeap = new PriorityQueue<>();
        key[0] = 0;
        minHeap.add(new Edge(-1, 0, 0));

        while (!minHeap.isEmpty()) {
            Edge current = minHeap.poll();
            int u = current.des;

            if (inMST[u])
                continue;

            inMST[u] = true;
            for (Edge neighbor : graph.get(u)) {
                int v = neighbor.des;
                int weight = neighbor.weight;
                if (!inMST[v] && weight < key[v]) {
                    key[v] = weight;
                    parent[v] = neighbor;
                    minHeap.add(new Edge(u, v, key[v]));
                }
            }
        }

        // Print the MST
        for (int i = 1; i < v; i++) {
            System.out.println("Edge: " + parent[i].src + " - " + parent[i].des + " Weight: " + parent[i].weight);
        }
    }

    public static void main(String[] args) {
        int V = 5;
        PrimsMST graph = new PrimsMST(V);
        graph.addEdge(1, 2, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);

        System.out.println("Minimum Spanning Tree:");
        graph.primMST();
    }
}
