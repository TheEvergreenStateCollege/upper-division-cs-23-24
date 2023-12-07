package dev.codewithfriends;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public abstract class ListWrapperFactory {

    private final static String[] groceryItems = {"Apples", "Bananas", "Carrots", "Grapes"};

    List<String> l;

    @Before
    public void setUp() {
        l = createList(10);
    }

    public abstract List<String> createList(int maxSize);

    @Test
    public void testToArray() {
        l.addAll(List.of(groceryItems));
        Object[] newArray = l.toArray();
        for (int i = 0; i < newArray.length; i++) {
            assertEquals(groceryItems[i], newArray[i]);
        }
    }

/*
    @Test
    public void testToArrayDest() {
        String[] newArray = new String[groceryItems.length];
        l.addAll(List.of(groceryItems));
        l.toArray(newArray);
        for (int i = 0; i < newArray.length; i++) {
            assertEquals(groceryItems[i], newArray[i]);
        }
    }
    */

    @Test
    public void testContainsAfterAdd() {
        assertFalse(l.contains(groceryItems[0]));
        l.add(groceryItems[0]);
        assertTrue(l.contains(groceryItems[0]));
    }

    @Test
    public void testIndexOf() {
        assertEquals(-1, l.indexOf(groceryItems[0]));
        l.add(groceryItems[0]);
        assertEquals(0, l.indexOf(groceryItems[0]));
    }
/*
    @Test
    public void testDoesntContainAfterRemove() {
        assertFalse(l.contains("Eggs"));
        l.add("Eggs");
        assertTrue(l.contains("Eggs"));
        l.add(groceryItems[1]);
        assertTrue(l.contains(groceryItems[1]));
        assertEquals(2, l.size());
        l.remove("Eggs");
        assertEquals(1, l.size());
        assertFalse(l.contains("Eggs"));
    }
*/

/*
    @Test
    public void testAddAndSize() {
        for (int i = 0; i < groceryItems.length; i++) {
            l.add(groceryItems[i]);
            assertEquals(groceryItems[i], l.get(i));
            assertEquals(i + 1, l.size());
        }
    }

    @Test
    public void testClear() {
        assertTrue(l.isEmpty());
        l.addAll(List.of(groceryItems));
        assertFalse(l.isEmpty());
        l.clear();
        assertTrue(l.isEmpty());
    }
*/
    @Test
    public void testSize() {
        assertEquals(0, l.size());
    }

    @Test
    public void test_isEmpty() {
        assertTrue(l.isEmpty());
    }
}


