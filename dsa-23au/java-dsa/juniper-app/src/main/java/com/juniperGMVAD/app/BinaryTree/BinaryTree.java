package com.juniperGMVAD.app.BinaryTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    public void delete(T value) {
        root = deleteRecursive(root, value);
    }

    public T search(T value) {
        return searchRecursive(root, value);
    }

    /**
     * Sets key in tree equal to value, or inserts it if non-existent. Allows user to replace keys in tree with value
     * @param value Key to be inserted into tree, or to replace existing key
     * @return Previous key object of specified key value, null if non-existent
     */
    public T set(T value) {
        return setRecursive(root, value);
    }

    public List<T> asList() {
        List<T> list = new ArrayList<T>();
        asListRecursive(root, list);
        return list;
    }

    /**
     * Method which returns an ArrayList of the T values within the tree which are in the range specified by the arguments, inclusive
     * @param min Minimum value returned
     * @param max Maximum value returned
     * @return List<T> of nodes whose values are within range
     */
    public List<T> rangeAsList(T min, T max) {
        List<T> result = new ArrayList<T>();
        rangeAsListRecursive(root, result, min, max);
        return result;
    }

    /**
     * Helper method for building a list of a subsection of tree elements
     * @param current
     * @param list
     * @param min
     * @param max
     */
    private void rangeAsListRecursive(Node<T> current, List<T> list, T min, T max) {
        if (current == null) {
            return;
        }

        if (comparator.compare(current.value, min) < 0) { // value is less than min
            rangeAsListRecursive(current.right, list, min, max);
        } else if (comparator.compare(current.value, min) == 0) { // value equals min
            list.add(current.value);
            rangeAsListRecursive(current.right, list, min, max);
        } else if (comparator.compare(current.value, min) > 0 && comparator.compare(current.value, max) < 0 ) { // value is greater than min and less than max
            list.add(current.value);
            rangeAsListRecursive(current.right, list, min, max);
            rangeAsListRecursive(current.left, list, min, max);
        } else if (comparator.compare(current.value, max) == 0) { // value equals max
            list.add(current.value);
            rangeAsListRecursive(current.left, list, min, max);
        } else if (comparator.compare(current.value, max) > 0) { // value is greater than max
            rangeAsListRecursive(current.left, list, min, max);
        }
    }

    public int size() {
        int[] totalSize = {0}; // This is a single-celled array so that it can be passed by reference to recursive method
        sizeRecursive(root, totalSize);
        return totalSize[0];
    }

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

    private T searchRecursive(Node<T> current, T value) {
        if (current == null) {
            // Key not found
            //TODO: explicit error handling
            return null;
        }

        if (comparator.compare(current.value, value) == 0) {
            return current.value;
        }

        if (comparator.compare(value, current.value) < 0) {
            return searchRecursive(current.left, value);
        } else {
            return searchRecursive(current.right, value);
        }
    }

    /**
     * 
     * @param current
     * @param value
     * @return Previous value object of specified key value, null if non-existent
     */
    private T setRecursive(Node<T> current, T value) {
        // If value does not exist in tree, insert and return null
        if (current == null) {
            insert(value);
            return null;
        }

        if (comparator.compare(value, current.value) < 0) { // if value less than current value, call addRecursive on left child
            return setRecursive(current.left, value);
        } else if (comparator.compare(value, current.value) > 0) { // if value greater than current value, call addRecursive on right child
            return setRecursive(current.right, value);
        } else {
            // Current is correct value
            T oldValue = current.value;
            current.value = value;
            return oldValue;
        }
    }

    private Node<T> deleteRecursive(Node<T> current, T value) {
        if (current == null) {
            return null;
        }

        //System.out.print("delete on this: " );
        //System.out.println(current.value);

        if (comparator.compare(value, current.value) < 0) {
            current.left = deleteRecursive(current.left, value);
        } else if (comparator.compare(value, current.value) > 0) {
            current.right = deleteRecursive(current.right, value);
        } else { // value equals current.value, found correct element
            // Either left or right node is empty
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }

            // Node with two children
            current.value = minValue(current.right);
            current.right = deleteRecursive(current.right, current.value);
        }

        return current;
    }

    private void inOrderPrintRecursive(Node current) {
        if (current != null) {
            inOrderPrintRecursive(current.left);
            System.out.print(current.value + " ");
            inOrderPrintRecursive(current.right);
        }
    }

    private void asListRecursive(Node<T> current, List<T> list) {
        if (current != null) {
            asListRecursive(current.left, list);
            list.add(current.value);
            asListRecursive(current.right, list);
        }
    }

    private void sizeRecursive(Node<T> current, int[] totalSize) {
        if (current != null) {
            sizeRecursive(current.left, totalSize);
            totalSize[0] += 1;
            sizeRecursive(current.right, totalSize);
        }
    }
}
