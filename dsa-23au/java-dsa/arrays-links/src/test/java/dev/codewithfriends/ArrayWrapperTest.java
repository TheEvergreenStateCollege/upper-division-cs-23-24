package dev.codewithfriends;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList; // Added import statement for ArrayList


/**
 * Unit test for simple App.
 */
public class ArrayWrapperTest
{
    String[] s1 = {"Eggs", "Bread", "Peanut Butter", "Butter"};
    @Test
    
    public void testAdd()
    {
        List <String> L = new ArrayWrapper<>(10);
        
        for (int i = 0; i < 4; i++) { // Loop from 0 to 3 (inclusive)
            L.add(s1[i]); // Add s1[i] to list1
        }

           int k = 2; 
           
           assertEquals("Eggs", L.get(0));
           assertEquals("Bread", L.get(1));
           assertEquals("Peanut Butter", L.get(k));
           assertEquals("Butter", L.get(k + 1));
           
            
           
           //insert broccoli at index 2 in list L 
           L.add(k, "Broccoli");
           
           assertEquals("Eggs", L.get(0));
           assertEquals("Bread", L.get(1));
           assertEquals("Broccoli", L.get(k));
           assertEquals("Peanut Butter", L.get(k + 1));
           assertEquals("Butter", L.get(k + 2));

    }

 }
    
   