package dev.codewithfriends;
import java.util.LinkedList;
import java.util.List;

public class BSTMain {
    
    public static boolean isVisited(BinaryNode node, LinkedList visited) {
        for (int i=0; i < visited.size(); i++) {
            if (node.equals(visited.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static List<BinaryNode> dfs(BinaryNode node, LinkedList visited) {
        // 1. setup
        // initialized v visited data structure
        // devin duc use linked list (doubly)
        BinaryNode curr = node;
        // 2. iterate (while/for loop) or recurse

            // body
            visited.add(node);

            // termination condition
            // if there is a left, or left unvisited
            if (node.left != null && isVisited(node.left,visited) == false) {
                dfs(node.left, visited);
            }
            // if there is a right, or right unvisited
            if (node.right != null && isVisited(node.right,visited) == false) {
                dfs(node.right, visited);
            }
            // then return
            // update

        // 3. post-processing (none)
        // 4. return
        visited.toArray();
    }
    public static void main(String[] args) {
        LinkedList<BinaryNode> visited = new LinkedList<>();
        List<BinaryNode> allNodes = new List<>();

    }
}
