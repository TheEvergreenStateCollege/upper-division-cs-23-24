package com.pswishcorp.app;

import org.junit.Test;
import static org.junit.Assert.*;

public class MyArrayListTest {

    @Test
    public void testSize() {
        MyArrayList<Integer> list = new MyArrayList<>();
        assertEquals(0, list.size());
        
        list.add(1);
        assertEquals(1, list.size());
        
        list.add(2);
        assertEquals(2, list.size());
    }

    @Test
    public void testIsEmpty() {
        MyArrayList<String> list = new MyArrayList<>();
        assertTrue(list.isEmpty());
        
        list.add("Item");
        assertFalse(list.isEmpty());
    }

    @Test
    public void testAddAndGet() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("First");
        list.add("Second");
        
        assertEquals("First", list.get(0));
        assertEquals("Second", list.get(1));
    }

    @Test
    public void testSet() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Item");
        
        String oldItem = list.set(0, "NewItem");
        assertEquals("Item", oldItem);
        assertEquals("NewItem", list.get(0));
    }

    @Test
    public void testRemove() {
        MyArrayList<Integer> list1 = new MyArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        
        int removedItem = list1.remove(1);
        assertEquals(2, removedItem);
        assertEquals(2, list1.size());
        
        // Need a test for list.get()
    }

    @Test
    public void testIterator() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        java.util.Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("C", iterator.next());
        assertFalse(iterator.hasNext());
    }
}
