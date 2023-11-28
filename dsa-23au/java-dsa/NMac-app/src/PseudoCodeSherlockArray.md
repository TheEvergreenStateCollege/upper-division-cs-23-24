Create a class named Result
    Define a method named balancedSums
        [existing balancedSums method logic, but remove the initial total sum calculation loop]

Create a class named Solution
    In the main method
        Initialize a BufferedReader for system input
        Initialize a BufferedWriter for system output

        Read an integer T (number of test cases) from input

        Repeat T times
            Read an integer n (size of the array) from input
            Read a line of integers, trim trailing spaces, and split into an array arrTemp
            Initialize an empty integer list arr
            Initialize a long variable total to 0

            For each string numStr in arrTemp
                Convert numStr to integer num
                Add num to arr
                Add num to total

            // Pass arr and total to balancedSums method
            Pass total along with arr to the balancedSums method of Result class
            Write the result to output and add a new line

        Close the BufferedReader and BufferedWriter
