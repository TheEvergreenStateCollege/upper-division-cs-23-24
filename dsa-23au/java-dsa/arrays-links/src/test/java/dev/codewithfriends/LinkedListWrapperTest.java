package dev.codewithfriends;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.List;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LinkedListWrapperTest {

    Book b1 = new Book("Some title", "some author");

    public void testAdd()
    {
    String[] groceryItems = {"Apples", "Bananas","Carrots","Grapes"};

    LinkedList<String> groceries = new LinkedList<>();
    for(String item : groceryItems)
    {
        groceries.add(item);
    }
   
    assertEquals(0, groceries.get(0));
    assertEquals(1, groceries.get(1));
    assertEquals(2, groceries.get(2));
    assertEquals(3, groceries.get(3));

    groceries.add("Candy");

    assertEquals(0, groceries.get(0));
    assertEquals(1, groceries.get(1));
    assertEquals(2, groceries.get(2));
    assertEquals(3, groceries.get(3));
    assertEquals(4, groceries.get(4));

    }

    // Create / Insert operation
    // List Spec
    @Test
    public void insertShifts()
    {
        List l = new ArrayWrapper<Book>(10);
        assertEquals(0, l.size());
        l.add(b1);
        assertEquals(1, l.size());
    }



}

