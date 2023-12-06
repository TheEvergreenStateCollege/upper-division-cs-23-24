# 5-1: Suppose you are given a sorted list1ay A of size n that has been circularly shifted k positions to the right.
# For example:, [35, 42, 5, 15, 27, 29] is a sorted list1ay that has been circularly shifted k = 2 positions, while
# [27, 29, 35, 42, 5, 15] has been shifted k = 4 positions.
    # 1. Suppose you know what k is. Give an o(1) algorithim to find the largest number in A.
    # 2. Suppose you don't know what k is. Give an o(logn) algorithim to find the largest number in A. 

# this works for #1
list = [35, 42, 5, 15, 27, 29]
print(sorted(list)[-1])

# # but for both #1 and #2 we need a binary search algorithm

def find_largest_in_circular_array(list2):
    left, right = 0, len(list2) - 1

    while left < right:
        mid = (left + right) // 2

        if list2[mid] > list2[right]:  # Compare the middle element with the elements right
            right = mid  # The largest element is on the left side of mid
        elif list2[mid] < list2[right]:  # Compare the middle element with the elements left
            left = mid + 1  # The largest element is on the right side of mid
        else:
            right -= 1  # Handle the case where list2[mid] == list2[right]
    
    return list2[left]  # left and right converge

# Example usage:
list2 = [29, 35, 42, 5, 15, 22]
largest = find_largest_in_circular_array(list2)
print(largest)
