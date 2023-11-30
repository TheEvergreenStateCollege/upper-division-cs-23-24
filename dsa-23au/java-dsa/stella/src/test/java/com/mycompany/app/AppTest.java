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
        g.addEdge("RN", "CP", 22);
        g.addEdge("RN", "KB", 24);
        g.addEdge("CP", "KB", 4);
        g.addEdge("CP", "GK", 17);
        g.addEdge("CP", "CK", 6);
        g.addEdge("KB", "SB", 8);
        g.addEdge("KB", "RG", 7);
        g.addEdge("KB", "CK", 10);
        g.addEdge("CK", "GK", 14);
        g.addEdge("CK", "M", 15);
        g.addEdge("CK", "IGA", 16);
        g.addEdge("CK", "RG", 12);
        g.addEdge("RG", "IGA", 14);
        g.addEdge("RG", "SB", 10);
        g.addEdge("M", "GK", 12);
        g.addEdge("M", "IGA", 14);

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
