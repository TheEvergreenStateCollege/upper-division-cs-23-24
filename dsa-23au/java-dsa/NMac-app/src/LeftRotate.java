import java.util.Scanner;

public class LeftRotate {

    public static void leftRotate(int[] numArray, int d) {
        // remember implementation method here


        if (numArray.length <= 1) {
            return;
        }
            d = d % numArray.length;

            int[] temp = new int[d];


        System.arraycopy(numArray, 0, temp, 0, d);

        for (int i = d; i < numArray.length; i++) {
            numArray[i - d] = numArray[i];
        }

        System.arraycopy(temp, 0, numArray, numArray.length - d, d);

    }

 public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

     // Example input process:
     // Enter array length: 5
     // Enter array elements: 1 2 3 4 5
     // Enter number of rotations: 2

     System.out.print("Enter array elements: ");
     int n = scanner.nextInt();

     int[] myArray = new int[n];
     System.out.println("Enter array elements: ");
     for (int i = 0; i < n; i++) {
         myArray[i] = scanner.nextInt();
     }

     System.out.println("Enter number of rotations: ");
     int rotations = scanner.nextInt();

     leftRotate(myArray, rotations);

     // Display the rotated array
     for (int num : myArray) {
         System.out.println(num + " ");
     }
     scanner.close();
    }
}
