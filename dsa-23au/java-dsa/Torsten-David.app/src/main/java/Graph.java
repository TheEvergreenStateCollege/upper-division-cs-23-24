import java.util.HashMap;
import java.util.Map;

public class Graph {
    
    public Map<String, Vertex> verticesMap = new HashMap<>();

    
    public class Edge{
        public int weight;
        public boolean isIncluded;  
        
        public Edge(int weight){
            this.weight = weight;
            this.isIncluded = false;
        }
    }

    public class Vertex{
        public String label = null;
        public Map<Vertex, Edge> edges = new HashMap<>();
        public boolean isVisited = false;

        public Vertex(String label){
            this.label = label;
            this.edges = new HashMap<>();
            this.isVisited = false;
        }
    }
    public void addNode(String label) {
       // Maps a label to a vertex.
        verticesMap.put(label, new Vertex(label));
    }


    public void addEdge(String srcNode, String destNode, int weight)
    {
      //Create node1 from the parameter source node
      Vertex node1 = verticesMap.get(srcNode); 
      Vertex node2 = verticesMap.get(destNode);

      Edge edge = new Edge(weight);
      
      node1.edges.put(node2, edge);
      node2.edges.put(node1, edge);

    }

}
