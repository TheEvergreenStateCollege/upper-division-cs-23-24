package com.ActivityTor.app;

public class BST < T extends Comparable<? super T>>{

    private BSTNode<T> root;
    
    public BSTNode<T> getRoot(){
        return root;
    }

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

    public Object[] findMin(){
        Object[] retR = root.findMin();
        return retR;
    }

    public Object[] findMax(){

        Object[] retR = root.findMax();
        return retR;
        
    }

    public void insert (String date, T x) 
    {
        root = insert(x, date, root);
    }

    private boolean contains(T x, BSTNode<T> t)
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

    private BSTNode<T> insert(T x, String date, BSTNode<T> t) {
        if (t == null) {
            return new BSTNode<>(x, date);
        }

        int compareResult = x.compareTo( t.data );

        if (compareResult < 0) {
            t.left = insert( x, date, t.left );
        } else if (compareResult > 0) {
            t.right = insert( x, date, t.right );
        } // else duplicate
          else if(compareResult == 0){
            t.dates.add(date);
          }
        return t;
    }

    private BSTNode<T> remove(T x, BSTNode<T> t) {
        throw new RuntimeException("Not yet implemented.");
    }

}
