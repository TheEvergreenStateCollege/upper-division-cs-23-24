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

        graph.addEdge("SB", "RG", 10);
        graph.addEdge("SB", "KB", 8);

        graph.addEdge("RG", "KB", 7);
        graph.addEdge("RG", "IGA", 14);
        graph.addEdge("RG", "CK", 12);

        graph.addEdge("IGA", "KB", 19);
        graph.addEdge("IGA", "CP", 20);
        graph.addEdge("IGA", "CK", 16);
        graph.addEdge("IGA", "M", 14);

        graph.addEdge("M", "CK", 15);
        graph.addEdge("M", "GK", 12);

        graph.addEdge("GK", "CK", 14);
        graph.addEdge("GK", "CP", 17);

        graph.addEdge("CK", "CP", 6);
        graph.addEdge("CK", "KB", 10);

        graph.addEdge("KB", "CP", 4);
        graph.addEdge("KB", "RN", 24);

        graph.addEdge("CP", "RN", 22);

        graph.printAllEdges();

        Graph clone = graph.cloneGraph();

        System.out.println("\n\n");

        clone.printAllEdges();

        List<String> result = clone.getAllNodesNames();
        for (String s : result) {
            System.out.println(s);
        }
    }
}
