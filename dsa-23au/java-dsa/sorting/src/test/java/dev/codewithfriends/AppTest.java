package dev.codewithfriends;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testInsertionSort()
    {
        Integer[] testArray = {5,4,3,2,1,0};

        Integer[] result = App.insertionSort(testArray);

        // assertEquals(Integer.valueOf(0),  Integer.valueOf(result[0]));
        for(int i = 0; i < testArray.length; i++){
            assertEquals(Integer.valueOf(i),  Integer.valueOf(result[i]));
        }
    }
}
