public class SherlockArray {
    public static String balanceSums(int[] arr) {
        int n = arr.length;

        int totalSum = 0;
        for (int i = 0; i < n - 1; i++) {
            totalSum = totalSum + arr[i];
        }

        int leftSum = 0;
        for (int i = 0; i < n - 1; i++) {
            int rightSum = totalSum - leftSum - arr[i];
            
            if (leftSum == rightSum) {
                return "YES";
            }

            leftSum = leftSum + arr[i];
        }

        return "NO";
    }
}

/*  method balanceSums(arr)
         
n = length of arr
     
// Calculate the total sum of the array
totalSum = 0
for i = 0 To n - 1
totalSum = totalSum + arr[i]
 END fOR
leftSum = 0
// Iterate through the array and check if there's an element that satisfies the condition
for i = 0 to n - 1
rightSum = totalSum - leftSum - arr[i]
// Calculate the sum to the right of the current element
IF leftSum equals rightSum THEN
RETURN "YES"// If the left and right sums are equal, return "YES"

    leftSum = leftSum + arr[i]// Add the current element to the left sum for the next iteration
    END for

            RETURN "NO"
            // If the loop completes without finding a balance point, return "NO"
         
*/
