# 4.28
# Consider the following modification to merge sort: Divide the input array tinto thirds (rather than halves), recursively sort each third, and fianlly combine the results using a three-way merge subroutine. What is the worst-case running time of this modification?
# Worst case for this is O(nlog3(n) 


# TODO the logic needs refactoring, this is not very logical to use vs sorted()
def three_way_merge_sort(array):
    if len(array) > 1:
        n = len(array)
        mid1 = n // 3
        mid2 = 2 * n // 3

        # Recursively sort each third
        left = array[:mid1]
        middle = array[mid1:mid2]
        right = array[mid2:]

        three_way_merge_sort(left)
        three_way_merge_sort(middle)
        three_way_merge_sort(right)

        # Combine the results using three-way merge
        three_way_merge(array, left, middle, right)


def three_way_merge(array, left, middle, right):
    i = j = k = index = 0

    while i < len(left) and j < len(middle) and k < len(right):
        if left[i] <= middle[j] and left[i] <= right[k]:
            array[index] = left[i]
            i += 1
        elif middle[j] <= left[i] and middle[j] <= right[k]:
            array[index] = middle[j]
            j += 1
        else:
            array[index] = right[k]
            k += 1
        index += 1

    # Copy the remaining elements, if any
    while i < len(left):
        array[index] = left[i]
        i += 1
        index += 1

    while j < len(middle):
        array[index] = middle[j]
        j += 1
        index += 1

    while k < len(right):
        array[index] = right[k]
        k += 1
        index += 1


example_array = [12, 2, 3, 24, 31, 17, 9]
print("Original Array:", example_array)

three_way_merge_sort(example_array)

print("Sorted Array:", example_array)
print(sorted(example_array))
