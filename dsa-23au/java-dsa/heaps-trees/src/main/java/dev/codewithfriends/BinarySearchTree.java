package dev.codewithfriends;

public class BinarySearchTree<T extends Comparable<? super T>>{

    private BinaryNode<T> root;

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T x)
    {
        return contains(x, root);
    }

    public T findMin() throws UnderFlowException {
        if ( isEmpty() ){
            throw new UnderFlowException();
        } 
        return findMax( root ).data;
    }

    public T findMax() {
        throw new RuntimeException("Not yet implemented");
    }

    public void insert (T x) {
        root = insert(x, root);
    }

    private boolean contains(T x, BinaryNode<T> t)
    // Internal method tofind an item in a subtree.
    // @parm x is item to search for
    // @parm t is node that roots the subtree.
    // @parm return true if the item is found, False otherwise

    {
        if(t == null)
            return false;

        int compareResult = x.compareTo(t.data);

        if(compareResult < 0) {
            return contains(x, t.left);
        } else if(compareResult > 0) {
            return contains(x, t.right);
        } else {
            return true;
        }
    }

    private BinaryNode<T> findMax(BinaryNode<T> t) {
        throw new RuntimeException("Not yet implemented.");
    }

    private BinaryNode<T> insert(T x, BinaryNode<T> t) {
        if (t == null) {
            return new BinaryNode<>(x);
        }

        int compareResult = x.compareTo( t.data );

        if (compareResult < 0) {
            t.left = insert( x, t.left );
        } else if (compareResult > 0) {
            t.right = insert( x, t.right );
        } // else duplicate
        return t;
    }

    private BinaryNode<T> remove(T x, BinaryNode<T> t) {
        throw new RuntimeException("Not yet implemented.");
    }

}