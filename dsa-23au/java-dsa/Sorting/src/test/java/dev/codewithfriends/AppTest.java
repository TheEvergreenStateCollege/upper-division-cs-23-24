package dev.codewithfriends;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dev.codewithfriends.Sorting.App;

/**
 * Unit test for simple App.
 */
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class AppTest {

    @Test
    public void testInsertionSort() {
        int[] inputList = {12, 3, 21, 8, 1, 47, 50, 88, 3, 13};

        // Expected sorted array
        int[] expectedSorted = {1, 3, 3, 8, 12, 13, 21, 47, 50, 88};

        int[] result = App.insertionSort(inputList);

        // Use assertArrayEquals to compare the two arrays
        assertArrayEquals(expectedSorted, result);
    }
}
       