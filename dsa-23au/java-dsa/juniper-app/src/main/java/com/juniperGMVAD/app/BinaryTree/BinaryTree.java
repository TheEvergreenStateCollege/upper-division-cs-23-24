package com.juniperGMVAD.app.BinaryTree;

import java.util.Comparator;

public class BinaryTree <T> {
    Node<T> root;
    Comparator<T> comparator;

    BinaryTree (Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void insert(T value) {
        root = insertRecursive(root, value);
    }

    public boolean contains(T value) {

    }

    public Node delete(T value) {

    }

    public int size() {

    }

    public List<T> asList() {
        
    }

    private Node<T> insertRecursive (Node<T> current, T value) {
        if (current == null ) {
            return new Node<T>(value, comparator);
        }

        if (comparator.compare(value, current.value) < 0) { // if value less than current value, call addRecursive on left child
            current.left = insertRecursive(current.left, value);
        } else if (comparator.compare(value, current.value) > 0) { // if value greater than current value, call addRecursive on right child
            current.right = insertRecursive(current.right, value);
        } else {
            return current;
        }

        return current;
    }
}
