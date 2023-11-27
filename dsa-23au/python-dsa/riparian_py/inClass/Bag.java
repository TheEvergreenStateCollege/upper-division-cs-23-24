
import java.util.ArrayList;
import java.util.List;

public class Bag {

    // || 1. put all nodes into list
    class Node{

        int edge1 = 10;
        int edge2 = 15;
        int edge3 = 20;
        
    }
    
    List<E> originalNodeList = new ArrayList<>();
    List<E> unvisitedNodeList = new ArrayList<>();


    Node fakeNode1 = new Node();
    Node fakeNode2 = new Node();
    Node fakeNode3 = new Node();
    Node fakeNode4 = new Node();
    Node fakeNode5 = new Node();
    Node fakeNode6 = new Node();

    
    // 2. take node at random, put into 2nd list of visited nodes
    // simulated taking node at random
    
    public Bag(){
        Node startNode = new Node();
        originalNodeList.add(fakeNode1);
        originalNodeList.add(fakeNode2);
        originalNodeList.add(fakeNode3);
        originalNodeList.add(fakeNode4);
        originalNodeList.add(fakeNode5);
        originalNodeList.add(fakeNode6);
        unvisitedNodeList.addAll(originalNodeList);
    }
    
    public int findMST(){

        int mstAccum = 0;
        
        while (unvisitedNodeList.size() > 0) {
            
            // 3. take shortest available edge from any visited node to any adjecent non-visited node, add to list of visited nodes
            int leftEdge = unvisitedNodeList[i].edge1;
            int rightEdge = unvisitedNodeList[i].edge2;

            if (leftEdge > rightEdge) { mstAccum += leftEdge; } 
            else { mstAccum += rightEdge; }
            
            unvisitedNodeList.remove(i);
        }
        
        System.out.println(mstAccum);
        return mstAccum;


    }
    
    
    // 4. repeat step 3 until all nodes visited
    // 5. sum all travelled edges to determine cost ||
    

}