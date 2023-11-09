package dev.codewithfriends;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    
    public static class Pair 
    {

        int denomsUsed;
        int amountRemaining;

        public Pair(int denomsUsed, int amountRemaining) 
        {
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
    public static int coinChangeRecursive(List<Integer> denoms, int amount, Map<{Pair,Integer> memo) {
       Pair key = new Pair(denmos.size(), amount);
      
       Collections.sort(denoms);
        // Base case

        if (amount < 0 || denoms.size() == 0) {
            // If it's impossible to make the change
            // because we have no denominations or it's negative
            return 0;
        }
        if (amount == 0) {
            return 1;
        }

        // If we cached the result previously, return it
        if (memo.containsKey(amount)) {
            return memo.get(amount);
        }

        int lastIndex = denoms.size() - 1;
        int lastItem = denoms.get(lastIndex);

        // Recursive case
        int includeCoin = 1 + coinChangeRecursive(denoms, amount - lastItem, memo);
        List<Integer> denomsExclude = Collections.unmodifiableList(denoms.subList(1, denoms.size()));
        int excludeCoin = coinChangeRecursive(denomsExclude, amount, memo);

        memo.put(key, includeCoin + excludeCoin);

        return includeCoin + excludeCoin;
    }

    public static void coinChangeIterative() {

    }

    public static void main( String[] args )
    {
        List<Integer> coins = new ArrayList<>();
        coins.add(25);
        coins.add(1);
        coins.add(10);
        coins.add(5);

        int amount = 73;
        Map<Integer, Integer> memo = new HashMap<>();

        int minCoins = coinChangeRecursive(coins, amount, memo);

    }
}
