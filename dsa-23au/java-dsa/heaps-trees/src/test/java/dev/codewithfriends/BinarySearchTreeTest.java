package dev.codewithfriends;

import static org.junit.Assert.assertTrue;

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
        bst.insert(BinaryNode<Integer>(6));
        bst.insert(BinaryNode<Integer>(2));
        bst.insert(BinaryNode<Integer>(8));
        bst.insert(BinaryNode<Integer>(1));
        bst.insert(BinaryNode<Integer>(3));
        bst.insert(BinaryNode<Integer>(4));
    }

    @Test
    public void testInsertAndContains()
    {
    }
}
