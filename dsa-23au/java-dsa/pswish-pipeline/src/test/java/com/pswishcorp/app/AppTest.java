package com.pswishcorp.app;
import static org.junit.Assert.*;
import org.junit.Test;

public class AppTest {

    @Test
    public void testInsertionSort() {
        // integers are easy to compare with < > ==
        int[] inputList = {
                12, 3, 21, 8, 1, 47, 50, 88, 3, 13
        };
        // 1 3 3 8 12 13 21 47 50 88

        int[] result = App.insertionSort(inputList);
        int[] expectedSorted = {1, 3, 3, 8, 12, 13, 21, 47, 50, 88};
        // inputList.length gives you the length of the array
        // write a loop here that compares every result[i] with what
        // you think is the correct value in the sorted list
        // write loop
            
            for (int i = 1; i < result.length; i++) {
                assertEquals(Integer.valueOf(expectedSorted[i]), Integer.valueOf(result[i]));
        }
    }
}
