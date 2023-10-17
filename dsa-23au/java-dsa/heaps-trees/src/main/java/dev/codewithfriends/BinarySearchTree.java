package dev.codewithfriends;

public class BinarySearchTree<T extends Comparable<? super T>>{

    private static class BinaryNode<T>{
            // Constructors 
        BinaryNode( T theElement )
        { this( theElement, null, null); }

        BinaryNode( T theElement, BinaryNode<T> lt, BinaryNode<T> rt)
        { 
            element = theElement; 
            left = lt;
            right = rt;
        }

        T element;               // the data in the node
        BinaryNode<T> left;      // left child
        BinaryNode<T> right;     // right child.
    }

    private BinaryNode<T> root;

    public BinarySearchTree(){
        root =null;
    }

    public void makeEmpty(){
        root = null;
    }
    public boolean isEmpty(){
        return root == null;
    }

    public boolean contains(T x){
        return contains(x, root);
    }
    public T findMin(){
        if ( isEmpty() ){
            throw new UnderFlowException();
        } 
        return findMax( root ).element;
    }
    public void insert (T x) {
        root = insert(x, root);
    }
    public void remove (T x) {
        root = remove(x, root);
    }


    private boolean contains(T x, BinaryNode<T> t){
        if(t == null){
            return false;
        }

        int compareResult = x.compareTo(t.element);

        if(compareResult < 0){
            return contains(x, t.left);
        }
        else if(compareResult > 0){
            return contains(x, t.right);
        }
        else
            return true; //match
    }
}