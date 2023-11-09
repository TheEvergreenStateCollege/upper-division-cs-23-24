// # 4.22
// # The median of a set of n values is the [n/th] smallest value.
// # #1 Suppose quicksort alwyas pivoted on the median of the current sub-array. How many comparisons woudl quicksort make then in the owrst case.
// # #2 Suppose quicksort always pivoted on the [n/3]th smallest value of the current sub-array. How many comparisons would be made then in the worst case.

import java.util.Arrays;

public class QuickSortMedian {

    public static void main(String[] args) {
        int[] array = {12, 2, 3, 24, 31, 17, 9};

        System.out.println("Original Array: " + Arrays.toString(array));
        quicksortMedian(array, 0, array.length - 1);
        System.out.println("Sorted Array: " + Arrays.toString(array));
    }

    public static void quicksortMedian(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionMedian(array, low, high);
            quicksortMedian(array, low, pivotIndex - 1);
            quicksortMedian(array, pivotIndex + 1, high);
        }
    }

    public static int partitionMedian(int[] array, int low, int high) {
        // Choose the median as the pivot
        int mid = low + (high - low) / 2;
        int pivot = array[mid];

        // Move the pivot to the end
        swap(array, mid, high);

        // Partition the array
        int i = low;
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                swap(array, i, j);
                i++;
            }
        }
        
        swap(array, i, high);  // Move the pivot to its final position

        return i;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
