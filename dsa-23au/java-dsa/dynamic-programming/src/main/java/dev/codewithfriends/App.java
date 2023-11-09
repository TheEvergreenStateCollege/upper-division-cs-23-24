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
    public static class pair{
        int demonsUsed;

        int amountRemaining;

        public pair(int demonsUsed, int amountRemaining)
        {
            
        }


    }

    /**
     * Recursive approach to making change from coins,
     * @param demons list of coin denominations as integers, sort in ascending order
     * @param amount
     * @return The number of ways to make change for the given amount
     */
    public static int coinChangeRecursive(List<Integer> demons, int amount, Map<Integer,Integer> memo) {
       //sort in desending order
       
        // Base case

        if (amount < 0 || demons.size() == 0) {
            // If it's impossible to make the change
            // because we have no denominations or it's negative
            return 0;
        }

        // If we cached the result previously, return it
        if (memo.containsKey(amount)) {
            return memo.get(amount);
        }


        // Recursive case
        int includeCoin = coinChangeRecursive(demons, amount - demons.get(0), memo);
        List<Integer> demonsExclude = Collections.unmodifiableList(demons.subList(1, demons.size()));
        int excludeCoin = coinChangeRecursive(demonsExclude, amount, memo);

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
