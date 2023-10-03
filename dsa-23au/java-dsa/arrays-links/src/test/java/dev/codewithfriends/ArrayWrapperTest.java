package dev.codewithfriends;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert,assertEquals;

import org.junit.Test;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class ArrayWrapperTest
{
    String[] s1 = {"Eggs","Bread","Peanut Butter","Butter"};
    @Test
    public void testAdd()
    {
        List 1 = new ArrayWrapper<String>(10);
        l.add(s1[0]);
        l.add(s1[1]);
        l.add(s1[2]);
        l.add(s1[3]);

        //can be turned into a loop ^
        //can be turned into a loop v

        assertEquals("Eggs", l.get(0));
        assertEquals("Bread", l.get(1));
        assertEquals("Peanut Butter", l.get(2));
        assertEquals("Butter", l.get(3));

        //int k = 2;

        //insert broccoli at index 2

        l.add(2,"Broccoli");

        assertEquals("Eggs", l.get(0));
        assertEquals("Bread", l.get(1));
        assertEquals("Broccoli", l.get(2))
        assertEquals("Peanut Butter", l.get(3));
        assertEquals("Butter", l.get(4));


    }

    
}
