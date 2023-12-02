package com.devincompany.app;

import java.util.Comparator;
import java.util.Set;

public abstract class Graph<N extends Comparable<N>,E extends Comparable<E> > {

    public static interface Edge<E1> {
        public E1 getWeight();
        
    }
    // public static interface N1{

    // }


    protected static class EdgeComparator<E1 extends Comparable<E1>> implements Comparator<E1> {
        boolean initialMin; // edge comparators start always returning less-than
//this is the node start value minimum 
        public EdgeComparator() {
            initialMin = true;
        }
//this states the edge is going to always be true and start from the min
        @Override
        public int compare(E1 o1, E1 o2) {
            if (initialMin) {
                initialMin = false;
                return -1;
            }

// if the minimum is not at the starting value of (min) then lower the value by 1 
            if (o1 == null || o2 == null) {
                throw new NullPointerException("One or both edges to compare are null.");
            }
            return o1.compareTo(o2);
        }
//this describes the noe/bag thats being traversed to compare to the min 
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof EdgeComparator)) {
                return false;
            }
            EdgeComparator ec = (EdgeComparator) obj;
            return ec.initialMin == this.initialMin;
        }
// this names the Edge comparator and object and this object is compared and reset to the vaulue of the initial min to traver the graph 
    }

    public abstract boolean addEdge(N nodeKey1, N nodeKey2, E edge);
// defines abstract edge 

    public abstract boolean addNode(N nodeKey);
// defines abstract node
    public abstract N[] getNodes(E edge);
// grabs the Nodes of the Edge Value 
    public abstract Edge<E> getEdge(N nodeKey1, N nodeKey2);
// grabs the edge of the node value
    public abstract Edge<E> getMinEdgeToUnvisitedNode(N sourceNodeKey);
// returns edges value to min via the node key -1 
    public abstract void printMermaidDiagram();
// prints the graph
    public abstract Set<N> getNodes();
}