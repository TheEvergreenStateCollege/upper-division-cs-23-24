package com.mycompany.app;

/**
 * Hello world!
 *
 */
public class App 
{
    public static int fib(int n) {
        if (n == 0) {
            return 1;
        }
        else if (n == 1) {
            return 1;
        }
        return fib(n-2) + fib(n-1);
    }
    
    public static void main( String[] args )
    {
        System.out.println(fib(7));
    }
}
