package dev.codewithfriends;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */

public class ArrayWrapperTest
{
    String[] s1 = {"Bread","Eggs","Peanut Butter","Butter"};
    @Test
    public void testAdd(){
        //Create list
        List<String> l = new ArrayWrapper<String>(10);
        for (int i = 0; i < s1.length;i++) {
            l.add(s1[i]);
            assertEquals(s1[i], l.get(i));
        }
        int k = 2;
        //Insert Broccoli at index 2 in l, k=2
        l.add(k,"Broccoli");
        assertEquals("Bread", l.get(0));
        assertEquals("Eggs", l.get(1));
        assertEquals("Broccoli", l.get(k+1));
        assertEquals("Peanut Butter", l.get(k+1));
        assertEquals("Butter", l.get(k+2));

    }

    Book b1 = new Book("Some title", "some author");

}
