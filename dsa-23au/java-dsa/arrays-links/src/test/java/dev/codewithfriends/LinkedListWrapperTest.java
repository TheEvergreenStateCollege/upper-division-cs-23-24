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

*/