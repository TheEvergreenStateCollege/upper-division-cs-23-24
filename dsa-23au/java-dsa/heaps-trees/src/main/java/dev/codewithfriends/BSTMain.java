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

    public static List<BinaryNode<Integer>> dfs(BinaryNode<Integer> node, LinkedList<BinaryNode<Integer>> visited) {

            // mark the current node as visited
            visited.add(node);

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
        return visited;
    }
    public static void main(String[] args) {
        LinkedList<BinaryNode<Integer>> visited = new LinkedList<>();
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        List<BinaryNode<Integer>> resultNodes = dfs(bst.getRoot(), visited);

    }
}
