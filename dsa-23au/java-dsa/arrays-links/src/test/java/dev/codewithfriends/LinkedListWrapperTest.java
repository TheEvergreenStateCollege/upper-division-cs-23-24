package dev.codewithfriends;
package dev.codewithfriends;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

public class LinkedListWrapperTest {

    Book b1 = new Book("Some title", "some author");

    // Create / Insert operation
    @Test
    public void insertShifts() {
        List<Book> l = new ArrayList<>(); // Use ArrayList<Book> for a list of Book objects
        assertEquals(0, l.size());
        l.add(b1);
        assertEquals(1, l.size());
    }
}
