package com.juniperGMVAD.app;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.List;
import org.junit.Test;

import com.juniperGMVAD.app.BinaryTree.BinaryTree;

public class BinaryTreeTest {

    @Test
    public void testInsertAndContains() {
        BinaryTree<Integer> tree = new BinaryTree<>(Integer::compareTo);

        tree.insert(5);
        tree.insert(3);
        tree.insert(7);

        assertTrue(tree.contains(5));
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(7));
    }

    @Test
    public void testDelete() {
        BinaryTree<Integer> tree = new BinaryTree<>(Integer::compareTo);

        tree.insert(5);
        tree.insert(3);
        tree.insert(7);

        assertTrue(tree.contains(5));

        tree.delete(5);

        assertFalse(tree.contains(5));
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(7));
    }

    @Test
    public void testSearch() {
        BinaryTree<Integer> tree = new BinaryTree<>(Integer::compareTo);

        tree.insert(5);
        tree.insert(3);
        tree.insert(7);

        assertEquals(Integer.valueOf(5), tree.search(5));
        assertEquals(Integer.valueOf(3), tree.search(3));
        assertEquals(Integer.valueOf(7), tree.search(7));
        assertNull(tree.search(10));
    }

    @Test
    public void testAsList() {
        BinaryTree<Integer> tree = new BinaryTree<>(Integer::compareTo);

        tree.insert(5);
        tree.insert(3);
        tree.insert(7);

        List<Integer> result = tree.asList();

        assertEquals(List.of(3, 5, 7), result);
    }

    @Test
    public void testSize() {
        BinaryTree<Integer> tree = new BinaryTree<>(Integer::compareTo);

        tree.insert(5);
        tree.insert(3);
        tree.insert(7);

        assertEquals(3, tree.size());

        tree.delete(5);

        assertEquals(2, tree.size());
    }
}
