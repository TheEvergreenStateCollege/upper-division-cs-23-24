# Cracking the code, from Assignment 5 due Nov 1st
# 8.5 Recursive Multiply: Write a recursive function to multiply two positive integers without using the * operator (or / operater). you can use addition, subtraction, and bit shifting, but you should minimize the numeber of such operations.
# bit shifting can get the job done.

def recursive_multiply(a, b):
    # Base case: If either a or b is 0, the result is 0
    if a == 0 or b == 0:
        return 0
    
    # Recursive case:
    # If b is even, recursively multiply a by b >> 1 (right shift by 1) and left shift the result by 1
    if b & 1 == 0:
        return (recursive_multiply(a, b >> 1)) << 1
    
    # If b is odd, recursively multiply a by (b-1) >> 1, left shift the result by 1, and add a
    return ((recursive_multiply(a, (b - 1) >> 1)) << 1) + a

# Example usage:
result = recursive_multiply(5, 3)
print(f"The result is: {result}")
