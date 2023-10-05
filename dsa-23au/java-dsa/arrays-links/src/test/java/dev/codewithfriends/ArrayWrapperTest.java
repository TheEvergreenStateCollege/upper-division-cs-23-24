package dev.codewithfriends;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

/**
 * Unit test for ArrayWrapper class.
 */
public class ArrayWrapperTest {
    String[] s1 = {"Eggs", "Bread", "Peanut Butter", "Butter"};

    @Test
    public void testAdd() {
        List<String> L = new ArrayList<>(); // Use ArrayList here, not ArrayWrapper

        for (int i = 0; i < 4; i++) {
            L.add(s1[i]);
        }

        int k = 2;

        assertEquals("Eggs", L.get(0));
        assertEquals("Bread", L.get(1));
        assertEquals("Peanut Butter", L.get(k));
        assertEquals("Butter", L.get(k + 1));

        // Insert "Broccoli" at index 2 in list L
        L.add(k, "Broccoli");

        assertEquals("Eggs", L.get(0));
        assertEquals("Bread", L.get(1));
        assertEquals("Broccoli", L.get(k));
        assertEquals("Peanut Butter", L.get(k + 1));
        assertEquals("Butter", L.get(k + 2));

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
        int k = 2;
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
    public void testAdd() {
        List<String> L = new ArrayList<>(); // Use ArrayList here, not ArrayWrapper

        for (int i = 0; i < 4; i++) {
            L.add(s1[i]);
        }

        int k = 2;

        assertEquals("Eggs", L.get(0));
        assertEquals("Bread", L.get(1));
        assertEquals("Peanut Butter", L.get(k));
        assertEquals("Butter", L.get(k + 1));

        // Insert "Broccoli" at index 2 in list L
        L.add(k, "Broccoli");

        assertEquals("Eggs", L.get(0));
        assertEquals("Bread", L.get(1));
        assertEquals("Broccoli", L.get(k));
        assertEquals("Peanut Butter", L.get(k + 1));
        assertEquals("Butter", L.get(k + 2));
    }
}

