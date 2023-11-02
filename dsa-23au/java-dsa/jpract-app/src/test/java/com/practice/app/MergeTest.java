

import java.util.Random;

public class MergeTest 
{
    public static void main(String[] args)
{
    int[] num = new int[10];
    Random random = new Random();

    int i;
    int j;
    int u = 9;

    System.out.println(u);

    for(i = 0; i < num.length; i++)
    {
        num[i] = random.nextInt(100000);
        System.out.println(num[i]);

    }

    MergeSort.sort(num);

    for(j = 0; j < num.length; j++)
    {
        System.out.println(num[j]);
    }
}
}

