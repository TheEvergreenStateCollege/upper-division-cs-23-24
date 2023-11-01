//merge sort
dev.codewithfriends;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BSTMain {

    public static void main(String[] args) {
      
        List<String> namesList = Arrays.asList("John", "Jane", "Bob", "Alice", "Zara", "Charlie");
        String[] sortedNames = MergeSort.mergeSort(namesList.toArray(new String[0]));
        System.out.println("Sorted Names: " + Arrays.toString(sortedNames));
    }
}

class MergeSort {

    public static String[] mergeSort(String[] array) {
        int n = array.length;
        if (n <= 1) {
            return array;
        }

        int mid = n / 2;
        String[] left = new String[mid];
        String[] right = new String[n - mid];

        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, n - mid);

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private static String[] merge(String[] left, String[] right) {
        int leftSize = left.length;
        int rightSize = right.length;
        String[] merged = new String[leftSize + rightSize];

        int leftPointer = 0;
        int rightPointer = 0;
        int mergedPointer = 0;

        while (leftPointer < leftSize && rightPointer < rightSize) {
            if (left[leftPointer].compareTo(right[rightPointer]) <= 0) {
                merged[mergedPointer++] = left[leftPointer++];
            } else {
                merged[mergedPointer++] = right[rightPointer++];
            }
        }

        while (leftPointer < leftSize) {
            merged[mergedPointer++] = left[leftPointer++];
        }

        while (rightPointer < rightSize) {
            merged[mergedPointer++] = right[rightPointer++];
        }

        return merged;
    }
}

