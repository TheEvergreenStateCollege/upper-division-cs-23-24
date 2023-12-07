package com.juniperGMVAD.app.BinaryHeap;

import java.util.Comparator;

//TODO: automatically resize array

public class BinaryHeap<T> {
    private final static int CAPACITY_DEFAULT = 64;
    private final static double LOAD_FACTOR_DEFAULT = 0.75;

    private int size = 0;
    private int capacity;
    private double loadFactor;

    private T[] heap;
    private Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this(comparator, CAPACITY_DEFAULT, LOAD_FACTOR_DEFAULT);
    }

    public BinaryHeap(Comparator<T> comparator, int capacity, double loadFactor) {
        this.comparator = comparator;
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.heap = (T[]) new Object[capacity];
    }

    public void insert(T value) {
        if (size >= (capacity * loadFactor)) resize();

        int newIndex = size;
        heap[newIndex] = value;

        if (newIndex != 0) { // If parent of new node exists
            int currIndex = newIndex;
            int parentIndex = parent(currIndex);

            while (parentIndex >= 0 && comparator.compare(heap[parentIndex], heap[currIndex]) < 0) { // While parent key is less than child's (and parent exists)
                // Swap value of child with parent
                T temp = heap[currIndex];
                heap[currIndex] = heap[parentIndex];
                heap[parentIndex] = temp;

                currIndex = parentIndex;
                parentIndex = parent(parentIndex);
            }
        }

        size++;
    }

    public T deleteMax() {
        T max = heap[0];
        heap[0] = heap[size - 1];
        maxHeapify(0);
        size--;
        return max;
    }

    private void resize() {
        capacity *= 2;
        T[] oldHeap = heap;
        heap = (T[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            heap[i] = oldHeap[i];
        }
    }

    private void maxHeapify(int index) {
        int currIndex = index;
        int leftIndex = leftChild(currIndex);
        int rightIndex = rightChild(currIndex);

        while ((leftIndex < size && comparator.compare(heap[currIndex], heap[leftIndex]) < 0) || 
               (rightIndex < size && comparator.compare(heap[currIndex], heap[rightIndex]) < 0)) { // While child larger
            if (comparator.compare(heap[currIndex], heap[leftIndex]) < 0) {
                // Swap value of child with parent
                T temp = heap[currIndex];
                heap[currIndex] = heap[leftIndex];
                heap[leftIndex] = temp;

                currIndex = leftIndex;
                leftIndex = leftChild(currIndex);
                rightIndex = rightChild(currIndex);
            } else if (comparator.compare(heap[currIndex], heap[rightIndex]) < 0) {
                // Swap value of child with parent
                T temp = heap[currIndex];
                heap[currIndex] = heap[rightIndex];
                heap[rightIndex] = temp;

                currIndex = leftIndex;
                leftIndex = leftChild(currIndex);
                rightIndex = rightChild(currIndex);
            } else { // Current index either leaf or has no children with greater values than itself
                return;
            }
        }
    }

    public void printHeapDebug() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + ", ");
        }

        System.out.print("\n");
    }

    private int parent(int index) {
        return Math.floorDiv((index - 1), 2);
    }

    private int leftChild(int index) {
        return (2 * index) + 1;
    }

    private int rightChild(int index) {
        return (2 * index) + 2;
    }
}
