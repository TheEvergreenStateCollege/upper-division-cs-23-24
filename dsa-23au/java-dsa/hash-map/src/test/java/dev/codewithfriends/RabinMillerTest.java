package dev.codewithfriends;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class RabinMillerTest
{
    @Test
    public void testFastModExp()
    {
    }

    @Test
    public void testDecompose()
    {
        RabinMiller.Decomposition decomp2 = RabinMiller.decompose(3072);
        assertEquals(3, decomp2.d);
        assertEquals(10, decomp2.s);

        RabinMiller.Decomposition decomp = RabinMiller.decompose(247);
        assertEquals(decomp.s, 0);
        assertEquals(decomp.d, 247);
    }
}
