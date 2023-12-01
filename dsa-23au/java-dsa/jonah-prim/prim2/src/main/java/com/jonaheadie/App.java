package com.jonaheadie;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Graph graph = new Graph();

        graph.addNode("SB");
        graph.addNode("RG");
        graph.addNode("IGA");
        graph.addNode("M");
        graph.addNode("CK");
        graph.addNode("KB");
        graph.addNode("CP");
        graph.addNode("GK");
        graph.addNode("RN");
        /*
        List<String> outside = graph.getAllOutsideNodes();

        for (String s : outside) {
            System.out.println(s);
        }

        List<Edge> bound = graph.getAllBoundaryEdges();

        for (Edge e : bound) {
            System.out.println(e.getFirst() + ", " + e.getSecond());
        }

        if (graph.isNodeIsolated("CK")) {
            System.out.println("CK is isolated");
        }

        List<String> all = graph.getAllNodes();

        for (String s: all) {
            System.out.println(s);
        }
        */
        graph.addEdge("GK", "CP", 30);
        graph.removeEdge("GK", "CP");
        graph.printAllEdges();
        graph.printAllNodes();

        if (!graph.isNodeConnected("RG")) {
            System.out.println("yes");
        }

        if (!graph.isNodeConnected("CP")) {
            System.out.println("no");
        }
    }
}
