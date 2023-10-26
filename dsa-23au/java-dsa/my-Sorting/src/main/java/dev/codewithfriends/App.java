package dev.codewithfriends;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public static int[] insertionSort(int array[])
    {

        int n = array.length;
        for(int j = 1; j < n; j++)
        {
            int key = array[j];
            int i = j-1;
            while((i > -1) && (array[i] > key) )
            {
                array[i+1] = array[i];
                i--;
            }
                array[i + 1] = key;
        }
        return array;
    }

    public static void mergeSort(int[] A)
    {
        if(A.length > 1)
        {
            int q = A.length/2;

            int[] leftArray = Arrays.copyOfRange(A, 0, q-1);


            int[] rightArray = Arrays.copyOfRange(A, q, A.length -1 );

            mergeSort(leftArray);
            mergeSort(rightArray);

            merge(A, leftArray, rightArray);

        }
    }

    private static void merge(int[] a, int[] l, int[] r)
    {
        int totElem = l.length + r.length;

        int i, li, ri;
        i = li = ri = 0;
        while(i < totElem)
        {
            if((li < l.length) && (ri < r.length))
            {
                if(l[i] < r[ri])
                {
                    a[i] = l[ri];
                    i++;
                    li++;
                }
                else{
                    a[i] = r[ri];
                    i++;
                    ri++;
                }
            } else{
                if(li >= l.length)
                {
                    while(ri < r.length)
                    {
                        a[i] = r[ri];
                        i++;
                        ri++;
                    }
                }
                if(ri >= r.length)
                {
                    while(li < l.length)
                    {
                        a[i] = l[li];
                        li++;
                        i++;
                    }
                }
            }
        }

        
    }
}
