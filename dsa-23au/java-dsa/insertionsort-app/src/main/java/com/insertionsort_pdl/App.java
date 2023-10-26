package com.insertionsort_pdl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static int[] main(int[] a)
    {
        for (int i = 1; i<a.length; i++){
            int inserted = a[i];
            int insertPlace;
            for (insertPlace = i-1; insertPlace>=0; insertPlace--){
                if (a[insertPlace] > inserted){
                    a[insertPlace+1] = a[insertPlace];
                }
                else {
                    break;
                }
            }
            a[insertPlace+1] = inserted;
            
        }
        return a;
        
    }
}
