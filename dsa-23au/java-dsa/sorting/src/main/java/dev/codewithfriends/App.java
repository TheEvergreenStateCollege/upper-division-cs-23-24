package dev.codewithfriends;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int[] array = {1,0,2,9,3,7,5,8};

        System.out.println(Arrays.toString(array));

        int[] newArray = mergeSort(array);
        
        System.out.println(Arrays.toString(newArray));
        
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
    
    
    public static int[] mergeSort(int[] array){
        return mergeSortHelper(array, 0, array.length);

    }

    public static int[] mergeSortHelper(int[] arr, int start, int end){

        //System.out.println(Arrays.toString(arr));

       
        int length = end - start;
        int mid = start + (length / 2);
        int[] newArray = new int[length]; 

        //if there is only one element

        if(length <= 1){
            newArray[0] = arr[start];
            return newArray;
        }

        //Split into left and right
        //Recursively call mergeSortHelper on left and right halves

        int[] left  = mergeSortHelper(arr, start, mid);       // left half
        int[] right = mergeSortHelper(arr, mid, end);     // right half
        
        int leftIndex = 0;
        int rightIndex = 0;

        for(int i = 0; i < length; i++){
            if ((leftIndex >= mid - start) || rightIndex >= end - mid || left[leftIndex] <= right[rightIndex]) {
                newArray[i] = left[leftIndex];
                leftIndex++;
                
            } else {
                newArray[i] = right[rightIndex];
                rightIndex++;
            }
        }

        left = null;
        right = null;

        return newArray;
    } 
}
