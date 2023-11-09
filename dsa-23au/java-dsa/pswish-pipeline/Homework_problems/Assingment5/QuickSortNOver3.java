// # 4.22
// # The median of a set of n values is the [n/th] smallest value.
// # #1 Suppose quicksort alwyas pivoted on the median of the current sub-array. How many comparisons woudl quicksort make then in the owrst case.
// # #2 Suppose quicksort always pivoted on the [n/3]th smallest value of the current sub-array. How many comparisons would be made then in the worst case.

// For a quicksort median n over 3
import java.util.Arrays;

public class QuickSortNOver3 {

    public static void main(String[] args) {
        int[] array = {12, 2, 3, 24, 31, 17, 9};

        System.out.println("Original Array: " + Arrays.toString(array));
        quicksortNOver3(array, 0, array.length - 1);
        System.out.println("Sorted Array: " + Arrays.toString(array));
    }

    public static void quicksortNOver3(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionNOver3(array, low, high);
            quicksortNOver3(array, low, pivotIndex - 1);
            quicksortNOver3(array, pivotIndex + 1, high);
        }
    }

    public static int partitionNOver3(int[] array, int low, int high) {
        // Choose the [n/3]th smallest value as the pivot
        int nOver3 = low + (high - low) / 3;
        int pivot = array[nOver3];

        // Move the pivot to the end
        swap(array, nOver3, high);

        // Partition the array
        int i = low;
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                swap(array, i, j);
                i++;
            }
        }

        // Move the pivot to its final position
        swap(array, i, high);

        return i;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

// the worst-case number of comparisons for quicksort always pivoting on the [n/3]th smallest value would still be O(n log n)