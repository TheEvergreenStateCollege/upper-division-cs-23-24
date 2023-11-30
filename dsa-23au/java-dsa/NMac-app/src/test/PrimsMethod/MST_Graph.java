package graphEdgeVertex;

import java.util.Comparator;
import java.util.Set;

public abstract class MST_Graph<N extends Comparable<N>,E extends Comparable<E> > {

    public static interface Edge<E1> {
        public E1 getWeight();
    }

    protected static class EdgeComparator<E1 extends Comparable<E1>> implements Comparator<E1> {
        boolean initialMin; // edge comparators start always returning less-than

        public EdgeComparator() {
            initialMin = true;
        }

        @Override
        public int compare(E1 o1, E1 o2) {
            if (initialMin) {
                initialMin = false;
                return -1;
            }
            if (o1 == null || o2 == null) {
                throw new NullPointerException("One or both edges to compare are null.");
            }
            return o1.compareTo(o2);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof graphEdgeVertex.Graph.EdgeComparator)) {
                return false;
            }
            graphEdgeVertex.Graph.EdgeComparator ec = (graphEdgeVertex.Graph.EdgeComparator) obj;
            return ((graphEdgeVertex.Graph.EdgeComparator<?>) ec).initialMin == this.initialMin;
        }
    }

    // Overridden method from abstract Graph class
    @Override
    public boolean addEdge(String nodeKey1, String nodeKey2, Integer weight) {
        // Implement the method according to the specific logic of your Graph class
    }

    // Overridden method from abstract Graph class
    @Override
    public boolean addNode(String nodeKey) {
        // Implement the method according to the specific logic of your Graph class
    }

    // Overridden method from abstract Graph class
    @Override
    public graphEdgeVertex.Graph.Edge<Integer> getEdge(String nodeKey1, String nodeKey2) {
        // Implement the method according to the specific logic of your Graph class
    }

    // Overridden method from abstract Graph class
    @Override
    public graphEdgeVertex.Graph.Edge<Integer> getMinEdgeToUnvisitedNode(String sourceNodeKey) {
        // Implement the method according to the specific logic of your Graph class
    }

    // Overridden method from abstract Graph class
    @Override
    public void printMermaidDiagram() {
        // Implement a method to print the graph in Mermaid diagram format
    }

    // Existing code for the Edge and Vertex classes, along with other methods...

}
