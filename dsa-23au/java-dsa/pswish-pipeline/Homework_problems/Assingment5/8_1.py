# Cracking the code, from Assignment 5 due Nov 1st
# 8.1
# Tripple step: A child is running up a staircase with n steps and can hop either 1 or 2 steps or 3 steps at a time.
# Implement a method to count how many possible ways the child can run up the staircase

def the_ways(n):
    if n == 0 or n == 1:
        return 1
    elif n == 2:
        return 2
    else:
        return the_ways(n - 1) + the_ways(n - 2) + the_ways(n - 3)


n = 10
ways = the_ways(n)
print(f"The child can climb the staircase in {ways} ways.")
