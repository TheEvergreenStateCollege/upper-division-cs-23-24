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
    /**
     * 
     * @param arr
     * @param start - beginning / leftmost - index, inclusive, to start mergeSorting
     * @param end - ending / rightmost index, inclusive, to start mergeSorting
     * @return
     */
    public static int[] mergeSortHelper(int[] arr, int start, int end){
        int[] newArray = new int[end - start + 1]; 
        int length = end - start + 1;
            if(arr == null){
                 return arr;
            }
            if(arr.length == 1){
                return arr;
            }

        mergeSortHelper(arr, start, start + (length / 2));       // left half
        mergeSortHelper(arr, start + (length / 2), end + 1);     // right half
        //Split into left and right
        //Recursively call mergeSortHelper on left and right halves
        int leftHalfPointer = start;
        int rightHalfPointer = start + (length / 2);
        for(int i = 0; leftHalfPointer < rightHalfPointer; i++){
            if(arr[leftHalfPointer] <= arr[rightHalfPointer]){
                arr[i] = arr[leftHalfPointer];
            }
            else{
                arr[i] = arr[rightHalfPointer];
            }
        }

        return arr;
    } 
}
