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
    String[] s1 = {"Eggs", "Bread", "Peanut Butter", "Butter"};
    @Test
    public void testAdd
    {
        List l = new ArrayWrapper<String>(10);
        l.add(s1[0]);
        l.add(s1[1]);
        l.add(s1[2]);
        l.add(s1[3]);
        //L not 1 lol
        int k=2;

        assertEquals("Eggs",1.get(0));
        assertEquals("Bread",1.get(1));
        assertEquals("Peanut Butter",1.get(k+1));
        assertEquals("Butter",1.get(k+2));
        
        1.add(2, "Broccoli");

         assertEquals("Eggs",1.get(0));
        assertEquals("Bread",1.get(1));
        
        assertEquals("Peanut Butter",1.get(k+1));
        assertEquals("Butter",1.get(k+2));

    }
}
