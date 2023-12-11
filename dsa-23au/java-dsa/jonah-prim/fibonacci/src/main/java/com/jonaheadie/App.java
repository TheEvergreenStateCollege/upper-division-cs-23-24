package com.jonaheadie;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println(iterative(10));
        System.out.println(recursive(10));
    }

    public static int iterative(int n) {
        if (n <= 1) {
            return n;
        }

        int prev = 1;
        int curr = 1;

        for (int i = 2; i < n; i++) {
            int temp = curr;
            curr = curr + prev;
            prev = temp;
        }

        return curr;
    }

    public static int recursive(int num) {
        if (n <= 1) {
            return n;
        }

        return recursiveHelper(1, 1, 0, num);
    }

    public static int recursiveHelper(int curr, int prev, int iter, int num) {
        if (iter == num) {
            return curr;
        }

        return recursiveHelper(curr + prev, curr, iter++, num);
    }
}
