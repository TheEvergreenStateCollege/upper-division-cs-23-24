package dev.codewithfriends;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    public static int coinChangeRecursive(List<Integer> denoms, int amount, Map<Integer,Integer> memo) {
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


        // Recursive case
        int includeCoin = coinChangeRecursive(denoms, amount - denoms.get(0), memo);
        List<Integer> denomsExclude = Collections.unmodifiableList(denoms.subList(1, denoms.size()));
        int excludeCoin = coinChangeRecursive(denomsExclude, amount, memo);

        memo.put(amount, includeCoin + excludeCoin);

        return includeCoin + excludeCoin;
    }

    public static void coinChangeIterative() {

    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
