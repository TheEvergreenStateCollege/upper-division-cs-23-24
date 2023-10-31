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
        int mid = (length / 2) - 1;

        int[] arr1 = mergeSortHelper(arr, start, mid);       // left half
        int[] arr2 = mergeSortHelper(arr, mid, end);     // right half
        //Split into left and right
        //Recursively call mergeSortHelper on left and right halves
        int j = 0; int k = 0;
        for (int i = 0; i < (arr1.length + arr2.length); i++) {
            if (j == arr1.length) {
                newArray[i] = arr2[k];
                k++;
                continue;
            }
            else if (k == arr2.length){
                newArray[i] = arr1[j];
                j++;
                continue;
                
            }
            if (arr1[j] >= arr2[k]) {
                newArray[i] = arr1[j];
                j++;
            }
            else {
                newArray[i] = arr2[k];
                k++;
            }
        }
        

        return newArray;
    } 
}
