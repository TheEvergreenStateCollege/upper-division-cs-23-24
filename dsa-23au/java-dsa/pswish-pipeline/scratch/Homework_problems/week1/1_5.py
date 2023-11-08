# Redbook problem 1-5. 
# The knapsack problem is as follows: given a set of integers S = {s1, s2, ...,sn},
# and a target number T, find a subset of S that adds up exactly to T. For ex-
# ample, there exists a subset within S = {1, 2, 5, 9, 10} that adds up to T = 22
# but not T = 23.
# Find counterexamples to each of the following algorithms for the knapsack problem. 
#       That is, give an S and T where the algorithm does not find a solution that leaves 
#       the knapsack completely full, even though a full-knapsack solution exists.
# 	1. Put the elements of S in the knapsack in left to right order if they fit, that is, 
#       the first-fit algorithm.
# 	2. Put the elements of S in the knapsack from smallest to largest, that is, the best-fit algorithm.
#   3. Put the elements of S in the knapsack from largest to smallest.

def first_fit(S, T):  #1
    knapsack = []
    for item in S:
        if sum(knapsack) + item <= T:  # 
            knapsack.append(item)
    return knapsack

def best_fit(S, T):  #2
    S.sort()
    knapsack = []
    for item in S:
        if sum(knapsack) + item <= T:
            knapsack.append(item)
    return knapsack

def reverse_fit(S, T):  #3
    S.sort(reverse=True)
    knapsack = []
    for item in S:
        if sum(knapsack) + item <= T:
            knapsack.append(item)
    return knapsack

S = [1, 2, 5, 9, 10]
T = 3
result = first_fit(S, T)
print("First-Fit Algorithm:", result)

result = best_fit(S, T)
print("Best-Fit Algorithm:", result)

result = reverse_fit(S, T)
print("Reverse-Fit Algorithm:", result)
