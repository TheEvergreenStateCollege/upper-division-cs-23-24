package dev.codewithfriends;

import java.lang.Integer;
import static org.junit.Assert.assertTrue;
import org.junit.Before;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class BinarySearchTreeTest 
{

    BinarySearchTree<Integer> bst;

    @Before
    public void setUp() {
        bst = new BinarySearchTree<Integer>();
        bst.insert(6);
        bst.insert(2);
        bst.insert(8);
        bst.insert(1);
        bst.insert(3);
        bst.insert(4);
    }

    @Test
    public void testInsertAndContains()
    {
    }
}
