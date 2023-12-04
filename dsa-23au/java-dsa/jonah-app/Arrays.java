// Jonah Eadie

import java.util.ArrayList;
import java.util.List;

public class Arrays {
    // Method to reverse an array of integers. Argument "a" is the list to be reversed
    public static List<Integer> reverseArray(List<Integer> a) {
        // Instantiate new list of integers "lst", setting its elements equal to those of the argument "a"
        List<Integer> lst = new ArrayList<Integer>(a);
        
        // Iterate through elements of a until reaching the index which is half of its largest index. "i" is current index
        for (int i = 0; i < a.size() / 2; i++) {
            // Instantiate a temporary variable "temp," set equal to the largest index of a minus "i"
            int temp = a.get(a.size() - 1 - i);
            // Set element of lst[largest_index - i] equal to a[i]
            lst.set(a.size() - 1 - i, a.get(i));
            // Set lst[i] equal to "temp"
            lst.set(i, temp);
        }
        // Return the list
        return lst;
    }
}

/* Method to reverse an array of integers. Argument "a" is the list to be reversed
   public static List<Integer> reverseArray(List<Integer> a) {
    Instantiate new list of integers "lst", setting its elements equal to those of the argument "a"

    Iterate through elements of a until reaching the index which is half of its largest index. "i" is current index
    Instantiate a temporary variable "temp," set equal to the largest index of a minus "i"
    Set element of lst[largest_index - i] equal to a[i]
    Set lst[i] equal to "temp"

    Return the list
}*/

// Modification for 11/28 class, Tuesday morning

/* Method to reverse an array of integers and double them. Argument "a" is the list to be reversed
   public static List<Integer> reverseArrayAndDouble(List<Integer> a) {
    Instantiate new list of integers "lst", setting its elements equal to those of the argument "a"

    Iterate through elements of a until reaching the index which is half of its largest index. "i" is current index
    Instantiate a temporary variable "temp," set equal to the largest index of a minus "i"
    Set element of lst[largest_index - i] equal to a[i] * 2
    Set lst[i] equal to "temp" * 2

    Return the list
}*/