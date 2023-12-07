package graphEdgeVertex;

import java.util.Comparator;

public abstract class MST_Graph<N extends Comparable<N>, E extends Comparable<E>> {

    public interface Edge<E1> {
        E1 getWeight();
    }

    protected static class EdgeComparator<E1 extends Comparable<E1>> implements Comparator<E1> {
        boolean initialMin;

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
            if (!(obj instanceof EdgeComparator)) {
                return false;
            }
            EdgeComparator<?> ec = (EdgeComparator<?>) obj;
            return ec.initialMin == this.initialMin;
        }
    }

    public abstract boolean addEdge(N nodeKey1, N nodeKey2, E edge);

    public abstract void addNode(N nodeKey);

    public abstract Edge<E> getEdge(N nodeKey1, N nodeKey2);

    public abstract Edge<E> getMinEdgeToUnvisitedNode(N sourceNodeKey);

    public abstract void printMermaidDiagram();

    // ... (Other code if any)
}

