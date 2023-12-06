// package com.pswishcorp.app;

// import org.junit.Test;
// import static org.junit.Assert.assertArrayEquals;

// public class HeapSort2Test {

//     @Test
//     public void testHeapSortInteger() {
//         Integer[] arr = { 12, 4, 11, 3, 1, 9, 6 };
//         Integer[] expectedSorted = { 1, 3, 4, 6, 9, 11, 12 };

//         HeapSort2.heapSort(arr);

//         assertArrayEquals(expectedSorted, arr);
//     }

//     @Test
//     public void testHeapSortString() {
//         String[] arr = {"apple", "banana", "cherry", "date", "fig"};
//         String[] expectedSorted = {"apple", "banana", "cherry", "date", "fig"};

//         HeapSort2.heapSort(arr);

//         assertArrayEquals(expectedSorted, arr);
//     }

//     @Test
//     public void testHeapSortEmptyArray() {
//         Integer[] arr = {};
//         Integer[] expectedSorted = {};

//         HeapSort2.heapSort(arr);

//         assertArrayEquals(expectedSorted, arr);
//     }

//     @Test
//     public void testHeapSortWithDuplicates() {
//         Integer[] arr = { 12, 4, 11, 6, 1, 9, 6 };
//         Integer[] expectedSorted = { 1, 4, 6, 6, 9, 11, 12 };

//         HeapSort2.heapSort(arr);

//         assertArrayEquals(expectedSorted, arr);
//     }
// }
