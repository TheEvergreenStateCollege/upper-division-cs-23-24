package dev.codewithfriends;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;

/**
 * Hello world!
 *
 */
public class App 
{

    /**
     * Recursive approach to making change from coins,
     * @param denoms list of coin denominations as integers, sort in ascending order
     * @param amount
     * @return The number of ways to make change for the given amount
     */

    public static class Pair {
        int coinsUsed;
        int amountRemaining;

        public Pair(int coinsUsed, int amountRemaining){
            //constructor for pair object
            this.coinsUsed = coinsUsed;
            this.amountRemaining = amountRemaining;
        }
    }

    public static int coinChangeRecursive(List<Integer> denoms, int amount, Map<Pair,Integer> memo) {
        // Base case
        Pair key = new Pair(denoms.size(), amount);

        if (amount < 0 || denoms.size() == 0) {
            // If it's impossible to make the change because we have no denominations or it's negative
            return 0;
        }
        if (amount == 0) {
            return 1;
        }

        // If we cached the result previously, return it
        if (memo.containsKey(amount)) {
            return memo.get(amount);
        }


        // Recursive case
        int includeCoin = 1 + coinChangeRecursive(denoms, amount - denoms.get(0), memo);
                // call to recursive function, change argument from amount to amount minus value at index 0
                
        List<Integer> denomsExclude = Collections.unmodifiableList(denoms.subList(1, denoms.size()));

        int excludeCoin = coinChangeRecursive(denomsExclude, amount, memo);

        int newWays = Math.min(includeCoin, excludeCoin);



        memo.put(key, newWays);

        return newWays;
    }

    public static void coinChangeIterative() {

    }

    public static void main( String[] args )
    {
        List<Integer> coins = new LinkedList<Integer>();
        coins.add(25);
        coins.add(10);
        coins.add(5);
        coins.add(1);

        System.out.println(coins);



    }
}
