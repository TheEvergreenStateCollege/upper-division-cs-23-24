package dev.codewithfriends;

import org.junit.Test;

public class PrimsTest {

    @Test
    public void testPrimsMST(){
        Graph<String,Integer> g = new GraphSparse<String,Integer>();
        g.addNode("RN");
        g.addNode("CP");
        g.addNode("KB");
        g.addNode("CK");
        g.addNode("RG");
        g.addNode("SB");
        g.addNode("IGA");
        g.addNode("GK");
        g.addNode("M");

        g.addEdge("SB","KB",8);
        g.addEdge("CK","KB",10);
        g.addEdge("RG","SB",10);
        g.addEdge("RG","KB",7);
        g.addEdge("RG","CK",12);
        g.addEdge("RG","IGA",14);
        g.addEdge("IGA","KB",19);
        g.addEdge("IGA","CP",20);
        g.addEdge("IGA","CK",16);
        g.addEdge("IGA","M",14);
        g.addEdge("M","CK",15);
        g.addEdge("M","GK",12);
        g.addEdge("GK","CK",14);
        g.addEdge("GK","CP",17);
        g.addEdge("CP","CK",6);
        g.addEdge("CP","RN",22);
        g.addEdge("RN","KB",24);
        g.addEdge("KB","CP",4);
        // g.addEdge("","",);

        g.printMermaidDiagram();
    }

}
