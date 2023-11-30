package com.mycompany.app;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testPrimsMST()
    {
        Graph g = new Graph();
        g.addNode("RN");
        g.addNode("CP");
        g.addNode("KB");
        g.addNode("SB");
        g.addNode("RG");
        g.addNode("IGA");
        g.addNode("CK");
        g.addNode("M");
        g.addNode("GK");
        g.addEdge("RN", "CP", 1);
        g.addEdge("RN", "KB", 1);
        g.addEdge("CP", "KB", 1);
        g.addEdge("CP", "GK", 1);
        g.addEdge("CP", "CK", 1);
        g.addEdge("KB", "SB", 1);
        g.addEdge("KB", "RG", 1);
        g.addEdge("KB", "CK", 1);
        g.addEdge("CK", "GK", 1);
        g.addEdge("CK", "M", 1);
        g.addEdge("CK", "IGA", 1);
        g.addEdge("CK", "RG", 1);
        g.addEdge("RG", "IGA", 1);
        g.addEdge("RG", "SB", 1);
        g.addEdge("M", "GK", 1);
        g.addEdge("M", "IGA", 1);

        Graph mst = g.primsMST();
       int expectedWeight = calculateExpectedMSTWeight(); 

        int totalWeight = mst.getTotalWeight();
        assertTrue("Total weight of MST should be " + expectedWeight, totalWeight > expectedWeight);

        assertTrue("MST should include all nodes", mst.getAllNodes().size() == g.getAllNodes().size());

        
        assertTrue("MST should contain edge between RN and CP", mst.hasEdge("RN", "CP"));
        assertTrue("MST should contain edge between RN and KB", mst.hasEdge("RN", "KB"));
        assertTrue("MST should contain edge between CP and KB", mst.hasEdge("CP", "KB"));
        assertTrue("MST should contain edge between CP and GK", mst.hasEdge("CP", "GK"));
        assertTrue("MST should contain edge between CP and CK", mst.hasEdge("CP", "CK"));
        assertTrue("MST should contain edge between KB and SB", mst.hasEdge("KB", "SB"));
        assertTrue("MST should contain edge between KB and CK", mst.hasEdge("KB", "CK"));
        assertTrue("MST should contain edge between CK and GK", mst.hasEdge("CK", "GK"));
        
        
    }

    private int calculateExpectedMSTWeight(Set<Graph.Edge> MST) {
       //iterate over edges in mst and add togeter theier weight
        for (Graph.Edge edge : MST ) {
            
    }
}
    }
}
