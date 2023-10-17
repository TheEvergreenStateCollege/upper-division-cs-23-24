package dev.codewithfriends;


public class BinarySearchTree<T extends Comparable<? super T>>{

    public class BinaryNode<T> {
     

        BinaryNode(T theElement) {
            this(theElement, null, null);
        }

        BinaryNode(T theElement, BinaryNode<T> lt, BinaryNode<T> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }
        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;
    }
    
    private BinaryNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public boolean contains(T x) {
        return contains(x);
    }
    
}
