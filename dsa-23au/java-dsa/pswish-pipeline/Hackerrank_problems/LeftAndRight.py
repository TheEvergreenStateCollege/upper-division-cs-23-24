# https://www.hackerrank.com/challenges/linkedin-practice-array-left-rotation/problem

# First, define a function that takes an array input to rotate and number of rotations
# return an array[d:] + an array[:d] array recursion 
# define d
# defne an array 
# call the function = funciton(array[1])
# print the unpacked results

def left_rotate_array(array, d):
    return array[d:] + array[:d]

d = [5, 4]
array = [1, 2, 3, 4, 5]
result = left_rotate_array(array, d[1])
print(*result)
