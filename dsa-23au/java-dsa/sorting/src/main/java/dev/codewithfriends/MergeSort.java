package dev.codewithfriends;

public class MergeSort {
/*
 * This is what I got from ChatGPT,
 * <AnyType extends Comparable<? super AnyType>>: This is a generic type parameter declaration. 
 * AnyType is a placeholder for a type that must extend Comparable. 
 * The ? super AnyType part means that the Comparable interface can compare objects of type AnyType or any of its super types. 
 * This is used to ensure type safety, so that you can only pass objects that can be compared to each other.
 */
 
 /*
  * mergeSort(args): This line calls the mergeSort method and passes the args array to it. 
  * It should take an array of AnyType and sort it.
  */

    public static <AnyType extends Comparable<? super AnyType>> void main(String[] args) {
        mergeSort(args);
    }

    public static <AnyType extends Comparable<? super AnyType>> void mergeSortHelper(AnyType[] array, AnyType[] tmpArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSortHelper(array, tmpArray, left, center);       // left half
            mergeSortHelper(array, tmpArray, center + 1, right);  // right half
            merge(array, tmpArray, left, center +1, right);
        }
    }

    public static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] array) {
        AnyType[] tmpArray = (AnyType[]) new Comparable[array.length];
        mergeSortHelper(array, tmpArray, 0, array.length -1);
    }

    public static <AnyType extends Comparable<? super AnyType>> void merge(AnyType[] array, AnyType[] tmpArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
    
        // Main loop
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (array[leftPos].compareTo(array[rightPos]) <= 0) {
                tmpArray[tmpPos++] = array[leftPos++];
            } else {
                tmpArray[tmpPos++] = array[rightPos++];
            }
        }
        // copy rest of first half
        while (leftPos <= leftEnd) {
            tmpArray[tmpPos++] = array[leftPos++];
        }
        // Copy rest of the right half
        while (rightPos <= rightEnd) {
            tmpArray[tmpPos++] = array[rightPos++];
        }
        // copy tmp array back
        for (int i = 0; i < numElements; i++, rightEnd--) {
            array[rightEnd] = tmpArray[rightEnd];
            *rightEnd being copied back into place 
        }
    }   
}
