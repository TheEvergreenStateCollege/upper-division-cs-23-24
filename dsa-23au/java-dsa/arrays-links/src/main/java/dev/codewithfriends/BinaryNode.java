package dev.codewithfriends;

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
