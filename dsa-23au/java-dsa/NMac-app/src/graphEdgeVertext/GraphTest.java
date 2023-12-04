package graphEdgeVertex;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GraphTest {

    private Graph graph;

    @Before
    public void setUp() {
        graph = new Graph();
        // Test graph 
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addEdge("A", "B", 10);
        graph.addEdge("B", "C", 15);
        graph.addEdge("A", "C", 20);
        graph.addEdge("C", "D", 5);
        graph.addEdge("B", "D", 10);
        
    }

    @Test
    public void testPrimsAlgorithm() {
        graph.executePrimsAlgorithm("A");

        // Assertions to check if the MST is correct
        assertTrue(graph.verticesMap.get("A").isVisited);
        assertTrue(graph.verticesMap.get("B").isVisited);
        assertTrue(graph.verticesMap.get("C").isVisited);
        assertTrue(graph.verticesMap.get("D").isVisited);

        // Check if the correct edges are included in the MST
        assertTrue(graph.verticesMap.get("A").edges.get(graph.verticesMap.get("B")).isIncluded);
        assertTrue(graph.verticesMap.get("B").edges.get(graph.verticesMap.get("D")).isIncluded);
        assertTrue(graph.verticesMap.get("C").edges.get(graph.verticesMap.get("D")).isIncluded);
        assertFalse(graph.verticesMap.get("A").edges.get(graph.verticesMap.get("C")).isIncluded);
        assertFalse(graph.verticesMap.get("B").edges.get(graph.verticesMap.get("C")).isIncluded);

        // Check the total weight of the MST
        int totalWeight = graph.verticesMap.get("A").edges.get(graph.verticesMap.get("B")).weight +
                graph.verticesMap.get("B").edges.get(graph.verticesMap.get("D")).weight +
                graph.verticesMap.get("C").edges.get(graph.verticesMap.get("D")).weight;
        assertEquals(25, totalWeight); // Assumes the MST is A-B, B-D, C-D

        // Add more complex scenarios and assertions as necessary
    }

    // Additional tests for different start nodes or graph configurations can be added here
}
