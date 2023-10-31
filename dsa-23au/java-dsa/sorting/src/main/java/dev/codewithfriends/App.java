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
        int[] newArray = new int[end - start + 1]; 
        int length = end - start + 1;
       
        if (end - start > 1){
            int leftEnd = start + length / 2;
            int rightStart = leftEnd + 1;
            mergeSortHelper(arr, start, leftEnd);       // left half
            mergeSortHelper(arr, rightStart, end);     // right half
            
            for (int i = start; i < rightStart; i++){
                newArray[i - start] = arr[i];
            }

            for (int i = rightStart; i <= end; i++){
                for(int j = 0; j < newArray.length; j++){
                    if (newArray[j] > arr[i]){
                        for (int k = arr.length - 2; k >= j; k--){
                            newArray[k + 1] = k;
                        }
                        newArray[j] = arr[i];
                    }
                }
            }
        }
        else{ //Base case
            //Check if arr requested part of the array is size 1 
            if (start == end){
                return arr;
            }
            int temp = arr[start];
            if (arr[start] <= arr[end]){
                arr[start] = arr[end];
                arr[end] = temp;
            }
        }
        //Split into left and right
        //Recursively call mergeSortHelper on left and right halves

        return arr;
    } 
}
