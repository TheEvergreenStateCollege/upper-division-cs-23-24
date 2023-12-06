def make_change_min_coins(coins, amount, memo={}):
    coins.sort(reverse=True)  # Sort coins in descending order
    if amount == 0:
        return 0
    if amount < 0 or not coins:
        return float('inf')
    if (len(coins), amount) in memo:
        return memo[(len(coins), amount)]

    include_last_coin = 1 + make_change_min_coins(coins, amount - coins[0], memo)  # Use coins[0] as it is now the largest coin
    exclude_last_coin = make_change_min_coins(coins[1:], amount, memo)

    memo[(len(coins), amount)] = min(include_last_coin, exclude_last_coin)
    print(memo)

    return memo[(len(coins), amount)]

# Example usage
coins = [25, 1, 10, 5]
amount = 73
min_coins = make_change_min_coins(coins, amount)
print(f"Minimum number of coins to make {amount} cents with coins: {min_coins}")