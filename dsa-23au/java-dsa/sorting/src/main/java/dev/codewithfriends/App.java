package dev.codewithfriends;

/**
 * Hello world!
 *
 */
public class App 
{
    /**
     * @param args
     */
    public static void main( String[] args )
    {
 
        
    }
    public static int[] insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

        return arr;
    }
    public static int[] mergeSort(int[] array) {
        mergeSortHelper(array, 0, array.length - 1);
        return array; // Return the sorted array
    }
    
    public static void mergeSortHelper(int[] arr, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2; // Calculate the midpoint of the subarray
    
            // Recursively sort the left and right halves
            mergeSortHelper(arr, start, mid); // Sort the left half
            mergeSortHelper(arr, mid + 1, end); // Sort the right half
    
            // Merge the two sorted halves
            merge(arr, start, mid, end);
        }
        else {
            return;
        }
    }
    
    public static void merge(int[] arr, int start, int mid, int end) {
        int leftLength = mid - start + 1;
        int rightLength = end - mid;
    
        int[] leftArray = new int[leftLength];
        int[] rightArray = new int[rightLength];
    
        // Copy data to temporary left and right arrays
        for (int i = 0; i < leftLength; i++) {
            leftArray[i] = arr[start + i];
        }
        for (int i = 0; i < rightLength; i++) {
            rightArray[i] = arr[mid + 1 + i];
        }
    
        int i = 0, j = 0, k = start;
    
        // Merge the two halves by comparing elements and putting them in the correct order
        while (i < leftLength && j < rightLength) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }
    
        // Copy any remaining elements from left and right arrays
        while (i < leftLength) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }
    
        while (j < rightLength) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
    