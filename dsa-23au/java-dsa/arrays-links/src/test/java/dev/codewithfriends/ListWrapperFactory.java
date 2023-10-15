package dev.codewithfriends;

import org.junit.Ignore;

import java.util.List;

import static org.junit.Assert.*;

public abstract class ListWrapperFactory {

    private final static String[] groceryItems = {"Apples", "Bananas","Carrots","Grapes"};

    List<String> l;

    public void setUp() {
        l = createList(10);
    };

    public abstract List<String> createList(int maxSize);

    public void testToArray() {
        String[] newArray = new String[groceryItems.length];
        l.toArray(newArray);
        for (int i = 0; i < newArray.length; i += 1) {
            assertEquals(groceryItems[i], newArray[i]);
        }
    }

    public void testContains() {
        assertFalse(l.contains(groceryItems[0]));
        l.add(groceryItems[0]);
        assertTrue(l.contains(groceryItems[0]));
    }

    public void testAddAndSize() {

        for (int i = 0; i < 4; i++) {
            l.add(groceryItems[i]);
            assertEquals(groceryItems[i], l.get(i));
            assertEquals(i+1, l.size());
        }

        int k = 2;

        assertEquals("Apples", l.get(0));
        assertEquals("Bananas", l.get(1));
        assertEquals("Carrots", l.get(k));
        assertEquals("Grapes", l.get(k + 1));

        // Insert "Broccoli" at index 2 in list L
        l.add(k, "Broccoli");

        assertEquals("Apples", l.get(0));
        assertEquals("Bananas", l.get(1));
        assertEquals("Broccoli", l.get(k));
        assertEquals("Carrots", l.get(k + 1));
        assertEquals("Grapes", l.get(k + 2));
    }

    public void testSize(){
        assertEquals(0, l.size());
    }

    public void test_isEmpty(){
        assertTrue(l.isEmpty());
    }

}

