# Consider the numerical Twenty Quesiton game. In this game, the first player thinks of a number in the range 1 to n.
# The second player has to figure out this number by asking the fewest number of true/false questions. 
# # 1 what is an optimal strategy if n is known?
# 2 what is a good strategy if n is not known?

# def twenty_questions(n):  # we know what n is
#     low, high = 1, n

#     while low <= high:
#         mid = (low + high) // 2
#         response = input(f"Is the number > than or == to {mid}? (yes/no) ")

#         if response.lower() == 'yes':
#             low = mid + 1
#         else:
#             high = mid - 1

#     return low

# # Example usage:

# n = 100 
# result = twenty_questions(n)
# print(f"The guessed number is: {result}")


def twenty_question():
    low, high = 1, 1000  # Start with a reasonably large range

    while low <= high:
        mid = (low + high) // 2
        response = input(f"Is the number >= to {mid}? (yes/no) ")

        if response.lower() == 'yes':
            low = mid + 1
        else:
            high = mid - 1

    # When the while loop ends, 'low' will be the correct number
    return low

# Example usage:
result = twenty_question()
print(f"The guessed number is: {result}")
