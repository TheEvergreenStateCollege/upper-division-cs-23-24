package dev.codewithfriends;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;




public class App 
{

    /**
     * Recursive approach to making change from coins,
     * @param denoms list of coin denominations as integers, sort in ascending order
     * @param amount
     * @return The number of ways to make change for the given amount
     */
    public static int coinChangeRecursive(List<Integer> denoms, int amount, Map<Integer,Integer> memo) {
        Collections.sort(denoms);
        
           
        // Base case
        if (amount < 0) {
            return 0;
        }
        if (denoms.size() == 0) {
            return 0;
        }
        if (amount == 0) {
            return 1;
        }

        // Memoization check
        if (memo.containsKey(amount)) {
            return memo.get(amount);
        }

        // Recursive case
        int includeCoin = coinChangeRecursive(denoms, amount - denoms.get(0), memo);
        List<Integer> denomsExclude = Collections.unmodifiableList(denoms.subList(1, denoms.size()));
        int excludeCoin = coinChangeRecursive(denomsExclude, amount, memo);

        memo.put(amount, Math.min(includeCoin , excludeCoin));

        return memo.get(amount);
    }

    public static void coinChangeIterative() {

    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" + );
    

    public static class Pair {

        int denomsUsed;
        int amountRemaining;

        public Pair(int denomsUsed, int amountRemaining) {
            this.denomsUsed = denomsUsed;
            this.amountRemaining = amountRemaining;
        }
    }
}
