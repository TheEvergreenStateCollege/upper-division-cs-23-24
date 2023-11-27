Create a class named Result

Define a method named balancedSums
    Accepts a list of integers named arr
    Initialize a long variable total to 0
    Initialize a long variable leftSum to 0

    // Calculate the total sum of elements in arr
    For each integer num in arr
        Add num to total

    // Iterate over each integer num in arr
    For each integer num in arr
        Subtract num from total
        If leftSum equals total
            Return "YES"
        Add num to leftSum

    Return "NO" (if no balanced point is found)

Create a class named Solution

In the main method
    Initialize a BufferedReader for system input
    Initialize a BufferedWriter for system output

    Read an integer T (number of test cases) from input

    Repeat T times
        Read an integer n (size of the array) from input
        Read a line of integers, trim trailing spaces, and split into an array arrTemp
        Initialize an empty integer list arr

        For each string in arrTemp
            Convert to integer and add to arr

        Call balancedSums method of Result class with arr as argument
        Write the result to output and add a new line

    Close the BufferedReader and BufferedWriter
