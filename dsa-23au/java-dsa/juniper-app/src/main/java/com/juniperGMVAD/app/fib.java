package com.juniperGMVAD.app;

public class fib{


public static void main(String args[])
{
    int a;
    int b;
    int n;

    a = 0;
    b = 1;
    
		    while(b < 100)
		    {
		    n = a + b;
		    
		    System.out.println(n);
		    a = b;
		    b = n;
		    
		    }
		    
}
}