package dev.codewithfriends.Sorting;

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
    public static void insertionSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // Move elements of arr[0..i-1] that are greater than key
            // to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
