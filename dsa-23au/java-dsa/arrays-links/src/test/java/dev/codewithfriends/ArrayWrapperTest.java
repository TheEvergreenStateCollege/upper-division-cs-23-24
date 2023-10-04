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
        assertEquals("Broccoli", l.get(k));
        assertEquals("Peanut Butter", l.get(k+1));
        assertEquals("Butter", l.get(k+2));
        l.set(2, "Cauliflower");
        assertEquals("Bread", l.get(0));
        assertEquals("Eggs", l.get(1));
        assertEquals("Cauliflower", l.get(k));
        assertEquals("Peanut Butter", l.get(k+1));
        assertEquals("Butter", l.get(k+2));
    }

    Book b1 = new Book("Some title", "some author");

    @Test
    public void testSize(){
        assertEquals(null, 0, 0, 0);
    }

    @Test
    public void test_isEmtpy(){
        List<String> l = new ArrayWrapper<String>(10);
        assertTrue(l.size() == 0);
    }
    @Test
    public void test_toArray(){
        // need lots of logic
        return;
    }
}

