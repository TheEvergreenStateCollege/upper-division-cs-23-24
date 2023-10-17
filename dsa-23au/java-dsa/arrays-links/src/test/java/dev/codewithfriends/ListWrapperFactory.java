package dev.codewithfriends;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Ignore
public abstract class ListWrapperFactory {

    private final static String[] groceryItems = {"Apples", "Bananas","Carrots","Grapes"};

    List<String> l;

    @Before
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

    @Test
    public void testContainsAfterAdd() {
        assertFalse(l.contains(groceryItems[0]));
        l.add(groceryItems[0]);
        assertTrue(l.contains(groceryItems[0]));
    }

    @Test
    public void testDoesntContainAfterRemove() {

        // Starting with an empty list, we don't contain an item
        // before we add it.
        assertFalse(l.contains("Eggs"));

        // After adding a single item, the size is 1
        l.add("Eggs");
        assertTrue(l.contains(groceryItems[0]));

        // Add a second item and check that size is 2 and we contain it
        l.add(groceryItems[1]);
        assertTrue(l.contains(groceryItems[1]));
        assertEquals(2, l.size());

        // Remove the first item and make sure size decreases to 1
        l.remove(groceryItems[0]);
        assertEquals(2, l.size());
        assertFalse(l.contains(groceryItems[0]));
    }

    @Ignore
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

    @Test
    public void testSize(){
        assertEquals(0, l.size());
    }

    @Test
    public void test_isEmpty(){
        assertTrue(l.isEmpty());
    }

}

