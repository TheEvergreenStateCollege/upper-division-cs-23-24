import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MSTGraph {

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
        // isVisited = false means node is "in the bag"
        //           = true, means we pulled node out of bag
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

    public Edge getMinEdgeToUnvisitedNode(Vertex source) {
        int minWeightSoFar = Integer.MAX_VALUE;
        Edge minWeightEdgeSoFar = null;

        for (Entry<Vertex,Edge> entry : source.edges.entrySet()) {
            Edge e = entry.getValue();
            Vertex dest = entry.getKey();
            if (dest.isVisited) {
                continue;
            }
            if (e.weight < minWeightSoFar) {
                minWeightSoFar = e.weight;
                minWeightEdgeSoFar = e;
            }
        }
        return minWeightEdgeSoFar;
    }
}