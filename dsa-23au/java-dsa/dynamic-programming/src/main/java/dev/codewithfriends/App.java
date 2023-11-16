package dev.codewithfriends;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */

public class App 
{
    public static class Pair {

        int denomsUsed;
        int amountRemaining;
    
        public Pair(int denomsUsed, int amountRemaining) {
            this.denomsUsed = denomsUsed;
            this.amountRemaining = amountRemaining;
        }
    }
    /**
     * Recursive approach to making change from coins,
     * @param denoms list of coin denominations as integers, sort in ascending order
     * @param amount
     * @return The number of ways to make change for the given amount
     */
    public static int coinChangeRecursive(List<Integer> denoms, int amount, Map<Pair,Integer> memo) {
        Pair key = new Pair(denoms.size(), amount);
        // Base case
        List<Integer> denoms2 = new ArrayList<Integer>(denoms);
        Collections.sort(denoms2);
        Collections.reverse(denoms2);
        denoms = denoms2;
        if (amount < 0 || denoms.size() == 0) {
            // If it's impossible to make the change
            // because we have no denominations or it's negative
            return Integer.MAX_VALUE-1;
        }
        if (amount == 0) {
            return 0;
        }

        // If we cached the result previously, return it
        if (memo.containsKey(key)) {
            return memo.get(key);
        }


        // Recursive case
        int includeCoin = 1 + coinChangeRecursive(denoms, amount - denoms.get(0), memo);
        List<Integer> denomsExclude = Collections.unmodifiableList(denoms.subList(1, denoms.size()));
        int excludeCoin = coinChangeRecursive(denomsExclude, amount, memo);
                int newWays = Math.min(includeCoin, excludeCoin);
        memo.put(new Pair(denoms.size(), amount), newWays);

        return newWays;
    }

    public static void coinChangeIterative() {

    }

    public static void main( String[] args )
    {

    }
}
