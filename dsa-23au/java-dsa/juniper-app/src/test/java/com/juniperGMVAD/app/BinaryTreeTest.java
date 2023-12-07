package com.juniperGMVAD.app;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.juniperGMVAD.app.BinaryTree.BinaryTree;
import com.juniperGMVAD.app.YearValue.SortByYear;
import com.juniperGMVAD.app.YearValue.YearValue;

public class BinaryTreeTest {
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
