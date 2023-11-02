package dev.codewithfriends;

import java.util.Comparator;
import java.util.Random;
/**
 * Hello world!
 *
 */
public class App
{

    public static void main( String[] args )
    {   
        int[] valArr = {1, 3, 5, 9};
        mergeSort(valArr);
    }

    public static <T extends Comparable<T>> T[] insertionSort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            T key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

        return arr;
    }
    public static int[] mergeSort(int[] array){
        return mergeSortHelper(array,0, array.length - 1);

    }
        // @param arr - 
        // @parm start - begniing / lefmost inde, inclsice, to start mergesort
        // @param end - 
        // @return 
        
    public static int[] mergeSortHelper(int[] arr, int start, int end){
        int[] newArray = new int[end - start + 1]; 
                
        // Check if arr requested part of the array in size 1
        if (start == end) // Base Case
            return arr;  
        
        if (start < end){
            int center = (start + end) /2;

            mergeSortHelper(arr, start, center);       // left half
            mergeSortHelper(arr, center + 1, end);     // right half
            merge(arr, newArray, start, center, end);

            int leftHalfPointer = start;
            int rightHalfPointer = start + center;
            for(int i = 0; leftHalfPointer < rightHalfPointer; i++){
                if(arr[leftHalfPointer] <= arr[rightHalfPointer]){
                    newArray[i] = arr[leftHalfPointer];
                }
                else{
                    newArray[i] = arr[rightHalfPointer];
                }
            }
            System.out.println(newArray);
            return arr;
        }
        //Split into left and right
        //Recursively call mergeSortHelper on left and right halves
        return newArray;
    }
    
        public static int[] merge(int[] arr, int[] newArray, int start, int center, int end)
        {
            int leftEnd = end -1;
            int tmpPos = start;
            int numElements = end - start + 1;

            // Main Loop

            while(start <= leftEnd && center <= end){
                if (arr[start] < arr[end]){
                    newArray[tmpPos++] = arr[start++];
                } else
                    newArray[tmpPos++] = arr[end++];
                }
            while(start <= leftEnd){
                newArray[tmpPos++] = arr[start++];
            }
            while(center <= end){
                newArray[tmpPos++] = arr[end++];
            }
            for (int i =0; i < numElements; i++, end--)
                arr[end] = newArray[end];

            return arr;

    }
}

