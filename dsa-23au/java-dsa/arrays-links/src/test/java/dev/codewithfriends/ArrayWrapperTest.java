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
    }
}
