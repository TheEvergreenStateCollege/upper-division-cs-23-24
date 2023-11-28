// Jonah Eadie

import java.lang.Math;

public class CatAndMouse {
    public static String catAndMouse(int catA, int catB, int mouse) {
        int catADifference = Math.abs(mouse - catA);
        int catBDifference = Math.abs(mouse - catB);

        if (catADifference == catBDifference || (catADifference > 3 && catBDifference > 3)) {
            return "Mouse C";
        }

        if (catADifference < catBDifference) {
            return "Cat A";
        } else {
            return "Cat B";
        }
    }
}


// Pseudocode by Jonah Luteyn
/*# https://www.hackerrank.com/challenges/cats-and-a-mouse/problem
def catAndMouse(catA, catB, mouse):

    # find absolute difference between index of catA and index of mouse
    # find absolute difference between index of catB and index of mouse

    # compare absolute differences from previous steps
        # if differences are the same, or if both distances are greater than 3, return 'Mouse C'
        # if catA from mouse difference is less than catB from mouse difference, return 'Cat A'
        # otherwise (if catA from mouse difference is greater than catB from mouse difference), return 'Cat B'*/
