package dev.learner_long_life;

/**
 * Hello world!
 *
 */
public class App 
{

    public static int fib(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;
        } else {
            return fib(n-1) + fib(n-2);
        }
    }

    public static void main( String[] args )
    {
        int result = fib(73);
        System.out.println( "The 73rd Fibonacci number is " + result );
    }
}
