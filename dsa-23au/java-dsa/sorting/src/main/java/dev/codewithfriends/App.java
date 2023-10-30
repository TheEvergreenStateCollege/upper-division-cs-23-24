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
    public static int[] mergeSort(int[] arr){
        throw new RuntimeException();
       
        



    }

    public static int[] mergeSortHelper(int[] arr, int start, int end){




    }



}
