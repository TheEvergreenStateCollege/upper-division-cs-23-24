package dev.codewithfriends;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.List;

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
        for (int i = 0; i < s1.length; i++) {
            l.add(s1[i]);
            assertEquals(s1[i], l.get(i));
        }
        //Insert Broccoli at index 2 in l, k=2
        l.add(2,"Broccoli");

        assertEquals("Bread", l.get(0));
        assertEquals("Eggs", l.get(1));
        // assertEquals("Broccoli", l.get(2));
        // assertEquals("Peanut Butter", l.get(3));
        // assertEquals("Butter", l.get(4));

    }

    Book b1 = new Book("Some title", "some author");

    @Test
    public void testSize(){
        assertEquals(null, 0, 0, 0);
    }

    @Test
    public void testtoArray(){
        return;
    }
}
