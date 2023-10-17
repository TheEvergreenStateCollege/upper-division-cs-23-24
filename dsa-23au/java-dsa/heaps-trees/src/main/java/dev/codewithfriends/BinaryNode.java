
package dev.codewithfriends;

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
