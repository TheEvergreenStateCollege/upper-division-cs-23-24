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
     * Complete the 'nonDivisibleSubset' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY s
     */

}
public class NonDivisibleSubset {

    public static int nonDivisibleSubset(int k, List<Integer> s) {
        // Step 2: Initialize an array to keep track of remainders
        int[] remainderCount = new int[k];

        // Step 3: Count Remainders
        for (int num : s) {
            remainderCount[num % k]++;
        }

        // Step 4: Handle special case (when k is even)
        if (k % 2 == 0) {
            remainderCount[k / 2] = 1;
        }

        // Step 5: Calculate the result
        int result = Math.min(remainderCount[0], 1);
        for (int i = 1; i <= k / 2; i++) {
            result += Math.max(remainderCount[i], remainderCount[k - i]);
        }

        // Step 6: Return the result
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);
        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        int result = nonDivisibleSubset(k, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
