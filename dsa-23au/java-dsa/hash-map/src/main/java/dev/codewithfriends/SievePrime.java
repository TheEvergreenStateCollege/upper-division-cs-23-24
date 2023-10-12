package dev.codewithfriends;

import java.util.List;
import java.util.LinkedList;
import java.util.stream.IntStream;

/**
 * A Java implementation of the Sieve of Eratosthenes, with utility methods.
 */
public class SievePrime {


    private long[] _array;

    public int getRowCount() {
        return rowCount;
    }

    public int getMaxBits() {
        return maxBits;
    }

    public int getN() {
        return n;
    }

    private int rowCount;
    private int maxBits;
    private int n;

    private final static int LONG_WIDTH = getLongWidth();
    private final static int INT_WIDTH = getIntWidth();

    public SievePrime(int n) {
        this.n = n;
        int rowCount = (int) Math.round(((n * 1.0) / LONG_WIDTH) + 1);
        _array = new long[rowCount];
        this.rowCount = rowCount;
        this.maxBits = n;
        for (int i = 0; i < rowCount; i += 1) {
            _array[i] = -1; // all F's
        }
    }

    public boolean extend(int newN) {
        if (newN <= this.n) {
            return false;
        }
        int rowCount = (int) Math.round(((newN * 1.0) / LONG_WIDTH) + 1);

        if (rowCount < this.rowCount) {
            return false;
        }
        long[] newArray = new long[rowCount];
        for (int i = 0; i < this.rowCount; i += 1) {
            newArray[i] = this._array[i];
        }

        this.rowCount = rowCount;
        this.maxBits = newN;

        return true;
    }

    public static int getIntWidth() {
        int x = -1;
        int d = 0;
        while (x != 0) {
            x = x << 1;
            d += 1;
        }
        return d;
    }

    public static int getLongWidth() {
        long x = -1;
        int d = 0;
        while (x != 0) {
            x = x << 1L;
            d += 1;
        }
        return d;
    }

    public boolean getBit(long index) {
        if (index >= this.maxBits) {
            return false;
        }
        int row = (int) Math.floor((index * 1.0) / LONG_WIDTH);
        int offset = (int)(index % getLongWidth());
        return (_array[row] & (1L << offset)) != 0;
    }

    public synchronized boolean setBit(long index) {
        if (index >= this.maxBits) {
            return false;
        }
        int row = (int) Math.floor(index * 1.0 / LONG_WIDTH);
        int offset = (int)(index % LONG_WIDTH);
        _array[row] |= (1L << offset);
        return true;
    }

    public synchronized boolean clearBit(long index) {
        if (index >= this.maxBits) {
            return false;
        }
        int row = (int) Math.floor(index * 1.0 / LONG_WIDTH);
        int offset = (int)(index % LONG_WIDTH);
        // Clearing index " + index + " row " + row + " " + LONG_WIDTH
        _array[row] &= ~(1L << offset);
        return true;
    }

    public static String longToBitString(long x) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < LONG_WIDTH) {
            sb.append(((x & 0x1) == 0x1) ? "1" : "0");
            i += 1;
            if (i % 4 == 0) {
                sb.append(" ");
            }
            x = x >> 1;
        }
        return sb.reverse().toString();
    }

    public List<Long> getNumbersLeft() {
        List<Long> result = new LinkedList<Long>();

        for (long i = 0; i < this.maxBits; i += 1) {
            if (this.getBit(i)) {
                result.add((long)i+1);
            }
        }

        return result;
    }

    public static List<Long> getPrimesLessThan(int n) {
        SievePrime sp = new SievePrime(n);
        sp.clearBit(0);

        // Prime numbers go from 2 to n-2
        for (int i = 1; i < n-3; i += 1) {
            int endSieveRange = (int) Math.round(n * 1.0 / (i+1));
            if (!sp.getBit(i)) {
                continue;
            }
            IntStream stream = IntStream.rangeClosed(i, endSieveRange).parallel();
            final int k = i+1;

            // Removing primes in multiples of i+1, doing a step of the sieve
            stream.forEach(j -> sp.clearBit(((j+1) * k)-1));
        }
        return sp.getNumbersLeft();
    }

    public static List<Long> getPrimesLessThan(int n, SievePrime oldSP) {

        // Attempt to extend the existing number sieve
        if (!oldSP.extend(n)) {
            return oldSP.getNumbersLeft();
        }
        // Prime numbers go from 2 to n-2
        for (int i = oldSP.getN() - 3; i < n-3; i += 1) {
            int endSieveRange = (int) Math.round(n * 1.0 / (i+1));
            if (!oldSP.getBit(i)) {
                continue;
            }
            IntStream stream = IntStream.rangeClosed(i, endSieveRange).parallel();
            final int k = i+1;

            // Removing primes in multiples of i+1, doing a step of the sieve
            stream.forEach(j -> oldSP.clearBit(((j+1) * k)-1));
        }
        return oldSP.getNumbersLeft();
    }

}
