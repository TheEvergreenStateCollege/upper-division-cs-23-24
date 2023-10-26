package dev.codewithfriends;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testInsertionSort() {
        // integers are easy to compare with < > ==
        int[] inputList = {
                12, 3, 21, 8, 1, 47, 50, 88, 3, 13
        };
        // 1 3 3 8 12 13 21 47 50 88

        int[] result = App.insertionSort(inputList);

        // inputList.length gives you the length of the array
        // write a loop here that compares every result[i] with what
        // you think is the correct value in the sorted list

        // write loop
            assertEquals(Integer.valueOf(8), Integer.valueOf(result[3]));
        }

    }
}
