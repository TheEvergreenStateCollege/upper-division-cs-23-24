package dev.codewithfriends;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void example()
    {
        List<Integer> denoms = new LinkedList<>();
        denoms.add(1);
        denoms.add(5);
        denoms.add(10);
        denoms.add(25);

        Map<Integer,Integer> memo = new HashMap<>();

        assertEquals( 1, App.coinChangeRecursive(denoms,73, memo));
    }
}
