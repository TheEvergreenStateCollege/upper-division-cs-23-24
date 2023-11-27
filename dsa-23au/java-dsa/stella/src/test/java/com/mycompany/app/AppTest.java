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
        g.addEdge("RN", "CP");
        g.addEdge("RN", "KB");
        g.addEdge("CP", "KB");
        g.addEdge("CP", "GK");
        g.addEdge("Cp", "CK");
        g.addEdge("KB", "SB");
        g.addEdge("KB", "RG");
        g.addEdge("KB", "CK");
        g.addEdge("CK", "GK");
        g.addEdge("CK", "M");
        g.addEdge("CK", "IGA");
        g.addEdge("CK", "RG");
        g.addEdge("RG", "IGA");
        g.addEdge("RG", "SB");
        g.addEdge("M", "GK");
        g.addEdge("M", "IGA");
        
    }
}
