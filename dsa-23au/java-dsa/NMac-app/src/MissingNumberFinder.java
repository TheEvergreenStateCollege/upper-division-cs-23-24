// Chpt 5, pg 167, 5-2

// A sorted array of size n contains distinct integers between 1 and n + 1,
// with one element missing.
// Give an O(log n) algorithm to find the missing integer,
// without using any extra spaces

// Consider this Binary search method for finding the missing number
// Given the array is sorted with distinct integers ranging from 1 to n+1,
// the missing element will create a gap where the value at an index is not equal
// to the index plus one (considering a 1-based array).

// Better explanation: We have a findMissingNumber method that performs a binary search
// to find the missing number in a sorted array of distinct integers.
// If the missing number is not within the array (i.e., it's n + 1), the loop exits,
// and the last return statement handles this case by returning array.length + 1.
//
//This method runs in O(log n) time since it's using a binary search approach,
// and it does not require any extra space.
//

public class MissingNumberFinder {

    public static int findMissingNumber(int[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if the middle element is the start of the gap
            if (array[mid] != mid + 1 && (mid == 0 || array[mid - 1] == mid)) {
                return mid + 1;
            }

            // Decide which side to go
            if (array[mid] != mid + 1) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // If we haven't found the missing number, it must be n + 1
        return array.length + 1;
    }

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5, 6, 8};
        int missingNumber = findMissingNumber(array);
        System.out.println("The missing number is: " + missingNumber);
    }
}