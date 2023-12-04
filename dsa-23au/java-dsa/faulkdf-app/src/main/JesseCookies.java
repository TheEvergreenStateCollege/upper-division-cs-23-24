import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'cookies' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY A
     */

    
    public static int cookies(int k, List<Integer> A) {
        // Create a priority queue (min-heap) to store the sweetness of cookies.
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Initialize the priority queue with the elements from the list A.
        pq.addAll(A);

        int operations = 0;

        // Continue the process until there's only one cookie in the queue
        // or the least sweet cookie in the queue has sweetness >= k.
        while (pq.size() > 1 && pq.peek() < k) {
            // Take the two least sweet cookies from the queue.
            int firstCookie = pq.poll();
            int secondCookie = pq.poll();

            // Combine the two cookies and calculate their sweetness.
            int combinedSweetness = firstCookie + (2 * secondCookie);

            // Put the combined cookie back in the queue.
            pq.offer(combinedSweetness);

            // Increment the number of operations.
            operations++;
        }

        // Check if the desired sweetness level k is achieved.
        if (pq.peek() >= k) {
            return operations;
        } else {
            // If not achieved, return -1 to indicate it's not possible.
            return -1;
        }
    }


    

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> A = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.cookies(k, A);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}