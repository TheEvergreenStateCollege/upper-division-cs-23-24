package com.pswishcorp.app;

public class App 
{
    //Insertion Sort

    // Starts at index 0 marks it as sorted
    // Unsorted starts at index 1
    //     and comparesto index.next
    //         If current is greater, swaps index.current with index.next
    //     If not, move to .next
    // Then it compares.next (index 1) with the current
    //     If greater, swaps
    //         If not greater then, 
    //             compare to previous until previous is less than current 
    // Continues until index n-1 > n-2
    public static void main(String[] args) {
        int[] inputList = {12, 3, 21, 8, 1, 47, 50, 88, 3, 13};
        }

    public static int[] insertionSort(int[] inputList){
        int array_length = inputList.length;
        
        //Start at index 1 for i since the 0 index is already sorted
        for (int i = 1; i < array_length; i++) {
            int key = inputList[i];
            
            // Set a previous variable 
            int previous = i - 1;

            // loop through the input lit at j index
            while (previous >= 0 && inputList[previous] > key) {
                inputList[previous + 1] = inputList[previous];
                previous--;
            }
            inputList[previous + 1] = key;
        }
        return inputList;
    }

}
