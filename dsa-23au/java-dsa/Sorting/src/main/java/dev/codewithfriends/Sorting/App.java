package dev.codewithfriends.Sorting;

import java.util.Arrays;

/**
 * Hello world!
 *
 */

 // "Start at Index 1, extracts the element(red), keeps comparing with t
 //he previous element(green) in a loop, if red is less than green, green 
// is shifted to the right; the loop iterates again; until the red 
 //is more the green or we reach index 0" 


public class App 
{
 

    
        public static void main(String[] args) {
            int[] numbers = {12, 3, 21, 8, 1, 47, 50, 88, 3, 13};
            insertionSort(numbers);
            System.out.println("Sorted Numbers: " + Arrays.toString(numbers));
        }
    
        public static int[] insertionSort(int[] arr) {
            int n = arr.length;
            for (int i = 1; i < n; i++) {
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

        public static int[] mergeSort(int[] arr) {
            
            throw new RuntimeException();
        }

        public static int[] mergeSortHelper(int[] arr, int start, int end){

        }
    }
    