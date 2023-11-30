package com.practice.app;
import java.util.*;

public class Graph {
   /* public class Node
    {
        String name;
        public Node (String name){
            this.name = name;
        }

    }
    public class Edge
    {
        int weight; 
        Node sourceNode; 
        Node destNode;
        public Edge(Node sourceNode, Node destNode, int weight)
        {
            this.sourceNode = sourceNode;
            this.destNode = destNode;
            this.weight = weight;
        }
    }
 */

    HashMap<String, ArrayList<Integer>> graph = new HashMap<>();

    public Graph()
    {
        graph = new HashMap<>();
    }

    public void addNode(String Name, ArrayList<Integer> edges)
   {
        graph.put(Name, edges);
   }







    
}
