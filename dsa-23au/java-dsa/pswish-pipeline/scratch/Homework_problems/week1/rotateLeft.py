def left_rotate_array(array, d):
    return array[d:] + array[:d]

# Read input
n, d = map(int, input().split())
array = list(map(int, input().split()))

#  Rotate array by d
result = left_rotate_array(array, d)

print(*result)

# submitted, score 30/30
# https://www.hackerrank.com/profile/pswish