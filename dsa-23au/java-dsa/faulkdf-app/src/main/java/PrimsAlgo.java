import java.util.*;

class PrimAlgorithm {
    static class Edge {
        int source, destination, weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Graph {
        int vertices;
        List<List<Edge>> adjacencyList;

        public Graph(int vertices) {
            this.vertices = vertices;
            adjacencyList = new ArrayList<>(vertices);
            for (int i = 0; i < vertices; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        public void addEdge(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            adjacencyList.get(source).add(edge);
            edge = new Edge(destination, source, weight);
            adjacencyList.get(destination).add(edge);
        }

        public void primMST() {
            boolean[] inMST = new boolean[vertices];
            PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(vertices, Comparator.comparingInt(o -> o.weight));

            int startVertex = 0; // Starting from vertex 0

            inMST[startVertex] = true;
            priorityQueue.addAll(adjacencyList.get(startVertex));

            while (!priorityQueue.isEmpty()) {
                Edge edge = priorityQueue.remove();

                if (!inMST[edge.destination]) {
                    System.out.println("Edge: " + edge.source + " - " + edge.destination + " | Weight: " + edge.weight);
                    inMST[edge.destination] = true;
                    priorityQueue.addAll(adjacencyList.get(edge.destination));
                }
            }
        }
    }

    public static void main(String[] args) {
        int vertices = 5;
        Graph graph = new Graph(vertices);

        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);

        graph.primMST();
    }
}
