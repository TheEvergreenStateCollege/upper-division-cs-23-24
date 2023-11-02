package dev.codewithfriends;

import org.junit.Test;
import static org.junit.Assert.*;

public class MergeSortTest {

    @Test
    public void testMergeSortWithEmptyArray() {

        Integer[] inputArray = {};
        Integer[] expectedArray = {};

        MergeSort.mergeSort(inputArray);
        assertArrayEquals(expectedArray, inputArray);
    }

    @Test
    public void testMergeSortWithSingleElementArray() {

        Integer[] inputArray = {8};
        Integer[] expectedArray = {8};

        MergeSort.mergeSort(inputArray);
        assertArrayEquals(expectedArray, inputArray);
    }

    @Test
    public void testMergeSortWithIntegerArray() {
        Integer[] inputArray = {5, 9, 1, 6, 13, 2, 4, 20};
        Integer[] expectedArray = {1, 2, 4, 5, 6, 9, 13, 20 };

        MergeSort.mergeSort(inputArray);
        assertArrayEquals(expectedArray, inputArray);
    }
}
