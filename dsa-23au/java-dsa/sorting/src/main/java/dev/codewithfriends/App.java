package dev.codewithfriends;

/**
 * Hello world!
 *
 */
public class App 
{
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
    public static int[] mergeSort(int[] array){
        return mergeSortHelper(array, 0, array.length - 1);

    }

    public static int[] mergeSortHelper(int[] arr, int start, int end){
        
        // int[] newArray = new int[end - start + 1]; 
        int middle = (start + end) /2;        

        if (start == end) { // base case
            return arr;                // check if the requested part of the array is size 1
        } else { // inductive case
            mergeSortHelper(arr, start, start + middle);       // left half
            mergeSortHelper(arr, start + middle + 1, end);     // right half
            merge(arr, start, middle, end); 
        }
        
        return arr;
    } 

    public static int[] merge(int[] arr, int left, int middle, int right){
        
        int[] newArray = new int[end right - left +1];
        

        for(int i = 0; i < leftArray.length; i++){
            leftArray[i] = 
        }
        for(int i = 0; i < rightArray.length; i++){

        }


        return arr;
    } 
}
