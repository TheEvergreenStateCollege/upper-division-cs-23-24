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


    // public longFactorial(int n)
    public static void extraLongFactorials(int n) {

        // largeN = n
        // count = n - 1
        BigInteger largeN = BigInteger.valueOf(n);
        int count = n - 1;
        
        
        if (n > 100 || n < 0){
            System.out.println("int too large or too small");
        }
        // while count is bigger than 0 
        //      largeN = largeN * count
        //      count--
        // print largeN
        else {
            while(count != 0){
                largeN = largeN.multiply(BigInteger.valueOf(count));
                --count;
            }
        }
        largeN.toString();
        System.out.println(largeN);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        Result.extraLongFactorials(n);

        bufferedReader.close();
    }
}