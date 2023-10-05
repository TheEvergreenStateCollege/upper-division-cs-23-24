
/**package dev.codewithfriends;

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
   
    assertEquals("Apples", groceries.get(0));
    assertEquals("Bananas", groceries.get(1));
    assertEquals("Carrots", groceries.get(2));
    assertEquals("Grapes", groceries.get(3));

    groceries.add(4, "Candy");

    assertEquals("Apples", groceries.get(0));
    assertEquals("Bananas", groceries.get(1));
    assertEquals("Carrots", groceries.get(2));
    assertEquals("Grapes", groceries.get(3));
    assertEquals("Candy", groceries.get(4));

    }

    public void testLinkedList() {
        
    }

    public void testAdd() {
        // create an array of 4 grocery items as Strings
        // create a LinkedList
        // add them one by one
        // use assertEquals to check they're added in correct order
        // insert a 5th grocery item (as a string)
        // check the order again
    }



}

*/
