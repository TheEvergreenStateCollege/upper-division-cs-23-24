import java.util.random.RandomGenerator;

public class Solution {

    //This first portion is to simply count how many spaces there are

    void replaceSpaces(char[] str, int length) {

        int spaceCount = 0, newLength, i;
        for (i = 0; i < length; i++) {
            if (str[i] == "") {
                spaceCount++;
            }
        }

        //This second portion we edit the string itself

        newLength = length + spaceCount * 2;
        str[newLength] = "\0";
        for (i = length - 1; i >= 0; i--) {
            if (str[i] == "") {
                str[newLength - 1] = "0";
                str[newLength - 2] = "2";
                str[newLength - 3] = "%";
                newLength = newLength -3;
            } else {
                str[newLength - 1] = str[i];
                newLength = newLength - 1;
            }

        }

    }

    void rotate(int[][] matrix, int n) {

        // A layer is a concentric square starting on the outer rim
        // of the matrix and working its way into the innermost elements.
        for (int layer = 0; layer < n / 2; ++layer) {

            // First is the beginning index on a side
            int first = layer;

            // Last is the end index on a side
            int last = n - 1 - layer;

            for(int i = first; i < last; ++i) {

                // offset is the 0-based progress we are making on one side, relative to first
                int offset = i - first;

                int top = matrix[first][i];

                //After this, the square rotates clockwise

                // F
                matrix[first][i] = matrix [last-offset][first];

                matrix[last-offset][first] = matrix[last][last - offset];

                matrix[last][last - offset] = matrix[i][last];

                matrix[i][last] = top;

            }

        }

    }

    public static void main(String[] args) {
        int[][] matrix = new int[10][10];
        int[][] matrix2 = new int[10][10];

        RandomGenerator rand = new RandomGenerator();

        for (int i = 0; i < 10; i += 1) {
            for (int j = 0; j < 10; j += 1) {
                matrix[i][j] = rand.nextInt();
                matrix2[i][j] = matrix[i][j];
            }
        }

        /*
           matrix[i][j]    ...   matrix[i][n-j]
           ...
           matrix[n-i][j]   ...  matrix[n-i][n-j]
         */
        rotate(matrix);
        assertEquals(matrix[1][2], matrix[])

    }

}
