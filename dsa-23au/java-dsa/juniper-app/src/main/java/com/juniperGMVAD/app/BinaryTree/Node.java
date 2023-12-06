package com.juniperGMVAD.app.BinaryTree;

import java.util.Comparator;

public class Node <T> {
    T value;
    Node<T> left;
    Node<T> right;
    Comparator<T> comparator;

    public Node (T value, Comparator<T> comparator) {
        this.value = value;
        left = null;
        right = null;
    }

    
}
