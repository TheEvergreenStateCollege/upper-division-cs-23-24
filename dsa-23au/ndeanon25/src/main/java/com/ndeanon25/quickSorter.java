package com.ndeanon25;
import java.util.*;

public class quickSorter {
    public static void main(String[] args) {
        int[] unsortedArray = {25, 90, 12, 7, 100};
        quickSort(unsortedArray,0,unsortedArray.length- 1);
        System.out.println("The quick sorted array is " + Arrays.toString(unsortedArray));
    }
        public static void quickSort(int[] unsortedArray, int left, int right) {
            if (left < right) {
                int pivotIndex = partition(unsortedArray, left, right);

                quickSort(unsortedArray, left, pivotIndex - 1);
                quickSort(unsortedArray, pivotIndex + 1, right);
            }
        }

        public static int partition(int[] arr, int left, int right) {
            int pivot = arr[left];
            int i = left;
            int k = right;
            while (i < k) {
                while (i <= right && arr[i] <= pivot) {
                    i++;
                }

                while (arr[k] > pivot) {
                    k--;
                }

                if (i < k) {
                    int temp = arr[i];
                    arr[i] = arr[k];
                    arr[k] = temp;
                }
            }

            int temp2 = arr[left];
            arr[left] = arr[k];
            arr[k] = temp2;

            return k;
        }

}