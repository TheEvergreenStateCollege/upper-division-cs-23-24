package dev.codewithfriends;

import java.util.HashSet;
import java.util.Set;
import java.util.Random;

/**
 * Rabin-Miller compositeness test as described in
 *
 */
public class RabinMiller {

    enum NumberType {
        PSEUDO_PRIME,
        COMPOSITE
    }

    public static class Decomposition {
        int d;
        int s;
        NumberType result;

        private Decomposition(int d, int s, NumberType result) {
            this.d = d;
            this.s = s;
            this.result = result;
        }

        public static Decomposition createComposite(int d, int s) {
            return new Decomposition(d, s, NumberType.COMPOSITE);
        }

        public static Decomposition createPrime() {
            return new Decomposition(0, 0, NumberType.PSEUDO_PRIME);
        }
    }

    public static int fastModExp(int b, int e, int n) {
        int result = b;
        while (e > 0) {
            if (e % 2 == 0) {
                result = (result * result) % n;
            } else {
                result = (result * b) % n;
            }
            e = e >> 1;
        }
        return result;
    }

    public static Decomposition decompose(int n) {
        int s = 0;
        while (n % 2 != 1) {
            s += 1;
            n /= 2;
        }
        return Decomposition.createComposite(n, s);
    }

    /**
     * This applies the Rabin-Miller compositeness test
     * @param n the number we are testing for probable compositeness
     * @return false if definitely composite (we found a base )
     * otherwise true, if probably prime (after trying numBases bases, we couldn't find one that
     */
    public static boolean isProbablyPrime(int n, int numBases) {
        Set<Integer> bases = new HashSet<>();
        Decomposition decomp = decompose(n);

        // Go through the requested number of bases
        for (int i = 0; i < numBases; i += 1) {
            Random rand = new Random();
            int base = rand.nextInt(n-2);
            // First check that a^d = 1 \mod n
            if (fastModExp(base, decomp.d, n) == 1) {
                return true;
            }
            for (int j = 1; j < decomp.s; j += 1) {
                if (fastModExp(base, decomp.d << decomp.s, n) == -1) {
                    return true;
                }
            }
            bases.add(base);
        }
        // We reach here if we are not a strong pseudoprime with respect to any
        // random bases
        return false;
    }

    public static int findPrimeGreaterThan(int n) {
        return 0;
    }
}
