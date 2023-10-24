package dev.codewithfriends;
import java.util.LinkedList;
import java.util.List;

public class BSTMain {
    
    public static boolean isVisited(BinaryNode<Integer> node, List<Integer> visited) {
        for (int i=0; i < visited.size(); i++) {
            if (node.data.equals(visited.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static List<Integer> dfs(BinaryNode<Integer> node, List<Integer> visited) {

        // mark the current node as visited
        visited.add(node.data);

        // if there is a left, or left unvisited
        if (node.left != null && isVisited(node.left,visited) == false) {
            dfs(node.left, visited);
        }
        // if there is a right, or right unvisited
        if (node.right != null && isVisited(node.right,visited) == false) {
            dfs(node.right, visited);
        }
        // then return
        return visited;
        // update
    }

  public static void main(String[] args) 
    {
        LinkedList<Integer> visited = new LinkedList<>();
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        List<Integer> result = dfs(bst.getRoot(), visited);



    }
}
