// package com.pswishcorp.app;

// /**
//  * <AnyType extends Comparable<? super AnyType>> borrowed from the weiss class book
//  * replacing percDown with buildHeap
//  */

// public class HeapSort2 {
//     private static <AnyType extends Comparable<? super AnyType>> void swapReferences(AnyType[] array, int i, int j) {
//         AnyType temp = array[i];
//         array[i] = array[j];
//         array[j] = temp;
//     }

//     private static <AnyType extends Comparable<? super AnyType>> void buildHeap(AnyType[] array, int n, int i) {
//         int largest = i;
//         int leftChild = 2 * i + 1;
//         // int rightChild = 2 * i + 2;

//         if (leftChild < n && array[leftChild].compareTo(array[largest]) > 0) {
//             largest = leftChild;
//         }

//         if (rightChild < n && array[rightChild].compareTo(array[largest]) > 0) {
//             largest = rightChild;
//         }

//         if (largest != i) {
//             swapReferences(array, i, largest);
//             buildHeap(array, n, largest);
//         }
//     }

//     public static <AnyType extends Comparable<? super AnyType>> void heapSort(AnyType[] array) {
//         int n = array.length;

//         // Build a max heap
//         for (int i = n / 2 - 1; i >= 0; i--) {
//             buildHeap(array, n, i);
//         }

//         // Extract elements one by one
//         for (int i = n - 1; i > 0; i--) {
//             swapReferences(array, 0, i); /* delete max */
//             buildHeap(array, i, 0);
//         }
//     }
// }
