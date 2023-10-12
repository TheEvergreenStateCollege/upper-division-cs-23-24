package dev.codewithfriends;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class SievePrimeTest
{

    @Test
    public void testGetN() {
        SievePrime sp = new SievePrime(24);
        assertEquals(24, sp.getN());
    }

    @Test
    public void testGetMaxBits() {
        SievePrime sp = new SievePrime(24);
        assertEquals(24, sp.getMaxBits());
        SievePrime sp0 = new SievePrime(0);
        assertEquals(0, sp0.getMaxBits());
    }

    @Test
    public void getRowCount() {
        SievePrime sp = new SievePrime(24);
        assertEquals(1, sp.getRowCount());
    }

    @Test
    public void testGetBit() {
        SievePrime sp = new SievePrime(24);
        for (int i = 0; i < 24; i += 1) {
            try {
                assertTrue(sp.getBit(i));
            } catch(Exception e) {
                System.out.println(e.toString() + "for getBit(" + i + ")");
            }
        }
    }

    @Test
    public void testClearBit() {
        SievePrime cp = new SievePrime(24);

        for (int i = 2; i < 23; i += 1) {
            assertTrue(cp.getBit(i));
            assertTrue(cp.clearBit(i));
            assertFalse(cp.getBit(i));
        }
    }

    @Test
    public void testLongToBitString() {
        String y = SievePrime.longToBitString(0L);
        assertEquals(80, y.length()); // 64 bits and 16 spaces
        assertEquals(" 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000",
                y);
        long x = 0xABCDEF0123456789L;
        assertEquals(" 1010 1011 1100 1101 1110 1111 0000 0001 0010 0011 0100 0101 0110 0111 1000 1001",
                SievePrime.longToBitString(x));
    }

    @Test
    public void testIntRange() {
        IntStream.rangeClosed(23, 1).parallel();
        // "You can supply IntStream.rangeClosed with end less than start.");
    }

    @Test
    public void testGetPrimesLessThan() {
        List<Long> primes = SievePrime.getPrimesLessThan(24);
        // 2, 3, 5, 7, 11, 13, 17, 19, 23  -> 9 numbers
        assertEquals(9, primes.size());
        assertEquals(Long.valueOf(23), (Long)primes.get(8));
    }

    @Test
    public void testMaxIntGetPrimesLessThan() {
        List<Long> primes = SievePrime.getPrimesLessThan(Integer.MAX_VALUE / 1000000);
        assertEquals(324, primes.size());
        assertEquals(Long.valueOf(2143), (Long)primes.get(323));

        List<Long> primes2 = SievePrime.getPrimesLessThan(Integer.MAX_VALUE / 10000);
        assertEquals(19192, primes2.size());
        assertEquals(Long.valueOf(214741), (Long)primes2.get(19191));

        List<Long> primes3 = SievePrime.getPrimesLessThan(Integer.MAX_VALUE / 100);
        assertEquals(1358124, primes3.size());
        assertEquals(Long.valueOf(21474829), (Long)primes3.get(1358123));
    }

    @Test
    public void testGetIntWidth() {
        assertEquals(32, SievePrime.getIntWidth());
    }

    @Test
    public void testGetLongWidth() {
        assertEquals(64, SievePrime.getLongWidth());
    }

}
