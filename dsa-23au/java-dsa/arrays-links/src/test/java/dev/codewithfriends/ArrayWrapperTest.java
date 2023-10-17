package dev.codewithfriends;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayWrapperTest {

    String[] s1 = {"Eggs", "Bread", "Peanut Butter", "Butter"};

    @Test
    public void testAdd() {
        List<String> L = new ArrayWrapper<String>(10); 
        L.add(s1[0]);
        L.add(s1[1]);
        L.add(s1[2]);
        L.add(s1[3]);

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
        assertEquals("Butter", L.get(k + 2);
    }

    @Test
    public void testSize() {
        List<String> L = new ArrayWrapper<String>(10);
        assertEquals(0, L.size());
    }

    @Test
    public void test_isEmpty() {
        List<String> L = new ArrayWrapper<String>(10);
        assertTrue(L.isEmpty());
    }

    @Test
    public void test_toArray() {
       
    }
}


    public void testAdd() {
        List<String> L = new ArrayList<>(); // Use ArrayList here, not ArrayWrapper

        for (int i = 0; i < 4; i++) {
            L.add(s1[i]);
            assertEquals(s1[i], l.get(i));
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
>>>>>>> eb7382b357699c11fa06ca8c642963bf9afeed9f
    }
}

