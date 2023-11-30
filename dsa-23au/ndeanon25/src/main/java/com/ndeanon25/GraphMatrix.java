package com.ndeanon25;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class GraphMatrix<N extends Comparable<N>,E extends Comparable<E>> extends Graph<N,E> {

    Object[][] matrix; // matrix of edge values
    Map<N,Integer> nodeKeysToIndex;
    int i; //  current node
    int nodeCount;

    N[] nodeKeys;

    public GraphMatrix(int nodeCount) {
        this.nodeCount = nodeCount;
        matrix = new Object[nodeCount][nodeCount];
        nodeKeysToIndex = new HashMap<>();
        i = 0;
    }

    /**
     *
     * @param nodeKey1
     * @param nodeKey2
     * @param weight
     * @return true if we added a new non-null value
     *  false if either
     */
    @Override
    public boolean addEdge(N nodeKey1, N nodeKey2, E weight) {
        Integer nodeIndex1 = nodeKeysToIndex.get(nodeKey1);
        Integer nodeIndex2 = nodeKeysToIndex.get(nodeKey2);
        if (nodeIndex1 == null) {
            return false;
        }
        if (nodeIndex2 == null) {
            return false;
        }
        if (weight == null) {
            return false;
        }
        matrix[nodeIndex2][nodeIndex1] = new GraphSparse.Edge<E>(weight);
        matrix[nodeIndex1][nodeIndex2] = new GraphSparse.Edge<E>(weight);
        return true;
    }

    @Override
    public boolean addNode(N nodeKey) {
        if (nodeKeysToIndex.containsKey(nodeKey)) {
            // we already have this node
            return false;
        } else if (i >= this.nodeCount) {
            throw new ArrayIndexOutOfBoundsException(
                    String.format("Node count was %d but tried to insert new node %d", this.nodeCount, i)
            );
        } else {
            nodeKeysToIndex.put(nodeKey, i);
            i += 1;
            return true;
        }
    }

    @Override
    public GraphSparse.Edge<E> getEdge(N nodeKey1, N nodeKey2) {
        Integer nodeIndex1 = nodeKeysToIndex.get(nodeKey1);
        Integer nodeIndex2 = nodeKeysToIndex.get(nodeKey2);
        if (nodeIndex1 == null) {
            return null;
        }
        if (nodeIndex2 == null) {
            return null;
        }
        return (GraphSparse.Edge<E>)this.matrix[nodeIndex1][nodeIndex2];
    }

    @Override
    public GraphSparse.Edge<E> getMinEdgeToUnvisitedNode(N sourceNodeLabel) {
        throw new RuntimeException("getMinEdgeToUnvisitedNode not implemented  yet for graph adjacency matrix representation.");
    }

    @Override
    public void printMermaidDiagram() {
        Set<N> nodes = this.nodeKeysToIndex.keySet();
        Set<N> nodes2 = new TreeSet<>(nodes);
        System.out.printf("graph TD\n");
        for (N node : nodes) {
            for (N node2 : nodes2) {
                System.out.printf("    %s-->%s\n", node, node2);

            }
        }
    }

}

