import dev.codewithfriends;

public class BinarySearchTree<T extends Comparable<? super T>> {
    private static class BinaryNode<T> {}
    private BinaryNode<T> root;
    
    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }
    
} 
