package main.java.com.practice.app;

public class mergeSort 
{
    public void sort(int[] inputArray)
    {
		
		//Instantiating the variables length and midpoint
		int length = inputArray.length;
		int midpoint = length / 2;
		
		//setting leftSide size equal to the length
		//up to midpoint
		//setting rightSide equal to the remaining length
		int[] leftSide = new int[midpoint];
		int[] rightSide = new int[length - midpoint];
		
		//if the length is equal to 2, it is already
		//in order, there is no dividing possible
		//or necessary
		if (length < 2)
		{
			return;
		}
		
		//for inputs greater than that it is splitting them
		//by filling the leftSide and rightSide
		else
		{
			for(int i = 0; i < midpoint; i++)
			{
				leftSide[i] = inputArray[i];
			}
			for(int j = midpoint; j < length; j++)
			{
				rightSide[j - midpoint] = inputArray[j];
			}

			sort(leftSide);
			sort(rightSide);
			
			merge(inputArray, rightSide, leftSide);
		}
    }
    public void merge(int[] leftSide, int[] rightSide, int[] inputArray)
	{
	int i = 0, j = 0, k = 0;
	
	while(i < leftSide.length && j < rightSide.length)
	{
		if(leftSide[i] <= rightSide[j])
		{
			inputArray[k] = leftSide[i];
			i++;
		}
		else 
		{
			inputArray[k] = rightSide[j];
			j++;
		}
		k++;
	}
    while (i < leftSide.length) {
        inputArray[k] = leftSide[i];
        i++;
        k++;
    }
    while (j < rightSide.length) {
        inputArray[k] = rightSide[j];
        j++;
        k++;
    }
    }

}