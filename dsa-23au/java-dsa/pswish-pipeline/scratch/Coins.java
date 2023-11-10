package main.java.com.pswishcorp.app;

import java.util.HashMap;
import java.util.Map;

public class MakeChangeMinCoins {

    private static Map<Integer, Integer> memo = new HashMap<>();

    public static int makeChangeMinCoins(int[] coins, int amount) {
        // Sort coins in descending order
        Arrays.sort(coins);

        // If amount is 0, return 0
        if (amount == 0) {
            return 0;
        }

        // If amount is less than 0 or there are no coins, return infinity
        if (amount < 0 || coins.length == 0) {
            return Integer.MAX_VALUE;
        }

        // Check if the current subproblem has already been solved
        if (memo.containsKey(amount)) {
            return memo.get(amount);
        }

        // Calculate the minimum number of coins to make the amount, including the last coin
        int includeLastCoin = 1 + makeChangeMinCoins(coins, amount - coins[0]);

        // Calculate the minimum number of coins to make the amount, excluding the last coin
        int excludeLastCoin = makeChangeMinCoins(coins, amount, coins[1]);

        // Store the minimum number of coins in the memo dictionary
        memo.put(amount, Math.min(includeLastCoin, excludeLastCoin));

        // Return the minimum number of coins
        return memo.get(amount);
    }

    public static void main(String[] args) {
        int[] coins = {25, 1, 10, 5};
        int amount = 73;

        int minCoins = makeChangeMinCoins(coins, amount);

        System.out.println("Minimum number of coins to make " + amount + " cents with coins: " + minCoins);
    }
}