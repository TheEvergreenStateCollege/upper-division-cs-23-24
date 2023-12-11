import java.util.*;

public class problems {
    public static String balancedSums(List<Integer> arr) {
        int totalSum = 0;
        int leftSum = 0;
    
        // Calculate the total sum of the array
        for (int num : arr) {
            totalSum += num;
        }
    
        for (int i = 0; i < arr.size(); i++) {
            // Calculate right sum by subtracting left sum and the current element
            int rightSum = totalSum - leftSum - arr.get(i);
    
            if (leftSum == rightSum) {
                return "YES";
            }
    
            // Update left sum for the next iteration
            leftSum += arr.get(i);
        }
    
        return "NO";
    }
    
}

// MODIFIED PROBLEM
/* Find an element of an array such that the sum of all elements to the left of the element is the same as the sum of all elements to the right
   	 initialize two int variables, one for total sum of the array, one for the left side
    	calculate total sum of the array using for loop
  	 calculate left sum by subtracting left sum and current element using for loop
      		  if left and right sum are equal, return yes with sums
        		update left sum for next iteration
   	 return no with sums if no element has unbalanced subarrays */
