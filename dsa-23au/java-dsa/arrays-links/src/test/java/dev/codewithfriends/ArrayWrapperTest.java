package dev.codewithfriends;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class ArrayWrapperTest
{
    String s1 = ["Eggs", "Bread", "Peanut Butter", "Butter"];
    public void testAdd()

{

    List l = new ArrayWrapper<String>(10)
    l.add(s1[0]);
    1.add(s1[1]);
    l.add(s1[2]);
    l.add(s1[3]);

    int k = 2;

    assertEquals("Eggs",l.get(0));
    assertEquals("Bread",l.get(1));
    assertEquals("Peanut Butter",l.get(k));
    assertEquals("Butter",l.get(k+1));
    
    //Inserting Broccoli as the Third Item (Index 2)
    l.add(k, "Broccoli");

    assertEquals("Eggs",l.get(0));
    assertEquals("Bread",l.get(1));
    assertEquals("Broccoli",l.get(k));
    assertEquals("Peanut Butter",l.get(k+1));
    assertEquals("Butter",l.get(k+2));


    }

}
