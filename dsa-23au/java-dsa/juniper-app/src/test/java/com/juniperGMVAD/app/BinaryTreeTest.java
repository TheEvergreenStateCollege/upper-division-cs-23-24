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
=======
    @Test
    public void rangeAsList() {
        BinaryTree<YearValue> bt = new BinaryTree<YearValue>(new SortByYear());

        bt.insert(new YearValue(2000, 1.1));
        bt.insert(new YearValue(2001, 2.1));
        bt.insert(new YearValue(2004, 3.1));
        bt.insert(new YearValue(2007, 4.1));
        bt.insert(new YearValue(2008, 5.1));
        bt.insert(new YearValue(2009, 6.1));
        bt.insert(new YearValue(2010, 7.1));
        bt.insert(new YearValue(2011, 8.1));
        bt.insert(new YearValue(2016, 9.1));
        bt.insert(new YearValue(2020, 10.1));
        bt.insert(new YearValue(2042, 11.1));

        List<YearValue> testList = bt.rangeAsList(new YearValue(2002, 0d), new YearValue(2011, 0d));
        
        assertTrue(testList.get(0).year == 2004);
        assertTrue(testList.get(1).year == 2007);
        assertTrue(testList.get(2).year == 2008);
        assertTrue(testList.get(3).year == 2009);
        assertTrue(testList.get(4).year == 2010);
        assertTrue(testList.get(5).year == 2011);
    }
}   

