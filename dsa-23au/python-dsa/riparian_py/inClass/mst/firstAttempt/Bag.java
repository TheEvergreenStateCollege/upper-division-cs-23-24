
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Bag {
    
    class Edge{
        Node originNode;
        Node destNode;
        int edgeCost;
    
        public Edge (Node origin, Node destination, int cost) {
            edgeCost = cost;
            destNode = destination;
            originNode = origin;
        }
    }
    class Node{
    
        String name;
        int leftEdge = 10 + rand.nextInt(21);
        int rightEdge = 10 + rand.nextInt(21);
        bool visited = false;
    
    }



    Random rand = new Random();

    // 1. put all nodes into list


    
    List<E> nodeList = new ArrayList<>();
    List<E> visited = new ArrayList<>();
    List<E> unvisited = new ArrayList<>();


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
        nodeList.add(fakeNode1);
        nodeList.add(fakeNode2);
        nodeList.add(fakeNode3);
        nodeList.add(fakeNode4);
        nodeList.add(fakeNode5);
        nodeList.add(fakeNode6);
        visited.add(startNode);
    }

    public int findNextMove(Node nodeToProcess){

        int[] edges = new ArrayList();

        for (int i = 0 ; i < visited.size(); i++){


        }
    }
    
    public int findMST(){

        int mstAccum = 0;
        
        for (int i = 0; i < nodeList.size(); i++) {
            
            // 3. take shortest available edge from any visited node to any adjecent non-visited node, add to list of visited nodes
            if (nodeList[i].visited == true) {continue;}
            if (nodeList[i].leftEdge < nodeList[i].rightEdge) { mstAccum += nodeList[i].leftEdge; } 
            else { mstAccum += nodeList[i].rightEdge; }
            
        }
        
        System.out.println(mstAccum);
        return mstAccum;

        
    }
    
    public static void main(String[] args){
    
        
    }
    
    
    // 4. repeat step 3 until all nodes visited
    // 5. sum all travelled edges to determine cost ||
    

}