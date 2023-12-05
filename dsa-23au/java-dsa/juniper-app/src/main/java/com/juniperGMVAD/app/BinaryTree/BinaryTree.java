package com.juniperGMVAD.app.BinaryTree;

import java.util.Comparator;

public class BinaryTree<T> {
    Node<T> root;
    Comparator<T> comparator;

    public BinaryTree (Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void printTreeInOrder() {
        inOrderPrintRecursive(root);
        System.out.println();
    }

    public void insert(T value) {
        root = insertRecursive(root, value);
    }

    public boolean contains(T value) {
        return containsRecursive(root, value);
    }

    public Node<T> delete(T value) {
        return deleteRecursive(root, value);
    }

    /*public int size() {

    }

    public List<T> asList() {
        
    }*/

    private T minValue(Node<T> current) {
        T minValue = current.value;

        while (current.left != null) {
            minValue = current.left.value;
            current = current.left;
        }

        return minValue;
    }

    private Node<T> insertRecursive(Node<T> current, T value) {
        if (current == null) {
            current = new Node<T>(value, comparator);
            return current;
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

    private boolean containsRecursive(Node<T> current, T value) {
        System.out.println("At: ");
        if (current == null) {
            return false;
        }

        if (comparator.compare(current.value, value) == 0) {
            return true;
        }

        if (comparator.compare(value, current.value) < 0) {
            return containsRecursive(current.left, value);
        } else {
            return containsRecursive(current.right, value);
        }
    }

    /*private Node<T> deleteRecursive(Node<T> current, T value) {
        //case 0, null
        return null;

        //case 1, no children

        //case 2, one child

        //case 2, two children

        /*if (comparator.compare(value, current.value) < 0) { // value less than current.value
            
        } else if (comparator.compare(value, current.value) < 0) { // value greater

        } else { // values the same

        }*/
        /*if (current == null) {
            return null;
        }

        if (current.left == null && current.right == null) { // no children
            current.value = minValue(current.right);
            current.right = deleteRecursive(current.right, current.value);
        }

        if (comparator.compare(value, current.value) < 0) {
            current.left = deleteRecursive(current.left, value);
        } else if (comparator.compare(value, current.value) > 0) {
            current.right = deleteRecursive(current.left, value);
        } else {
            // Value and current value are equal, element to be deleted and returned
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            } 
        }

        return root;
    }*/

    private void inOrderPrintRecursive(Node current) {
        if (current != null) {
            inOrderPrintRecursive(current.left);
            System.out.print(current.value + " ");
            inOrderPrintRecursive(current.right);
        }
    }
}
