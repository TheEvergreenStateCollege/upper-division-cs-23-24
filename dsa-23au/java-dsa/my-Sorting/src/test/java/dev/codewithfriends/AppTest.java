package dev.codewithfriends;
import static org.junit.Assert.*;
//import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    public void testInsertionSort() {
        // integers are easy to compare with < > ==
        int[] inputList = {
                12, 3, 21, 8, 1, 47, 50, 88, 3, 13
        };
        // 1 3 3 8 12 13 21 47 50 88

        int[] result = App.insertionSort(inputList);
        int expectedArray[] = {1,3,3,8,12,13,21,47,50,88};

          assertArrayEquals(expectedArray, result);

        }

    }



