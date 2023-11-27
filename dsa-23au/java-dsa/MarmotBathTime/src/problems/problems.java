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
