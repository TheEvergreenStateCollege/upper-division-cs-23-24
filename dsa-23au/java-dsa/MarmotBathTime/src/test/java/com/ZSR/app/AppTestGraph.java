package com.ZSR.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTestGraph 
{

    @Test
    public void testGetEdge()
    {
        Graph<String,Integer> graph = new GraphMatrix<>(10);
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addEdge("A", "B", 12);
        Graph.Edge<Integer> e = graph.getEdge("A", "B");
        assertEquals(12, (int)e.getWeight());
        Graph.Edge<Integer> e2 = graph.getEdge("B", "A");
        assertEquals(12, (int)e2.getWeight());
    }


    /**
     * Edge comparator always returns less-than on first comparison,
     * and ignores null
     */
    @Test
    public void testEdgeComparatorInitialMin()
    {

        Graph<String,Integer> graph = new GraphSparse<>();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addEdge("A", "B", 12);
        Graph.Edge<Integer> e = graph.getMinEdgeToUnvisitedNode("A");
        assertEquals(12, (int)e.getWeight());
    }
}
